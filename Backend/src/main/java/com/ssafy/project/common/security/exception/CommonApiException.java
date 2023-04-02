package com.ssafy.project.common.security.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommonApiException extends RuntimeException{

    private final ErrorCode errorCode;
}
