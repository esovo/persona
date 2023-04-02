package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.BoardAddReqDTO;
import com.ssafy.project.common.db.dto.request.BoardModifyReqDTO;
import com.ssafy.project.common.db.dto.response.BoardAllResDTO;
import com.ssafy.project.common.db.entity.common.Board;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.entity.common.Video;
import com.ssafy.project.common.db.repository.BoardRepository;
import com.ssafy.project.common.db.repository.UserRepository;
import com.ssafy.project.common.db.repository.VideoRepository;
import com.ssafy.project.common.provider.AuthProvider;
import com.ssafy.project.common.security.exception.CommonApiException;
import com.ssafy.project.common.security.exception.CommonErrorCode;
import com.ssafy.project.common.security.exception.CustomAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final AuthProvider authProvider;

    @Override
    public Page<BoardAllResDTO> findAllBoard(int page, String sort, String keyword) {
        Page<BoardAllResDTO> boards = boardRepository.findAllWithFilter(page, sort, keyword);
         return boards;
    }

    @Override
    public List<BoardAllResDTO> findTopBoard() {
        return boardRepository.findTop3Board();
    }

    @Override
    public Page<BoardAllResDTO> findMyBoard(int page) {
        Long userId = authProvider.getUserIdFromPrincipal();
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());
        Page<Board> boards = boardRepository.findByUserId(userId, pageable);
        Page<BoardAllResDTO> boardAllResDTOS = boards.map(board ->
                BoardAllResDTO.builder()
                .id(board.getId())
                .nickName(board.getUser().getNickname())
                .createdDate(board.getCreatedDate())
                .title(board.getTitle())
                .content(board.getContent())
                .likeCnt(board.getBoardLikes().size())
                .commentCnt(board.getComments().size())
                .build());
        return boardAllResDTOS;
    }

    @Override
    public BoardAllResDTO detailBoard(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        if(!optionalBoard.isPresent()) throw new CommonApiException(CommonErrorCode.BOARD_NOT_FOUND);

        Board board = optionalBoard.get();
        board.setViewCnt(board.getViewCnt()+1L);
        boardRepository.save(board);

        BoardAllResDTO boardResDTO = BoardAllResDTO.builder()
                .id(board.getId())
                .nickName(board.getUser().getNickname())
                .createdDate(board.getCreatedDate())
                .title(board.getTitle())
                .content(board.getContent())
                .likeCnt(board.getBoardLikes().size())
                .commentCnt(board.getComments().size())
                .build();
        return boardResDTO;
    }
    @Override
    public void addBoard(BoardAddReqDTO boardAddReqDTO) {
        Video video = null;
        if(boardAddReqDTO.getVideoId() != null)
        video = videoRepository.findById(boardAddReqDTO.getVideoId()).orElseThrow(() -> new CommonApiException(CommonErrorCode.VIDEO_NOT_FOUND));
        User user = userRepository.findById(authProvider.getUserIdFromPrincipal()).orElseThrow(() -> new CommonApiException(CommonErrorCode.USER_NOT_FOUND));

        Board board = Board.builder()
                .video(video)
                .title(boardAddReqDTO.getTitle())
                .content(boardAddReqDTO.getContent())
                .user(user)
                .build();

        user.getBoards().add(board);
        userRepository.save(user);
    }

    @Override
    public void modifyBoard(BoardModifyReqDTO boardModifyReqDTO) {
        Board board = boardRepository.findById(boardModifyReqDTO.getBoardId()).orElseThrow(() -> new CommonApiException(CommonErrorCode.BOARD_NOT_FOUND));

        board.setContent(boardModifyReqDTO.getContent());
        board.setTitle(boardModifyReqDTO.getTitle());
        boardRepository.save(board);
    }

    @Override
    public void removeBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CommonApiException(CommonErrorCode.BOARD_NOT_FOUND));
        boardRepository.deleteById(id);
    }
}
