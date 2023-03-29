package com.ssafy.project.api.service;

import org.springframework.data.domain.Page;

public interface BoardLikeService {

    public void addBoardLike(Long user_id, Long board_id);
    public void removeBoardLike(Long user_id, Long board_id);
}
