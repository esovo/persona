package com.ssafy.project.api.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@Api(tags = {"댓글 API"})
@RequiredArgsConstructor
@RequestMapping(value = "/comment")
public class CommentController {

    //조회
    //등록
    //수정
    //삭제

}
