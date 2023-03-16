package com.ssafy.project.api.controller;

import com.ssafy.project.api.service.BoardService;
import com.ssafy.project.common.db.dto.request.BoardAddReqDTO;
import com.ssafy.project.common.db.dto.request.BoardModifyReqDTO;
import com.ssafy.project.common.db.dto.request.BoardSearchReqDTO;
import com.ssafy.project.common.db.dto.response.BoardDTO;
import com.ssafy.project.common.db.entity.common.Board;
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
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<ResponseDTO>  boardList(@RequestParam("page") int page){
        List<BoardDTO> boards = boardService.findAllBoard(page);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCES_BOARD_READ, boards));
    }

    //검색
    @PostMapping("/search")
    @ApiOperation(value = "게시글 검색")
    public ResponseEntity<ResponseDTO> boardSearch(@RequestBody BoardSearchReqDTO boardSearchReqDTO){
        Page<Board> boardList = boardService.findByWord(boardSearchReqDTO);

        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "검색을 완료했습니다.", boardList));
    }

    //등록
    @PostMapping
    @ApiOperation(value = "게시글 등록")
    public ResponseEntity<ResponseDTO> boardAdd(@RequestBody BoardAddReqDTO boardAddReqDTO){
        //파일 서버에 저장하는 프로세스
        boardService.addBoard(boardAddReqDTO);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "게시글 등록에 성공했습니다."));
    }

    //삭제
    @DeleteMapping
    @ApiOperation(value = "게시글 삭제")
    public ResponseEntity<ResponseDTO> boardRemove(@RequestParam("id")Long id) {
        boardService.removeBoard(id);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "게시글이 삭제되었습니다."));
    }

    //수정
    @PutMapping
    @ApiOperation(value = "게시글 수정")
    public ResponseEntity<ResponseDTO> boardModify(@RequestBody BoardModifyReqDTO boardModifyReqDTO){
            boardService.modifyBoard(boardModifyReqDTO);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "게시글이 수정되었습니다."));
    }


}
