package com.ssafy.project.api.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@Api(tags = {"대본 API"})
@RequiredArgsConstructor
@RequestMapping(value = "/script")
public class ScriptController {

    //대본 조회
    //대본 등록
    //대본 수정
    //대본 삭제
    //내 대본 보기
}
