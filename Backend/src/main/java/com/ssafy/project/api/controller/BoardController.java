package com.ssafy.project.api.controller;

import com.ssafy.project.api.service.BoardService;
import com.ssafy.project.common.db.dto.request.BoardAddReqDTO;
import com.ssafy.project.common.db.dto.request.BoardModifyReqDTO;
import com.ssafy.project.common.db.dto.request.BoardSearchReqDTO;
import com.ssafy.project.common.db.dto.response.BoardAllResDTO;
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
@Api(tags = {"게시판 API"})
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {

    private final BoardService boardService;

    //조회
    @GetMapping("/all")
    @ApiOperation(value="전체 게시물 조회")
    public ResponseEntity<ResponseDTO> boardAllList(@RequestParam int page, String sort, String keyword){
        Page<BoardAllResDTO> boards = boardService.findAllBoard(page, sort, keyword);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_READ, boards));
    }

    @GetMapping("/top")
    @ApiOperation(value = "인기게시글 조회")
    public ResponseEntity<ResponseDTO> boardTopList(){
        List<BoardAllResDTO> boards = boardService.findTopBoard();
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_READ, boards));
    }

    @GetMapping("my")
    @ApiOperation(value = "내 게시물 조회")
    public ResponseEntity<ResponseDTO> boardMyList(@RequestParam int page){
        Page<BoardAllResDTO> boards = boardService.findMyBoard(page);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_READ, boards));
    }

    @GetMapping("/detail")
    @ApiOperation(value="게시글 상세 조회")
    public ResponseEntity<ResponseDTO> boardDetail(@RequestParam Long boardId){
        BoardAllResDTO boardResDTO = boardService.detailBoard(boardId);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_READ, boardResDTO));
    }

    //등록
    @PostMapping
    @ApiOperation(value = "게시글 등록")
    public ResponseEntity<ResponseDTO> boardAdd(@RequestBody BoardAddReqDTO boardAddReqDTO){
        boardService.addBoard(boardAddReqDTO);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_CREATE));
    }

    //삭제
    @DeleteMapping
    @ApiOperation(value = "게시글 삭제")
    public ResponseEntity<ResponseDTO> boardRemove(@RequestParam("id")Long id) {
        boardService.removeBoard(id);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_DELETE));
    }

    //수정
    @PutMapping
    @ApiOperation(value = "게시글 수정")
    public ResponseEntity<ResponseDTO> boardModify(@RequestBody BoardModifyReqDTO boardModifyReqDTO){
            boardService.modifyBoard(boardModifyReqDTO);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_UPDATE));
    }
}
