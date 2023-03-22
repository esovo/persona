package com.ssafy.project.common.security.config.common;

import com.ssafy.project.common.security.HttpCookieOAuth2AuthorizationRequestRepository;
import com.ssafy.project.common.security.config.CustomOAuth2UserService;
import com.ssafy.project.common.security.entrypoint.CustomAuthenticationEntryPoint;
import com.ssafy.project.common.security.handler.OAuth2AuthenticationFailureHandler;
import com.ssafy.project.common.security.handler.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CorsFilter corsFilter;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 미사용
                .and()
                .csrf().disable() // csrf 미사용
                .headers().frameOptions().disable()
                .and()
                .formLogin().disable() // 로그인 폼 미사용
                .httpBasic().disable() // Http basic Auth 기반 로그인 창 미사용
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 인증,인가가 되지 않은 요청 시 발생
                .and()
                .addFilter(corsFilter)
                .authorizeRequests()
                .antMatchers("/oauth2/**").permitAll() // Security 허용 Url
//                .anyRequest().authenticated() // 그 외엔 모두 인증 필요
                .and()
                .oauth2Login()
                .authorizationEndpoint().baseUri("/oauth2/authorization") // OAuth2 로그인 Url
                .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository()) // 인증 요청을 쿠키에 저장하고 검색
                .and()
//                .redirectionEndpoint().baseUri("") // OAuth2 인증 후 Redirect Url => 프론트에서 처리
//                .and()
                .userInfoEndpoint().userService(customOAuth2UserService) // 소셜의 회원 정보를 받아와 가공처리
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler) // 인증 성공 시 Handler
                .failureHandler(oAuth2AuthenticationFailureHandler); // 인증 실패 시 Handler

        // OAuth2 리소스 서버에서 토큰 인증 처리
//        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

}

