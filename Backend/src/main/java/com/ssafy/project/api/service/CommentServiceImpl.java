package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.CommentAddReqDTO;
import com.ssafy.project.common.db.dto.response.CommentDTO;
import com.ssafy.project.common.db.entity.common.Board;
import com.ssafy.project.common.db.entity.common.Comment;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.repository.BoardRepository;
import com.ssafy.project.common.db.repository.CommentRepository;
import com.ssafy.project.common.db.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    public Page<CommentDTO> findComment(Long boardId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Comment> commentList = commentRepository.findByBoardId(boardId, pageable);
        Page<CommentDTO> commentDTOList = commentList.map(comment ->
             CommentDTO.builder()
                   .id(comment.getId())
//                   .userProfile(comment.getUser().getUserProfile())
                   .nickname(comment.getUser().getNickname())
                   .content(comment.getContent())
                   .commentLikes(comment.getCommentLikes().size())
                   .createdDate(comment.getCreatedDate())
                   .build());
        return commentDTOList;
    }

    @Override
    public Comment addComment(CommentAddReqDTO commentAddReqDTO) {
        Board board = boardRepository.getById(commentAddReqDTO.getBoardId());
        User user = userRepository.getById(1L);
        Comment comment = Comment.builder()
                .board(board)
                .user(user)
                .content(commentAddReqDTO.getContent())
                .build();

        board.getComments().add(comment);
        boardRepository.save(board);
        return comment;
    }

    @Override
    public void modifyComment(Long id, String content) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if(!optionalComment.isPresent()) throw new RuntimeException();

        Comment comment = optionalComment.get();
        comment.setContent(content);
        commentRepository.save(comment);
    }

    @Override
    public void removeComment(Long id) {
        commentRepository.deleteById(id);
    }
}
