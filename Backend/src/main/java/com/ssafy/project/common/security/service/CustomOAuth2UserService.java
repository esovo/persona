package com.ssafy.project.common.security.service;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.ssafy.project.common.db.dto.social.*;
import com.ssafy.project.common.db.entity.base.SocialEnum;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.repository.UserRepository;
import com.ssafy.project.common.security.common.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        log.info("loadUser 호출");

//        OAuth2AccessToken oAuth2AccessToken = oAuth2UserRequest.getAccessToken();
//        String accessToken = oAuth2AccessToken.getTokenValue();
//        log.info("accesstoken : {}", accessToken);

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {

        Map<String, Object> mapForLog = oAuth2User.getAttributes();
        mapForLog.forEach((k, v) -> log.info("{} : {}", k,v));

        log.info("processOAuth2User 호출");
        OAuth2UserInfo oAuth2UserInfo = getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes()
        );

        if (StringUtils.isBlank(oAuth2UserInfo.getEmail()))
            throw new OAuth2AuthenticationException("이메일을 제공받지 못했습니다.");

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if (userOptional.isPresent()) {
            if (!userOptional.get().getSocialAuth().getSocialType().equals(SocialEnum.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId())))
                throw new OAuth2AuthenticationException("이미 다른 소셜로 가입되어 있습니다.");
            user = updateUser(userOptional.get(), oAuth2UserInfo);
        } else {
            user = registerUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        
        log.info("getOAuth2UserInfo 호출");
        if (registrationId.equalsIgnoreCase(SocialEnum.google.toString())) {
            log.info("is google");
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(SocialEnum.naver.toString())) {
            log.info("is naver");
            return new NaverOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(SocialEnum.kakao.toString())) {
            log.info("is kakao");
            return new KakaoOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationException("지원하지 않는 소셜 타입 : " + registrationId);
        }
    }

    private User registerUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        
        log.info("registerUser 실행");
        return userRepository.save(User.builder()
                .email(oAuth2UserInfo.getEmail())
                .nickname(oAuth2UserInfo.getName())
                .socialAuth(SocialAuth.builder()
                        .providerId(oAuth2UserInfo.getId())
                        .socialType(SocialEnum.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
                        .email(oAuth2UserInfo.getEmail())
                        .name(oAuth2UserInfo.getName())
                        .imageUrl(oAuth2UserInfo.getImageUrl())
                        .build())
                .build());
    }

    private User updateUser(User user, OAuth2UserInfo oAuth2UserInfo) {
        
        log.info("updateUser 실행");
        user.getSocialAuth().update(oAuth2UserInfo.getName(), oAuth2UserInfo.getImageUrl(), oAuth2UserInfo.getAttributes());
        return user;
    }

}