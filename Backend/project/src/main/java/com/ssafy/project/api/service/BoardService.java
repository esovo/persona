package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.BoardAddReqDTO;
import com.ssafy.project.common.db.dto.request.BoardModifyReqDTO;
import com.ssafy.project.common.db.dto.request.BoardSearchReqDTO;
import com.ssafy.project.common.db.dto.response.BoardDTO;
import com.ssafy.project.common.db.entity.common.Board;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {

    //조회
    List<BoardDTO> findAllBoard(int page);
    //검색
    List<BoardDTO> findByWord(BoardSearchReqDTO boardSearchReqDTO);
    //등록
    void addBoard(BoardAddReqDTO boardAddReqDTO);
    //수정
    Board modifyBoard(BoardModifyReqDTO boardModifyReqDTO);
    //삭제
   void removeBoard(Long id);
}
