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

export const isHeartState = atom({
  key: 'isHeartState',
  default: false,
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
