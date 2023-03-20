package com.ssafy.project.common.security.handler;

import com.ssafy.project.common.security.HttpCookieOAuth2AuthorizationRequestRepository;
import com.ssafy.project.common.security.exception.BadRequestException;
import com.ssafy.project.common.security.properties.AppProperties;
import com.ssafy.project.common.util.provider.CookieProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import static com.ssafy.project.common.security.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;


@Log4j2
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${oauth2.google.id}")
    private String clientId;

    @Value("${oauth2.google.secret}")
    private String clientSecret;

//    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri = "http://localhost:8080/oauth2/callback/google";

    private final AppProperties appProperties;

    private final CookieProvider cookieProvider;

    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        log.info("onAuthenticationSuccess 실행");

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            System.out.println("Parameter name: " + paramName);
            System.out.print("Parameter values: ");
            for (String paramValue : paramValues) {
                System.out.print(paramValue + ", ");
            }
            System.out.println();
        }
//
//        String targetUrl = determineTargetUrl(request, response, authentication);
//
//        // response가 이미 커밋되면, 추가적인 응답 불가능
//        if (response.isCommitted()) {
//            logger.debug("해당 Url은 이미 커밋되었습니다. " + targetUrl);
//            return;
//        }
//        clearAuthenticationAttributes(request, response);
//
//        String authorizationCode = request.getParameter("code");
//        log.info("authorizationCode : {}", authorizationCode);
//
//        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        log.info("determineTargetUrl 실행");
        Optional<String> redirectUri = cookieProvider.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);

        log.info("등록 비교할 Uri : {}", redirectUri);

        // uri가 존재하지만, 등록되지 않은 경우
        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get()))
            throw new BadRequestException("등록되지 않은 Uri입니다.");

        // 리디렉션 uri가 있으면 그 값으로, 없으면 defaultUri ("/")
        String targetUri = redirectUri.orElse(getDefaultTargetUrl());

        // 등록되지 않은 Uri면
        return UriComponentsBuilder.fromUriString(targetUri)
                .queryParam("error", "")
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {

        log.info("clearAuthenticationAttributes 실행");
        // 발생했던 exception들을 삭제
        super.clearAuthenticationAttributes(request);

        // 쿠키에 저장되었던 인증정보를 삭제
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {

        log.info("isAuthorizedRedirectUri 실행");
        URI clientRedirectUri = URI.create(uri);

        log.info("==========================");
        List<String> uriListForLog = appProperties.getOauth2().getAuthorizedRedirectUris();
        for (String uris : uriListForLog) {
        log.info("인증된 Uri : {}", uris);
        }
        log.info("==========================");

        // param uri와 properties의 uri목록을 비교하여, anymatch된다면 true 반환
        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost()) && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                        log.info("등록되어 있는 Uri 입니다.");
                        return true;
                    }
                    return false;
                });
    }

}