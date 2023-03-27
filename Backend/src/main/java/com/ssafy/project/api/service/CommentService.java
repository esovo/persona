package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.CommentAddReqDTO;
import com.ssafy.project.common.db.dto.response.CommentDTO;
import com.ssafy.project.common.db.entity.common.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

    //조회
    public Page<CommentDTO> findComment(Long boardId, int page);
    //등록
    public Comment addComment(CommentAddReqDTO commentAddReqDTO);
    //수정
    public void modifyComment(Long id, String content);
    //삭제
    public void removeComment(Long id);
}
