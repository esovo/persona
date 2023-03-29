package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.UserModifyReqDto;
import com.ssafy.project.common.db.dto.response.UserDetailResDto;
import com.ssafy.project.common.db.dto.response.UserSearchDto;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.repository.UserRepository;
import com.ssafy.project.common.security.exception.CustomAuthException;
import com.ssafy.project.common.util.provider.RedisProvider;
import com.ssafy.project.common.util.provider.TokenProvider;
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
    public UserDetailResDto detailUserById(long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new CustomAuthException("로그인 되어있지 않습니다."));
        return UserDetailResDto.builder()
                .email(user.getEmail())
                .imageUrl(user.getSocialAuth().getImageUrl())
                .nickname(user.getNickname())
                .socialType(String.valueOf(user.getSocialAuth().getSocialType()))
                .createdDate(user.getCreatedDate()).build();
    }

    @Transactional
    @Override
    public UserSearchDto detailUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomAuthException("존재하지 않는 이메일입니다."));

        return UserSearchDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .imageUrl(user.getSocialAuth().getImageUrl()).build();
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomAuthException("존재하지 않는 회원입니다."));
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void modifyUser(Long id, UserModifyReqDto userModifyReqDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomAuthException("존재하지 않는 회원입니다."));
        user.setNickname(userModifyReqDto.getNickname());
        userRepository.save(user);
    }
}
