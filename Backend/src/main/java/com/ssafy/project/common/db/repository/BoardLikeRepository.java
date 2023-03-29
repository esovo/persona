package com.ssafy.project.common.db.repository;

import com.ssafy.project.common.db.entity.common.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    //좋아요 눌렀는지 확인
    public Optional<BoardLike> findByUserIdAndBoardId(Long user_id, Long board_id);
}
