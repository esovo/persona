package com.ssafy.project.common.util.provider;

import com.ssafy.project.common.security.common.UserPrincipal;
import org.springframework.security.core.Authentication;

public interface AuthProvider {

    UserPrincipal getUserPricipalFromAuthentication(Authentication authentication);

    long getUserIdFromPrincipal();

}
