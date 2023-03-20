package com.ssafy.project.api.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Log4j2
@Controller
public class testController {


    @Value("${oauth2.google.id}")
    private String clientId;

    @Value("${oauth2.google.secret}")
    private String clientSecret;

    //    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri = "http://localhost:8080/oauth2/callback/google";

    @RequestMapping(value = "/auth/account/googlesigncallback", method = RequestMethod.GET)
    public void GoogleSignCallback(HttpServletRequest request, HttpServletResponse response) {
        try {
            String authorizationCode = request.getParameter("code");
            log.info("authorizationCode : {}", authorizationCode);
//
//        getRedirectStrategy().sendRedirect(request, response, targetUrl);

            HttpHeaders headers = new HttpHeaders();
            RestTemplate restTemplate = new RestTemplate();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

            log.info(authorizationCode);
            log.info(clientId);
            log.info(clientSecret);
            log.info(redirectUri);
            parameters.add("code", authorizationCode);
            parameters.add("client_id", clientId);
            parameters.add("client_secret", clientSecret);
            parameters.add("redirect_uri", redirectUri);
            parameters.add("grant_type", "authorization_code");

            HttpEntity<MultiValueMap<String,String>> rest_request = new HttpEntity<>(parameters,headers);

            URI uri = URI.create("https://oauth2.googleapis.com/token");

            ResponseEntity<String> restReponse = restTemplate.postForEntity(uri, rest_request, String.class);
            System.out.println(restReponse.getBody());

            return;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
