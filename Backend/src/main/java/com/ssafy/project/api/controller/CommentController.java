package com.ssafy.project.api.controller;

import com.ssafy.project.api.service.CommentService;
import com.ssafy.project.common.db.dto.request.CommentAddReqDTO;
import com.ssafy.project.common.db.dto.response.CommentDTO;
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

import java.util.List;

@Log4j2
@RestController
@Api(tags = {"댓글 API"})
@RequiredArgsConstructor
@RequestMapping(value = "/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    @ApiOperation(value="전체 댓글 조회")
    public ResponseEntity<ResponseDTO> commentList(@RequestParam Long boardId, @RequestParam int page) {
        Page<CommentDTO> comments = commentService.findComment(boardId, page);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_READ, comments));
    }

    //등록
    @PostMapping
    @ApiOperation(value="댓글 등록")
    public ResponseEntity<ResponseDTO> commentAdd(@RequestBody CommentAddReqDTO commentAddReqDTO){
        commentService.addComment(commentAddReqDTO);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_CREATE));
    }

    //수정
    @PutMapping
    @ApiOperation(value="댓글 수정")
    public ResponseEntity<ResponseDTO> commentModify(@RequestParam Long commentId, @RequestParam String content){
        commentService.modifyComment(commentId, content);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_UPDATE));
    }

    //삭제
    @DeleteMapping
    @ApiOperation(value="댓글 삭제")
    public ResponseEntity<ResponseDTO> commentRemove(@RequestParam Long commentId){
        commentService.removeComment(commentId);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_DELETE));
    }


}
