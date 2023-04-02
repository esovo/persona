package com.ssafy.project.api.controller;

import com.ssafy.project.api.service.VideoService;
import com.ssafy.project.common.db.dto.common.ResponseDTO;
import com.ssafy.project.common.provider.S3Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
public class TestController {

    VideoService videoService;
    @Autowired
    S3Provider s3Provider;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> hi(@RequestParam("json") Map<String, String> json) throws IOException {

        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "ok"));
    }

}
