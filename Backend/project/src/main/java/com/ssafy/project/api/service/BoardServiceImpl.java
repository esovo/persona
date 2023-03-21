package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.BoardAddReqDTO;
import com.ssafy.project.common.db.dto.request.BoardModifyReqDTO;
import com.ssafy.project.common.db.dto.request.BoardSearchReqDTO;
import com.ssafy.project.common.db.dto.response.BoardAllResDTO;
import com.ssafy.project.common.db.dto.response.BoardResDTO;
import com.ssafy.project.common.db.dto.response.CommentDTO;
import com.ssafy.project.common.db.entity.common.Board;
import com.ssafy.project.common.db.entity.common.Video;
import com.ssafy.project.common.db.repository.BoardRepository;
import com.ssafy.project.common.db.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final VideoRepository videoRepository;

    @Override
    public List<BoardAllResDTO> findAllBoard(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Board> boardList = boardRepository.findAll(pageable);

        // User user = userRepository.findById(board.user_id); 디비에는 LocalDateTime으로 되어 있고 여기서는 date 타입으로 가져와서 문제
        List<BoardAllResDTO> boardDTOList = new ArrayList<>();
        boardList.forEach(board -> {
//            System.out.println(board.getUser());
            
            BoardAllResDTO boardAllResDTO = BoardAllResDTO.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .viewCnt(board.getViewCnt())
                    .likes(board.getBoardLikes().size())
                    .createdDate(board.getCreatedDate())
//                    .nickName(board.getUser().getNickname())
                    .build();
            boardDTOList.add(boardAllResDTO);
        });
         return boardDTOList;
    }

    @Override
    public BoardResDTO detailBoard(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        if(!optionalBoard.isPresent()) throw new RuntimeException();

        Board board = optionalBoard.get();
        List<CommentDTO> commentDTOList = new ArrayList<>();

        board.getComments().forEach( comment -> {
            CommentDTO commentDTO = CommentDTO.builder()
                    .id(comment.getId())
//                    .userProfile(comment.getUserProfile)
//                    .nickname(comment.getNickname)
                    .content(comment.getContent())
                    .commentLikes(comment.getCommentLikes().size())
                    .createdDate(comment.getCreatedDate())
                    .build();
            commentDTOList.add(commentDTO);
        });

        BoardResDTO boardResDTO = BoardResDTO.builder()
                .id(board.getId())
                .likes(board.getBoardLikes().size())
                .videoUrl(board.getVideo().getUrl())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCnt(board.getViewCnt())
                .createdDate(board.getCreatedDate())
                .nickName(board.getUser().getNickname())
                .commentDTOList(commentDTOList)
                .build();
        return boardResDTO;
    }

    @Override
    public List<BoardAllResDTO> findByWord(BoardSearchReqDTO boardSearchReqDTO) {
        Pageable pageable = PageRequest.of(boardSearchReqDTO.getPage(), 10);
        List<BoardAllResDTO> boardDTOList = new ArrayList<>();
        Page<Board> boardList = new PageImpl<>(new ArrayList<>());
        // User user = userRepository.findById(board.user_id); 디비에는 LocalDateTime으로 되어 있고 여기서는 date 타입으로 가져와서 문제

        if(boardSearchReqDTO.getColumn().equals("title"))
            boardList = boardRepository.findByTitleContaining(boardSearchReqDTO.getWord(), pageable);
        else boardList = boardRepository.findByContentContaining(boardSearchReqDTO.getWord(), pageable);

        boardList.forEach(board -> {
//            System.out.println(board.getUser());

            BoardAllResDTO boardDTO = BoardAllResDTO.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .viewCnt(board.getViewCnt())
                    .likes(board.getBoardLikes().size())
                    .createdDate(board.getCreatedDate())
//                    .nickName(board.getUser().getNickname())
                    .build();
            boardDTOList.add(boardDTO);
        });
        return boardDTOList;
    }

    @Override
    public void addBoard(BoardAddReqDTO boardAddReqDTO) {
        Video video = videoRepository.getById(boardAddReqDTO.getVideoId());

        //유저 아이디로 user객체 넣기 가져와서 entity에 넣기
        Board board = Board.builder()
                .video(video)
                .title(boardAddReqDTO.getTitle())
                .content(boardAddReqDTO.getContent())
//                .user()
                .build();
        boardRepository.save(board);
    }

    @Override
    public Board modifyBoard(BoardModifyReqDTO boardModifyReqDTO) {
        Optional<Board> optionalBoard = boardRepository.findById(boardModifyReqDTO.getBoardId());

        if(!optionalBoard.isPresent()) throw new RuntimeException();

        Board board = optionalBoard.get();
        board.setContent(boardModifyReqDTO.getContent());
        board.setTitle(boardModifyReqDTO.getTitle());
        boardRepository.save(board);

        return board;
    }

    @Override
    public void removeBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
