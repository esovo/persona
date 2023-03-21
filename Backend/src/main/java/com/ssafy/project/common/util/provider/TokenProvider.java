package com.ssafy.project.common.util.provider;

import com.ssafy.project.common.security.common.UserPrincipal;
import org.springframework.security.core.Authentication;

public interface TokenProvider {

    String createToken(long id, String token);
    UserPrincipal getUserPricipalFromAuthentication(Authentication authentication);
    Long getUserIdFromToken(String token);
    boolean validateToken(String authToken);
}
