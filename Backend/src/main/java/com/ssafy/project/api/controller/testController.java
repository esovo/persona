package com.ssafy.project.api.controller;

import com.ssafy.project.common.util.provider.AuthProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Controller
public class testController {


    @Autowired
    AuthProvider authProvider;

    @Value("${oauth2.google.id}")
    private String clientId;

    @Value("${oauth2.google.secret}")
    private String clientSecret;

    //    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri = "http://localhost:8080/oauth2/callback/google";

    @RequestMapping(value = "/auth/account/googlesigncallback", method = RequestMethod.GET)
    public String hi(HttpServletRequest request, HttpServletResponse response) {
        log.info("test ======================================== : {}", authProvider.getUserIdFromPrincipal());

        return "hi";
    }

}
