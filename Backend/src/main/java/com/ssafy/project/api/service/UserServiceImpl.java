package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.UserModifyReqDTO;
import com.ssafy.project.common.db.dto.response.UserDetailResDTO;
import com.ssafy.project.common.db.dto.response.UserHomeResDTO;
import com.ssafy.project.common.db.dto.response.UserSearchDTO;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.repository.UserRepository;
import com.ssafy.project.common.provider.AuthProvider;
import com.ssafy.project.common.provider.RedisProvider;
import com.ssafy.project.common.provider.TokenProvider;
import com.ssafy.project.common.security.exception.CustomAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final AuthProvider authProvider;

    private final TokenProvider tokenProvider;

    private final RedisProvider redisProvider;

    @Transactional
    @Override
    public void logoutUser(HttpServletRequest request) {

        String token = tokenProvider.getTokenFromRequest(request);
        Long id = tokenProvider.getUserIdFromToken(token);

        Date expireTime = tokenProvider.getExpireTime(token);

        Date nowTime = new Date();
        long remainTime = expireTime.getTime() - nowTime.getTime();

        redisProvider.setBlackList(token, id, remainTime , TimeUnit.MILLISECONDS);
    }

    @Transactional
    @Override
    public UserHomeResDTO homeUser() {

        long id = authProvider.getUserIdFromPrincipal();
        User user = userRepository.findById(id).orElseThrow(() -> new CustomAuthException("로그인 되어있지 않습니다."));

        return UserHomeResDTO.builder()
                .nickname(user.getNickname())
                .imageUrl(user.getSocialAuth().getImageUrl()).build();
    }

    @Transactional
    @Override
    public UserDetailResDTO detailUserById() {

        long id = authProvider.getUserIdFromPrincipal();
        User user = userRepository.findById(id).orElseThrow(() -> new CustomAuthException("로그인 되어있지 않습니다."));

        return UserDetailResDTO.builder()
                .email(user.getEmail())
                .imageUrl(user.getSocialAuth().getImageUrl())
                .nickname(user.getNickname())
                .socialType(String.valueOf(user.getSocialAuth().getSocialType()))
                .createdDate(user.getCreatedDate().toString()).build();
    }

    @Transactional
    @Override
    public UserSearchDTO detailUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomAuthException("존재하지 않는 이메일입니다."));

        return UserSearchDTO.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .imageUrl(user.getSocialAuth().getImageUrl()).build();
    }

    @Transactional
    @Override
    public void deleteUser() {

        long id = authProvider.getUserIdFromPrincipal();
        User user = userRepository.findById(id).orElseThrow(() -> new CustomAuthException("존재하지 않는 회원입니다."));
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void modifyUser(UserModifyReqDTO userModifyReqDto) {

        long id = authProvider.getUserIdFromPrincipal();
        User user = userRepository.findById(id).orElseThrow(() -> new CustomAuthException("존재하지 않는 회원입니다."));
        user.setNickname(userModifyReqDto.getNickname());
        userRepository.save(user);
    }
}
