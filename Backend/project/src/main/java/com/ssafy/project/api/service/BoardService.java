package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.BoardAddReqDTO;
import com.ssafy.project.common.db.dto.request.BoardModifyReqDTO;
import com.ssafy.project.common.db.dto.request.BoardSearchReqDTO;
import com.ssafy.project.common.db.dto.response.BoardAllResDTO;
import com.ssafy.project.common.db.dto.response.BoardResDTO;
import com.ssafy.project.common.db.entity.common.Board;

import java.util.List;

public interface BoardService {

    //조회
    List<BoardAllResDTO> findAllBoard(int page);
    BoardResDTO detailBoard(Long boardId);
    //검색
    List<BoardAllResDTO> findByWord(BoardSearchReqDTO boardSearchReqDTO);
    //등록
    void addBoard(BoardAddReqDTO boardAddReqDTO);
    //수정
    Board modifyBoard(BoardModifyReqDTO boardModifyReqDTO);
    //삭제
   void removeBoard(Long id);
}
