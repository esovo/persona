package com.ssafy.project.api.controller;

import com.ssafy.project.common.util.Msg;
import com.ssafy.project.common.util.ResponseDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@Api(tags = {"대본 API"})
@RequiredArgsConstructor
@RequestMapping(value = "/script")
public class ScriptController {

    //대본 조회
//    public ResponseEntity<ResponseDTO> scriptList(){
//        Page<ScriptDTO> scripts = scriptService.findAllScript(page, sort);
//        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_SCRIPT_READ, scripts));
//    }

    //내가 참여한 대본
}
