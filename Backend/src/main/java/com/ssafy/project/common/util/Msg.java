package com.ssafy.project.common.util;

public interface Msg {

    //유저
    final String SUCCESS_USER_LOGOUT="로그아웃에 성공했습니다.";
    final String SUCCESS_USER_SEARCH="유저 조회에 성공했습니다.";

    //게시글
    final String SUCCESS_BOARD_READ="게시글 조회에 성공했습니다.";
    final String SUCCESS_BOARD_SEARCH="게시글 검색에 성공했습니다.";
    final String SUCCESS_BOARD_CREATE="게시글 등록에 성공했습니다.";
    final String SUCCESS_BOARD_DELETE="게시글 삭제에 성공했습니다.";

    //댓글
    final String SUCCESS_COMMENT_READ="댓글 조회에 성공했습니다";
    final String SUCCESS_COMMENT_CREATE="댓글 등록에 성공했습니다.";
    final String SUCCESS_COMMENT_UPDATE="댓글 수정에 성공했습니다.";
    final String SUCCESS_COMMENT_DELETE="댓글 삭제에 성공했습니다.";


    //대본
    final String SUCCESS_SCRIPT_READ="대본 조회에 성공했습니다.";
}
