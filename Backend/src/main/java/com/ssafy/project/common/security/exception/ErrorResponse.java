package com.ssafy.project.common.security.exception;

import io.swagger.annotations.ApiOperation;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final String code;
    private final String message;

    public static ErrorResponse of(String code, String message){
        return new ErrorResponse(code, message);
    }
}
