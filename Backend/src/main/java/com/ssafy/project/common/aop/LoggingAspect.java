//package com.ssafy.project.common.aop;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component
//@Aspect
//@Slf4j
//public class LoggingAspect {
//
//    @Before(value="execution(* com.ssafy.project.api..*.*(..))")
//    public void logging(JoinPoint jp) {
//
//        log.info("=== 메서드 선언부: {}", jp.getSignature());
//        log.info("=== 전달 파라미터: {}", Arrays.toString(jp.getArgs()));
//    }
//}
