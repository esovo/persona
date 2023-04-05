package com.ssafy.project.api.controller;

import com.ssafy.project.api.service.VideoService;
import com.ssafy.project.common.db.dto.common.ResponseDTO;
import com.ssafy.project.common.provider.S3Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Log4j2
@RestController
@RequiredArgsConstructor
public class TestController {

    VideoService videoService;

    S3Provider s3Provider;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> hi(HttpServletRequest request) throws IOException {

        log.info(request.getHeader("Authorization"));
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "ok"));
    }

    private final ResourceLoader resourceLoader;

    @PostMapping("/file")
    public void getFile(@RequestPart MultipartFile file) throws IOException {


        log.info(file.getOriginalFilename());
        log.info(file.getName());

        File newFile = new File(file.getOriginalFilename());

        log.info(newFile.getAbsolutePath());

        Path path = Paths.get("test/a/b/c");

        Files.move(newFile.toPath(), path.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);

        log.info(newFile.getAbsolutePath());
    }

}
