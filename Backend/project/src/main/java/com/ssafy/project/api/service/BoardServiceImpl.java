package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.BoardAddReqDTO;
import com.ssafy.project.common.db.dto.request.BoardModifyReqDTO;
import com.ssafy.project.common.db.dto.request.BoardSearchReqDTO;
import com.ssafy.project.common.db.dto.response.BoardDTO;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final VideoRepository videoRepository;

    @Override
    public List<BoardDTO> findAllBoard(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Board> boardList = boardRepository.findAll(pageable);

        // User user = userRepository.findById(board.user_id); 디비에는 LocalDateTime으로 되어 있고 여기서는 date 타입으로 가져와서 문제

        List<BoardDTO> boardDTOList = new ArrayList<>();
        boardList.forEach(board -> {
//            System.out.println(board.getUser());
            
            BoardDTO boardDTO = BoardDTO.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .viewCnt(board.getViewCnt())
                    .likes(board.getBoardLikes().size())
                    .registerDate(board.getRegisterDate())
                    .videoUrl(board.getVideo().getUrl())
//                    .userId(board.getUser().getId())
//                    .nickName(board.getUser().getNickname())
                    .build();
            boardDTOList.add(boardDTO);
        });
         return boardDTOList;
    }

    @Override
    public List<BoardDTO> findByWord(BoardSearchReqDTO boardSearchReqDTO) {
        Pageable pageable = PageRequest.of(boardSearchReqDTO.getPage(), 10);
        List<BoardDTO> boardDTOList = new ArrayList<>();
        Page<Board> boardList = new PageImpl<>(new ArrayList<>());
        // User user = userRepository.findById(board.user_id); 디비에는 LocalDateTime으로 되어 있고 여기서는 date 타입으로 가져와서 문제

        if(boardSearchReqDTO.getColumn().equals("title"))
            boardList = boardRepository.findByTitleContaining(boardSearchReqDTO.getWord(), pageable);
        else boardList = boardRepository.findByContentContaining(boardSearchReqDTO.getWord(), pageable);

        boardList.forEach(board -> {
//            System.out.println(board.getUser());

            BoardDTO boardDTO = BoardDTO.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .viewCnt(board.getViewCnt())
                    .likes(board.getBoardLikes().size())
                    .registerDate(board.getRegisterDate())
                    .videoUrl(board.getVideo().getUrl())
//                    .userId(board.getUser().getId())
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
                .registerDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
//                .user()
                .build();
        boardRepository.save(board);
    }

    @Override
    public Board modifyBoard(BoardModifyReqDTO boardModifyReqDTO) {

        Optional<Board> originBoard = boardRepository.findById(boardModifyReqDTO.getBoardId());
        boardRepository.deleteById(boardModifyReqDTO.getBoardId());

        Board board = Board.builder()
                .updateDate(LocalDateTime.now())
                .title(originBoard.get().getTitle())
                .content(originBoard.get().getContent())
                .build();
        //여기 하다 말았음
        return null;
    }

    @Override
    public void removeBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
