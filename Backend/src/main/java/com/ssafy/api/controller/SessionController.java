package com.ssafy.api.controller;

import com.ssafy.api.service.JwtService;
import com.ssafy.api.service.MemberService;
import com.ssafy.api.service.RedisService;
import com.ssafy.common.db.dto.request.MemberLoginReqDto;
import com.ssafy.common.db.dto.request.MemberRegistReqDto;
import com.ssafy.common.db.dto.response.TokenDto;
import com.ssafy.common.util.common.MemberUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionController {

    private final MemberService memberService;

    private final JwtService jwtService;

    private final MemberUtil memberUtil;

    private final RedisService redisService;


    @Transactional
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "access-token/refresh-token 발급 후 Redis 저장 및 프론트에 반환", responses = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "401", description = "등록되지 않은 사용자")})
    public ResponseEntity<?> login(@Parameter(name = "memberLoginReqDto")
                                   @RequestBody MemberLoginReqDto memberLoginReqDto) {

        if(memberService.login(memberLoginReqDto)) {

            Map<String, TokenDto> resultMap = new HashMap<>();

            TokenDto accessToken = jwtService.createToken(memberLoginReqDto.getId(), "accessToken");
            TokenDto refreshToken = jwtService.createToken(memberLoginReqDto.getId(), "refreshToken");
            resultMap.put("access-token", accessToken);
            resultMap.put("refresh-token", refreshToken);

            // redis에 refresh-token 저장
            redisService.set(memberLoginReqDto.getId()+"_refresh_token", refreshToken.getValue(),
                    refreshToken.getValidDate(), TimeUnit.MILLISECONDS);

            return new ResponseEntity<Map<String, TokenDto>>(resultMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Transactional
    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "access-token 블랙리스트 등록, refresh-token 삭제", responses = {
            @ApiResponse(responseCode = "200", description = "성공")})
    public ResponseEntity<?> logout(HttpServletRequest request) {

        String id = memberUtil.getMemberIdFromPrincipal();
        String token = jwtService.resolveToken(request);

        Date expireTime = jwtService.getExpireTime(token);

        // Date 객체는 생성 시 초기화하지 않아도 default 현재시간
        Date nowTime = new Date();
        Long remainTime = expireTime.getTime() - nowTime.getTime();

        // refresh-token 삭제
        redisService.delete(id+"_refresh_token");

        // access-token blacklist 등록
        redisService.setBlackList(token, id, remainTime, TimeUnit.MILLISECONDS);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/regist")
    @Operation(summary = "회원가입", description = "등록된 아이디/이메일인지 조회 후 아니면 회원가입", responses = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 아이디 혹은 이메일")})
    public ResponseEntity<?> registMember(@Parameter(name = "memberRegistReqDto")
                                          @RequestBody MemberRegistReqDto memberRegistReqDto) {

        if(memberService.regist(memberRegistReqDto)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    @Transactional
    @DeleteMapping
    @Operation(summary = "탈퇴", description = "로그인한 회원 정보 삭제 및 토큰 삭제", responses = {
            @ApiResponse(responseCode = "200", description = "성공")})
    public ResponseEntity<?> deleteMember(HttpServletRequest request) {
        
        // logout과 토큰 해제 로직 동일
        String id = memberUtil.getMemberIdFromPrincipal();
        String token = jwtService.resolveToken(request);

        Date expireTime = jwtService.getExpireTime(token);

        Date nowTime = new Date();
        Long remainTime = expireTime.getTime() - nowTime.getTime();

        redisService.delete(id+"_refresh_token");

        redisService.setBlackList(token, id, remainTime, TimeUnit.MILLISECONDS);

        memberService.delete(id);


        return new ResponseEntity<Void>(HttpStatus.OK);
    }

//    @Transactional
//    @PostMapping("/reissue")
//    @Operation(summary = "탈퇴", description = "로그인한 회원 정보 삭제 및 토큰 삭제", responses = {
//            @ApiResponse(responseCode = "200", description = "성공")})
//    public ResponseEntity<?> deleteMember(HttpServletRequest request) {
//
//
//
//
//        return new ResponseEntity<Void>(HttpStatus.OK);
//    }
}
