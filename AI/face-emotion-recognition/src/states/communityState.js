import { atom } from 'recoil';

export const postWriteModal = atom({
  key: 'postWriteModal',
  default: false,
});

export const postDetailModal = atom({
  key: 'postDetailModal',
  default: false,
});

export const videoModal = atom({
  key: 'videoModal',
  default: false,
});

export const selectedPostState = atom({
  key: 'selectedPostState',
  default: undefined,
});

export const postsState = atom({
  key: 'postsState',
  default: [
    {
      id: 1,
      user: 'ovo',
      date: '2023-03-24 11:04:30',
      title: '테스트 제목입니다.',
      content: '테스트 글입니다.',
      like: 1,
      comment: 0,
    },
    {
      id: 2,
      user: 'ovo6',
      date: '2023-03-28 11:04:30',
      title: '테스트2 제목입니다.',
      content: '테스트2 글입니다.',
      like: 2,
      comment: 0,
    },
    {
      id: 3,
      user: 'ovovo',
      date: '2023-03-28 11:04:30',
      title: '테스트3 제목입니다.',
      content: '테스트3 글입니다.',
      like: 3,
      comment: 0,
    },
    {
      id: 4,
      user: 'ovovo',
      date: '2023-03-28 11:04:30',
      title: '테스트4 제목입니다.',
      content: '테스트4 글입니다.',
      like: 4,
      comment: 0,
    },
    {
      id: 5,
      user: 'ovovo',
      date: '2023-03-28 11:04:30',
      title: '테스트5 제목입니다.',
      content: '테스트5 글입니다.',
      like: 5,
      comment: 0,
    },
  ],
});

export const popostsState = atom({
  key: 'popostsState',
  default: [
    {
      id: 1,
      user: 'ovo',
      date: '2023-03-24 11:04:30',
      title: '인기글 테스트 제목입니다.',
      content: '테스트 글입니다.',
      like: 1,
      comment: 0,
    },
    {
      id: 2,
      user: 'ovo6',
      date: '2023-03-28 11:04:30',
      title: '인기글 테스트2 제목입니다.',
      content: '테스트2 글입니다.',
      like: 2,
      comment: 0,
    },
    {
      id: 3,
      user: 'ovovo',
      date: '2023-03-28 11:04:30',
      title: '인기글 테스트3 제목입니다.',
      content: '테스트3 글입니다.',
      like: 3,
      comment: 0,
    },
  ],
});

export const commentsState = atom({
  key: 'commentsState',
  default: [
    {
      id: 1,
      nickname: 'ovo1',
      createDate: '2023-03-24 11:04:30',
      title: '테스트 제목입니다.',
      content: '댓글 1입니다',
      userProfile: '프로필 url',
    },
    {
      id: 2,
      nickname: 'ovo2',
      createDate: '2023-03-24 11:04:30',
      title: '테스트 제목입니다.',
      content: '댓글 2입니다.',
      userProfile: '프로필 url',
    },
    {
      id: 3,
      nickname: 'ovo3',
      createDate: '2023-04-01 22:26:30',
      title: '테스트 제목입니다.',
      content: '댓글 3입니다.',
      userProfile: '프로필 url',
    },
    {
      id: 4,
      nickname: 'ovo4',
      createDate: '2023-04-01 22:26:30',
      title: '테스트 제목입니다.',
      content: '댓글 4입니다.',
      userProfile: '프로필 url',
    },
  ],
});

export const videosState = atom({
  key: 'videosState',
  default: [
    {
      id: 1,
      createDate: '2023-04-02 16:28:28',
      title: '영상 1',
      imgUrl: 'kurome.jpg',
      url: 'server Url',
    },
    {
      id: 2,
      createDate: '2023-04-02 16:30:28',
      title: '영상 2',
      imgUrl: 'mymelody.jpg',
      url: 'server Url',
    },
    {
      id: 3,
      createDate: '2023-04-02 16:32:28',
      title: '영상 3',
      imgUrl: 'cinnamoroll.jpg',
      url: 'server Url',
    },
    {
      id: 4,
      createDate: '2023-04-02 16:38:28',
      title: '영상 4',
      imgUrl: 'hellokitty.jpg',
      url: 'server Url',
    },
    {
      id: 3,
      createDate: '2023-04-02 16:32:28',
      title: '영상 5',
      imgUrl: 'cinnamoroll.jpg',
      url: 'server Url',
    },
    {
      id: 4,
      createDate: '2023-04-02 16:38:28',
      title: '영상 6',
      imgUrl: 'hellokitty.jpg',
      url: 'server Url',
    },
    {
      id: 3,
      createDate: '2023-04-02 16:32:28',
      title: '영상 7',
      imgUrl: 'cinnamoroll.jpg',
      url: 'server Url',
    },
  ],
});
