package com.ssafy.project.common.security.handler;

import com.ssafy.project.common.security.exception.BadRequestException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// 모든 컨트롤러의 익셉션을 핸들링하는 클래스
// @RestControllerAdvice => 객체도 함께 리턴 가능
@ControllerAdvice
public class CustomExceptionHandler {

    // @NotNull, @NotBlank 등의 발리데이션 익셉션 발생 시 핸들링하는 메소드
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        throw new BadRequestException(msg);
    }
}
