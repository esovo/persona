package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.CommentAddReqDTO;
import com.ssafy.project.common.db.dto.response.CommentDTO;
import com.ssafy.project.common.db.entity.common.Board;
import com.ssafy.project.common.db.entity.common.Comment;
import com.ssafy.project.common.db.repository.BoardRepository;
import com.ssafy.project.common.db.repository.CommentRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

//    @Override
//    public List<CommentDTO> findComment(Long boardId) {
//
//        List<Comment> commentList = commentRepository.findByBoardId(boardId);
//        List<CommentDTO> commentDTOList = new ArrayList<>();
//
//       commentList.forEach(comment -> {
//           CommentDTO commentDTO = CommentDTO.builder()
//                   .id(comment.getId())
////                   .userProfile(comment.getUser().getUserProfile())
//                   .nickname(comment.getUser().getNickname())
//                   .content(comment.getContent())
//                   .commentLikes(comment.getCommentLikes().size())
//                   .createdDate(comment.getCreatedDate())
//                   .build();
//           commentDTOList.add(commentDTO);
//       });
//        return commentDTOList;
//    }

    @Override
    public Comment addComment(CommentAddReqDTO commentAddReqDTO) {
        Board board = boardRepository.getById(commentAddReqDTO.getBoardId());

        Comment comment = Comment.builder()
                .board(board)
//                .user(user)
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
