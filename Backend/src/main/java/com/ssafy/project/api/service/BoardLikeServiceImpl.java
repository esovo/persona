package com.ssafy.project.api.service;

import com.ssafy.project.common.db.entity.common.Board;
import com.ssafy.project.common.db.entity.common.BoardLike;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.repository.BoardLikeRepository;
import com.ssafy.project.common.db.repository.BoardRepository;
import com.ssafy.project.common.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardLikeServiceImpl implements BoardLikeService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardLikeRepository boardLikeRepository;

    @Override
    public void addBoardLike(Long user_id, Long board_id) {
        Board board = boardRepository.findById(board_id).orElseThrow(()-> new RuntimeException());
        User user = userRepository.findById(user_id).orElseThrow(()-> new RuntimeException());

        if(boardLikeRepository.findByUserIdAndBoardId(user_id, board_id).isPresent()) throw new RuntimeException();

        BoardLike boardLike = BoardLike.builder()
                .board(board)
                .user(user)
                .build();

        board.getBoardLikes().add(boardLike);
        boardRepository.save(board);
    }

    @Override
    public void removeBoardLike(Long user_id, Long board_id) {
        Board board = boardRepository.findById(board_id).orElseThrow(()-> new RuntimeException());
        User user = userRepository.findById(user_id).orElseThrow(()-> new RuntimeException());
        BoardLike boardLike = boardLikeRepository.findByUserIdAndBoardId(user_id, board_id).orElseThrow(()->new RuntimeException());

        board.getBoardLikes().remove(boardLike);
        boardRepository.save(board);
    }

    @Override
    public boolean checkBoardLike(Long userId, Long boardId) {
        return boardLikeRepository.existsBoardLikeByUserIdAndBoardId(userId, boardId);
    }
}
