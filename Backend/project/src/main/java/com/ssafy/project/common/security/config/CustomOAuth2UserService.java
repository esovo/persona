package com.ssafy.project.common.security.config;

import com.ssafy.project.common.db.dto.social.*;
import com.ssafy.project.common.db.entity.base.SocialEnum;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.repository.UserRepository;
import com.ssafy.project.common.security.common.UserPrincipal;
import com.ssafy.project.common.security.exception.NoEmailProvidedException;
import com.ssafy.project.common.security.exception.NotAllowedSocialTypeException;
import com.ssafy.project.common.util.provider.RedisProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @PostConstruct
            void hi() {
        log.info("CustomOAuth2UserService init");
    }
    UserRepository userRepository;

    RedisProvider redisProvider;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException{
        log.info("0000000000000000000");
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(oAuth2UserRequest);
        log.info("1111111111111111111111111111111");
        // OAuth2 서비스 구분 코드 => 구글, 카카오, 네이버
        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();

        // OAuth2 Attributes의 PK값 => 구글은 sub
        String userNameAttributeName = oAuth2UserRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        /*
         * 소셜사이트의 타입을 판별한 후, 해당하는 사이트의 attributes를 social site에 해당하는 OAuth2UserInfo로 반환
         * 각 social site마다 다른 attributes의 key값 들을 같은 abstract class의 getter를 통해 가져올 수 있게 함
         */
        OAuth2UserInfo OAuth2UserInfo = getOAuth2UserInfo(
                oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        log.info("2222222222222222222222222222222222");
        /*
         * 일반적으로 OAuth 2.0 프로토콜을 따르는 소셜 로그인 공급자는 사용자의 이메일 주소를 제공합니다.
         * 하지만 OAuth 2.0 프로토콜 자체는 이메일 주소를 필수적으로 제공하도록 규정하지 않으므로, OAuth2UserInfo에서 이메일 속성이 null일 수도 있습니다.
         * 또한, 사용자가 소셜 로그인 공급자에 등록된 이메일 주소를 변경할 수도 있으므로,
         * 사용자가 이미 가입되어 있는 것이라고 해도 OAuth2UserInfo에서 이메일 주소를 가져올 수 없을 수 있습니다.
         * 따라서, 이메일 주소가 null인 경우를 고려하여 코드를 작성하는 것이 좋습니다.
         */
        String email = Optional.ofNullable(OAuth2UserInfo.getEmail())
                .orElseThrow(() -> new NoEmailProvidedException("이메일을 제공받지 못했습니다."));

        Optional<User> idOptinal = userRepository.findBySocialAuthProviderId(userNameAttributeName);

        User user = null;

        // loadUser는 로그인, 혹은 세션 만료 시 호출되므로, 해당 시점마다 소셜사이트의 사용자 정보를 DB에 갱신
        if(idOptinal.isPresent()) {

            // 1. 기존 회원이라면, 소셜사이트의 정보로 update
            user = idOptinal.get();
            updateUser(email, user);

            // 새 권한 생성
            UserPrincipal userPrincipal = UserPrincipal.loadDefault(user);

            // principal에 기존 유저의 권한정보를 set
            userPrincipal.setAuthorities(user.getAuthorities());

            redisProvider.cashAuthoriesInDB(idOptinal.get().getId(), userPrincipal.getAuthoritiesToList());

            return userPrincipal;
        }
        else {

            // 2. 신규 회원이라면, new User Entity를 생성
            user = registUser(registrationId, OAuth2UserInfo);

            // user의 초기 권한 생성 - CLIENT 권한으로 set
            UserPrincipal userPrincipal = UserPrincipal.create(user);

            redisProvider.cashAuthoriesInDB(idOptinal.get().getId(), userPrincipal.getAuthoritiesToList());

            return userPrincipal;
        }
    }

    public OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {

        if (registrationId.equalsIgnoreCase(SocialEnum.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        }else if (registrationId.equalsIgnoreCase(SocialEnum.NAVER.toString())) {
            return new NaverOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(SocialEnum.KAKAO.toString())) {
            return new KakaoOAuth2UserInfo(attributes);
        } else {
            throw new NotAllowedSocialTypeException("사용 불가능한 소셜 타입입니다.");
        }
    }

    public User registUser(String registrationId, OAuth2UserInfo oAuth2UserInfo) {

        // update/regist date 자동생성
        return User.builder()
                // nickname은 default로 소셜에 저장되어 있는 것을 사용
                .nickname(oAuth2UserInfo.getName())
                .socialAuth(SocialAuth.builder()
                        .providerId(oAuth2UserInfo.getId())
                        .socialType(SocialEnum.valueOf(registrationId))
                        .email(oAuth2UserInfo.getEmail())
                        .name(oAuth2UserInfo.getName())
                        .imageUrl(oAuth2UserInfo.getImageUrl())
//                        .ip()
                        .build())
                .build();
    }

    public User updateUser(String email, User user) {

        SocialAuth socialAuth = user.getSocialAuth();
        socialAuth.setEmail(email);
        // registtime, providerId는 고정
        // providerId, imageUrl, nickname은 사용하던 것 으로 유지
        // updatedDate 자동 변경

        return user;
    }
}
