package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.CommentAddReqDTO;
import com.ssafy.project.common.db.dto.request.CommentModReqDTO;
import com.ssafy.project.common.db.dto.response.CommentDTO;
import com.ssafy.project.common.db.entity.common.Board;
import com.ssafy.project.common.db.entity.common.Comment;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.repository.BoardRepository;
import com.ssafy.project.common.db.repository.CommentRepository;
import com.ssafy.project.common.db.repository.UserRepository;
import com.ssafy.project.common.provider.AuthProvider;
import com.ssafy.project.common.security.exception.CommonApiException;
import com.ssafy.project.common.security.exception.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final AuthProvider authProvider;

    @Override
    public Page<CommentDTO> findComment(Long boardId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Comment> commentList = commentRepository.findByBoardId(boardId, pageable);
        Page<CommentDTO> commentDTOList = commentList.map(comment ->
             CommentDTO.builder()
                   .id(comment.getId())
                   .userProfile(comment.getUser().getSocialAuth().getImageUrl())
                   .nickname(comment.getUser().getNickname())
                   .content(comment.getContent())
                   .createdDate(comment.getCreatedDate())
                   .build());
        return commentDTOList;
    }

    @Override
    public Page<CommentDTO> findMyComment(int page) {
        Long userId = authProvider.getUserIdFromPrincipal();
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());
        Page<Comment> comments = commentRepository.findByUserId(userId, pageable);
        Page<CommentDTO> commentDTOS = comments.map(comment ->
                CommentDTO.builder()
                        .id(comment.getId())
                        .userProfile(comment.getUser().getSocialAuth().getImageUrl())
                        .nickname(comment.getUser().getNickname())
                        .content(comment.getContent())
                        .title(comment.getBoard().getTitle())
                        .createdDate(comment.getCreatedDate())
                        .build());
        return commentDTOS;
    }

    @Override
    public Comment addComment(CommentAddReqDTO commentAddReqDTO) {
        Board board = boardRepository.getById(commentAddReqDTO.getBoardId());
        User user = userRepository.getById(authProvider.getUserIdFromPrincipal());
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
    public void modifyComment(CommentModReqDTO commentModReqDTO) {
        Comment comment = commentRepository.findById(commentModReqDTO.getCommentId()).orElseThrow(() -> new CommonApiException(CommonErrorCode.COMMENT_NOT_FOUND));

        comment.setContent(commentModReqDTO.getContent());
        commentRepository.save(comment);
    }

    @Override
    public void removeComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommonApiException(CommonErrorCode.COMMENT_NOT_FOUND));
        commentRepository.deleteById(commentId);
    }
}
