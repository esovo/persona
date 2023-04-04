export const communityApis = {
  BOARD_POST_API: '/app/board',
  BOARD_GET_API: (boardId) => {
    return `/app/board/detail?boardId=${boardId}`;
  },
  BOARD_PUT_API: '/app/board',
  BOARD_DELETE_API: (boardId) => {
    return `/app/board?boardId=${boardId}`;
  },

  BOARD_LIST_GET_API: (page, sort, keyword) => {
    return `/app/board/all?page=${page}&sort=${sort}&keyword=${keyword}`;
  },
  BOARD_TOP_LIST_GET_API: '/app/board/top',

  BOARD_LIKE_GET_API: (scriptId) => {
    return `/app/boardlike/check?scriptId=${scriptId}`;
  },
  BOARD_LIKE_POST_API: (boardId) => {
    return `/app/boardlike?boardId=${boardId}`;
  },
  BOARD_LIKE_DELETE_API: (boardId) => {
    return `/app/boardlike?boardId=${boardId}`;
  },

  COMMENT_PUT_API: '/app/comment',
  COMMENT_POST_API: '/app/comment',
  COMMENT_DELETE_API: (commentId) => {
    return `/app/comment?commentId=${commentId}`;
  },
  COMMENT_LIST_GET_API: (commentId, page) => {
    return `/app/comment/all?commentId=${commentId}&page=${page}`;
  },
};
