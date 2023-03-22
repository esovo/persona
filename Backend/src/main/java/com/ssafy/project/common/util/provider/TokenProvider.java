package com.ssafy.project.common.util.provider;

import org.springframework.security.core.Authentication;

import java.security.Principal;

public interface TokenProvider {

    String createToken(long id, String token);
    Principal getPricipalFromAuthentication(Authentication authentication);
    Long getUserIdFromToken(String token);
    boolean validateToken(String authToken);
}
