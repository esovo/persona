package com.ssafy.project.api.controller;

import com.ssafy.project.api.service.BoardLikeService;
import com.ssafy.project.common.util.Msg;
import com.ssafy.project.common.util.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@Api(tags = {"게시판 좋아요 API"})
@RequestMapping("/board_like")
public class BoardLikeController {

    private final BoardLikeService boardLikeService;

    //좋아요
    @PostMapping("/like")
    @ApiOperation(value = "게시글 좋아요")
    public ResponseEntity<ResponseDTO> boardLikeAdd(@RequestParam Long board_id){
        //유저정보 가져와서 안에 파라미터로 넘겨줘야 함.
        boardLikeService.addBoardLike(1L, board_id);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_CREATE));
    }

    //좋아요 취소
    @DeleteMapping
    @ApiOperation(value = "게시글 좋아요 취소")
    public ResponseEntity<ResponseDTO> boardLikeRemove(@RequestParam Long board_id){
        //유저정보 가져와서 안에 파라미터로 넘겨줘야 함.
        boardLikeService.removeBoardLike(1L, board_id);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_DELETE));
    }

}
