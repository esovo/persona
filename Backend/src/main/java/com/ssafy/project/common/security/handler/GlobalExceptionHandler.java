package com.ssafy.project.common.security.handler;

import com.ssafy.project.common.security.exception.*;
import com.ssafy.project.common.db.dto.common.ResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.net.BindException;
import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseStatusExceptionHandler {

    // @NotNull, @NotBlank 등의 발리데이션 익셉션 발생 시 핸들링하는 메소드 => validation 라이브러리 포함 안해서 미사용
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public void handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        String msg = e.getBindingResult()
//                .getAllErrors()
//                .get(0)
//                .getDefaultMessage();
//
//        throw new BadRequestException(msg);
//    }

    @ExceptionHandler(CommonApiException.class)
    public ResponseEntity<Object> handlerCommonApiException(CommonApiException e){
        ErrorCode errorCode = e.getErrorCode();
        return handleExceptionInternal(errorCode);
    }

    @ExceptionHandler(CustomAuthException.class)
    public ResponseEntity<ResponseDTO> handleCustomAuthException(CustomAuthException e) {
        log.info("handleCustomAuthException 실행");
        return ResponseEntity.badRequest().body(ResponseDTO.of(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(CustomOAuth2AuthenticationException.class)
    public ResponseEntity<ResponseDTO> handleCustomOAuth2AuthenticationException(CustomOAuth2AuthenticationException e) {
        log.info("handleCustomOAuth2AuthenticationException 실행");
        return ResponseEntity.badRequest().body(ResponseDTO.of(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode));
    }

    private ErrorResponse makeErrorResponse(ErrorCode e) {
        return ErrorResponse.of(e.name(), e.getMessage());
    }
}


