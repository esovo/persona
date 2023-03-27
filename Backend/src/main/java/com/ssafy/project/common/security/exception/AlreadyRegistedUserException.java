package com.ssafy.project.common.security.exception;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

public class AlreadyRegistedUserException extends OAuth2AuthenticationException {

    public AlreadyRegistedUserException(String msg) { super(msg); }
}
