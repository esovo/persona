package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.BoardAddReqDTO;
import com.ssafy.project.common.db.dto.request.BoardModifyReqDTO;
import com.ssafy.project.common.db.dto.request.BoardSearchReqDTO;
import com.ssafy.project.common.db.dto.response.BoardResDTO;
import com.ssafy.project.common.db.entity.common.Board;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.entity.common.Video;
import com.ssafy.project.common.db.repository.BoardLikeRepository;
import com.ssafy.project.common.db.repository.BoardRepository;
import com.ssafy.project.common.db.repository.UserRepository;
import com.ssafy.project.common.db.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final BoardLikeRepository boardLikeRepository;

    @Override
    public Page<BoardResDTO> findAllBoard(int page, String sort) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sort).descending());
        Page<Board> boardList = boardRepository.findAll(pageable);

        Page<BoardResDTO> boardDTOList = boardList.map(board ->
            BoardResDTO.builder()
            .id(board.getId())
            .likeCnt(board.getLikeCnt())
            .videoUrl(board.getVideo().getUrl())
            .title(board.getTitle())
            .content(board.getContent())
            .viewCnt(board.getViewCnt())
            .createdDate(board.getCreatedDate())
            .nickName(board.getUser().getNickname())
            .isLike(boardLikeRepository.findByUserIdAndBoardId(board.getUser().getId(), board.getId()).isPresent())
            .build()
        );
         return boardDTOList;
    }

    @Override
    public List<BoardResDTO> findTopBoard() {

        List<Board> boardList = boardRepository.findTop4ByOrderByLikeCntDesc();

        List<BoardResDTO> boardDTOList = boardList.stream().map(board ->
                BoardResDTO.builder()
                .id(board.getId())
                .likeCnt(board.getLikeCnt())
                .videoUrl(board.getVideo().getUrl())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCnt(board.getViewCnt())
                .createdDate(board.getCreatedDate())
                .nickName(board.getUser().getNickname())
                .isLike(boardLikeRepository.findByUserIdAndBoardId(board.getUser().getId(), board.getId()).isPresent())
                .build()).collect(Collectors.toList());

        return boardDTOList;
    }


    @Override
    public BoardResDTO detailBoard(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        if(!optionalBoard.isPresent()) throw new RuntimeException();

        Board board = optionalBoard.get();
        board.setViewCnt(board.getViewCnt()+1L);
        boardRepository.save(board);

        BoardResDTO boardResDTO = BoardResDTO.builder()
                .id(board.getId())
                .likeCnt(board.getLikeCnt())
                .videoUrl(board.getVideo().getUrl())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCnt(board.getViewCnt())
                .createdDate(board.getCreatedDate())
                .nickName(board.getUser().getNickname())
                .isLike(boardLikeRepository.findByUserIdAndBoardId(board.getUser().getId(), board.getId()).isPresent())
                .build();
        return boardResDTO;
    }

    @Override
    public Page<BoardResDTO> findByWord(BoardSearchReqDTO boardSearchReqDTO) {
        Pageable pageable = PageRequest.of(boardSearchReqDTO.getPage(), 10, Sort.by(boardSearchReqDTO.getSort()).descending());
        Page<Board> boardList = new PageImpl<>(new ArrayList<>());
        // User user = userRepository.findById(board.user_id); 디비에는 LocalDateTime으로 되어 있고 여기서는 date 타입으로 가져와서 문제

        if(boardSearchReqDTO.getColumn().equals("title"))
            boardList = boardRepository.findByTitleContaining(boardSearchReqDTO.getWord(), pageable);
        else boardList = boardRepository.findByContentContaining(boardSearchReqDTO.getWord(), pageable);

        Page<BoardResDTO> boardDTOList = boardList.map(board ->
            BoardResDTO.builder()
                    .id(board.getId())
                    .likeCnt(board.getLikeCnt())
                    .videoUrl(board.getVideo().getUrl())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .viewCnt(board.getViewCnt())
                    .createdDate(board.getCreatedDate())
                    .nickName(board.getUser().getNickname())
                    .isLike(boardLikeRepository.findByUserIdAndBoardId(board.getUser().getId(), board.getId()).isPresent())
                    .build());
        return boardDTOList;
    }

    @Override
    public void addBoard(BoardAddReqDTO boardAddReqDTO) {
        Video video = videoRepository.getById(boardAddReqDTO.getVideoId());
        User user = userRepository.getReferenceById(1L);
        //유저 아이디로 user객체 넣기 가져와서 entity에 넣기
        Board board = Board.builder()
                .video(video)
                .title(boardAddReqDTO.getTitle())
                .content(boardAddReqDTO.getContent())
                .user(user)
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
