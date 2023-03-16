package com.ssafy.project.common.security.exception;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

public class NoEmailProvidedException extends OAuth2AuthenticationException {

    public NoEmailProvidedException(String msg) { super(msg); }
}
