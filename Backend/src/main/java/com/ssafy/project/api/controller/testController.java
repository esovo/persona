package com.ssafy.project.api.controller;

import com.ssafy.project.api.service.VideoService;
import com.ssafy.project.common.db.dto.request.VideoCreateReqDTO;
import com.ssafy.project.common.util.ResponseDTO;
import com.ssafy.project.common.util.provider.S3Provider;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@RestController
@RequiredArgsConstructor
public class testController {

    VideoService videoService;
    @Autowired
    S3Provider s3Provider;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> hi(@RequestParam("file") MultipartFile file) throws IOException {


        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "ok"));
    }

}
