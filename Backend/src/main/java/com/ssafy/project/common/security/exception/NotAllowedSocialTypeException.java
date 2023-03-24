package com.ssafy.project.common.security.exception;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

public class NotAllowedSocialTypeException extends OAuth2AuthenticationException {

    public NotAllowedSocialTypeException(String msg) {
        super(msg);
    }
}