package com.ssafy.project.api.service;

import org.springframework.data.domain.Page;

public interface BoardLikeService {

    public void addBoardLike(Long userId, Long boardId);
    public void removeBoardLike(Long userId, Long boardId);
    public boolean checkBoardLike(Long userId, Long boardId);
}
