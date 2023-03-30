package com.ssafy.project.api.service;

import com.ssafy.project.common.db.entity.common.Board;
import com.ssafy.project.common.db.entity.common.BoardLike;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.repository.BoardLikeRepository;
import com.ssafy.project.common.db.repository.BoardRepository;
import com.ssafy.project.common.db.repository.UserRepository;
import com.ssafy.project.common.provider.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardLikeServiceImpl implements BoardLikeService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final AuthProvider authProvider;

    @Override
    public void addBoardLike(Long boardId) {
        Long userId = authProvider.getUserIdFromPrincipal();
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new RuntimeException());
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException());

        if(boardLikeRepository.findByUserIdAndBoardId(boardId, boardId).isPresent()) throw new RuntimeException();

        BoardLike boardLike = BoardLike.builder()
                .board(board)
                .user(user)
                .build();

        board.getBoardLikes().add(boardLike);
        boardRepository.save(board);
    }

    @Override
    public void removeBoardLike(Long boardId) {
        Long userId = authProvider.getUserIdFromPrincipal();
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new RuntimeException());
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException());
        BoardLike boardLike = boardLikeRepository.findByUserIdAndBoardId(userId, boardId).orElseThrow(()->new RuntimeException());

        boardLikeRepository.deleteByUserIdAndBoardId(userId, boardId);
    }

    @Override
    public boolean checkBoardLike(Long boardId) {
        Long userId = authProvider.getUserIdFromPrincipal();
        return boardLikeRepository.existsBoardLikeByUserIdAndBoardId(userId, boardId);
    }
}
