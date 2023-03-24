package com.ssafy.project.common.db.repository;

import com.ssafy.project.common.db.entity.common.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    //인기글 조회
    List<Board> findTop4ByOrderByLikeCntDesc();
    //검색
    Page<Board> findByTitleContaining(String title, Pageable pageable);
    Page<Board> findByContentContaining(String content, Pageable pageable);
    //상세조회
    Optional<Board> findById(Long id);
    //삭제
    void deleteById(Long id);
}
