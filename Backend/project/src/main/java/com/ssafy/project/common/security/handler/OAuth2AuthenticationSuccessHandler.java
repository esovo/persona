package com.ssafy.project.common.security.handler;

import com.ssafy.project.common.security.HttpCookieOAuth2AuthorizationRequestRepository;
import com.ssafy.project.common.security.exception.BadRequestException;
import com.ssafy.project.common.security.properties.AppProperties;
import com.ssafy.project.common.util.provider.CookieProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

// static변수 import
import static com.ssafy.project.common.security.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Log4j2
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AppProperties appProperties;

    private final CookieProvider cookieProvider;

    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        log.info("onAuthenticationSuccess 실행");
        String targetUrl = determineTargetUrl(request, response, authentication);

        // response가 이미 커밋되면, 추가적인 응답 불가능
        if (response.isCommitted()) {
            logger.debug("해당 Url은 이미 커밋되었습니다. " + targetUrl);
            return;
        }
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        log.info("determineTargetUrl 실행");
        Optional<String> redirectUri = cookieProvider.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);

        // uri가 존재하지만, 등록되지 않은 경우
        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get()))
            throw new BadRequestException("등록되지 않은 Uri입니다.");

        // 리디렉션 uri가 있으면 그 값으로, 없으면 defaultUri로 할당
        String targetUri = redirectUri.orElse(getDefaultTargetUrl());

        // 에러메세지 param 값 없음으로 반환
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

        // param uri와 properties의 uri목록을 비교하여, anymatch된다면 true 반환
        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost()) && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                        return true;
                    }
                    return false;
                });
    }

}