package com.ssafy.project.api.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class testController {
    @GetMapping
    public ResponseEntity<?> testApi() {
        log.info("Log4j2 정상 작동");

//        return new ResponseEntity<Void>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
