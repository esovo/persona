package com.ssafy.project.api.controller;

import com.ssafy.project.api.service.ScriptService;
import com.ssafy.project.common.db.dto.request.ScriptSearchReqDTO;
import com.ssafy.project.common.db.dto.response.ScriptDTO;
import com.ssafy.project.common.db.entity.common.Script;
import com.ssafy.project.common.util.Msg;
import com.ssafy.project.common.util.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(tags = {"대본 API"})
@RequiredArgsConstructor
@RequestMapping(value = "/script")
public class ScriptController {

    private final ScriptService scriptService;

    //대본 조회
    @PutMapping("/all")
    @ApiOperation(value = "전체 게시물 조회")
    public ResponseEntity<ResponseDTO> scriptList(@RequestBody ScriptSearchReqDTO scriptSearchReqDTO){
        Page<ScriptDTO> scripts = scriptService.findAllScript(scriptSearchReqDTO);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_READ, scripts));
    }

    //내가 참여한 대본 보기(영상, 참여 정보 다 가져와야 함)
}
