package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.response.UserResDto;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.repository.UserRepository;
import com.ssafy.project.common.security.exception.CustomAuthException;
import com.ssafy.project.common.util.provider.RedisProvider;
import com.ssafy.project.common.util.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final TokenProvider tokenProvider;

    private final RedisProvider redisProvider;

    @Override
    public void logoutUser(HttpServletRequest request) {

        String token = tokenProvider.getTokenFromRequest(request);
        Long id = tokenProvider.getUserIdFromToken(token);

        Date expireTime = tokenProvider.getExpireTime(token);

        Date nowTime = new Date();
        long remainTime = expireTime.getTime() - nowTime.getTime();

        redisProvider.setBlackList(token, id, remainTime , TimeUnit.MILLISECONDS);
    }

    @Override
    public UserResDto detailUserById(long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new CustomAuthException("로그인 되어있지 않습니다."));
        return UserResDto.builder()
                .email(user.getEmail())
                .imageUrl(user.getSocialAuth().getImageUrl())
                .nickname(user.getNickname())
                .socialType(String.valueOf(user.getSocialAuth().getSocialType()))
                .createdDate(user.getCreatedDate()).build();

    }

    @Override
    public UserResDto detailUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomAuthException("존재하지 않는 이메일입니다."));

        return null;
    }
}
