package com.ssafy.project.api.controller;

import com.ssafy.project.api.service.BookmarkService;
import com.ssafy.project.common.util.Msg;
import com.ssafy.project.common.util.ResponseDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@Api(tags = {"대본 북마크 API"})
@RequiredArgsConstructor
@RequestMapping(value = "/bookmark")
public class ScriptBookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<ResponseDTO> bookmarkAdd(@RequestParam Long scriptId) {
        // userId 가져와서 넣기
        Long userId = 1L;
        bookmarkService.AddBookmark(userId, scriptId);
       return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_CREATE));
    }



}
