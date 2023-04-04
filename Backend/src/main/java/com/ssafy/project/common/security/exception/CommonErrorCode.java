package com.ssafy.project.common.security.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    //게시글
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."),
    //댓글
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."),
    //유저
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    //북마크
    BOOKMARK_ALREADY_EXIST(HttpStatus.ALREADY_REPORTED, "이미 존재하는 북마크 입니다."),
    BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 북마크입니다."),
    //비디오
    VIDEO_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 영상입니다."),
    //참여자
    PARTICIPANT_NOT_FOUND(HttpStatus.NOT_FOUND, "대본 연습에 참가한 이력이 없습니다."),
    //대본
    SCRIPT_NOT_FOUND(HttpStatus.NOT_FOUND,"대본이 존재하지 않습니다."),

    FILE_NOT_VALID(HttpStatus.BAD_REQUEST,"유효하지 않은 파일 형식입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
