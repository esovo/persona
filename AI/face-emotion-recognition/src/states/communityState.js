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
      imgUrl: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA0MjBfMTY3%2FMDAxNjUwNDAyMzIyMTEz.qEMSZaMljLh3kZSpCu98es-H9Wk8KJ5mK40Jldxe7fwg.BEnKIE2ET9E_tRJchfq01Oj_0EFi6B6JkmT7_twTdtUg.PNG.xhxhthgusdl%2F66845797-ACC8-4A21-8761-5AFD62754A2E.png&type=sc960_832',
      url: 'server Url',
    },
    {
      id: 2,
      createDate: '2023-04-02 16:30:28',
      title: '영상 2',
      imgUrl: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA5MjlfMTgw%2FMDAxNjY0NDI3NzUxNTI5.49Tb_QqDDNrHWcLQM3EKj4NU1JbYqHz3y2vc0LqvKHsg.toMJgESLH6CpdcpsjrbTZqPYUxaXl9RHlTBIl4s9hMIg.JPEG.sarah110729%2F09B968B6-3593-4C11-A051-0D3408E5A7B9.jpeg&type=sc960_832',
      url: 'server Url',
    },
    {
      id: 3,
      createDate: '2023-04-02 16:32:28',
      title: '영상 3',
      imgUrl: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzAzMDdfMTQ4%2FMDAxNjc4MTg4NzkxNTIx.JgmsVJhlYDj09lBZxnVdUUTXIS2f1nxZSS5Ecrjby0Ug.yvMnNS44W0sbY2BsqooTBkPe6cqnmx4d2cVAYxMBOTwg.JPEG.jiwoo623%2FFB%25A3%25DFIMG%25A3%25DF1678188756204.jpg&type=sc960_832',
      url: 'server Url',
    },
    {
      id: 4,
      createDate: '2023-04-02 16:38:28',
      title: '영상 4',
      imgUrl: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA5MjBfNzAg%2FMDAxNjMyMDc4NTM1NzM2.DsYA6c81SougygCFJmg-DJj5bfkFJ-ZlViz-wM0aH9Yg.ussY6ck4Anw502SWptfkFVw8sxFXeAWnnZYZUSPv3o4g.JPEG.alsrud355w%2FFAD7689F-F219-4A33-9C7A-735037695444.jpeg&type=sc960_832',
      url: 'server Url',
    },
    {
      id: 5,
      createDate: '2023-04-02 16:32:28',
      title: '영상 5',
      imgUrl: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA3MzBfNDkg%2FMDAxNjU5MTY1NjMwNDE3.XErtxpdyn7Becfe_QObfQK_qHD10QGjdhaRvwiqihkEg.0I7D0ZayefDGj4YiipfiF4mWjLprlPfNvBQZyonZ3aEg.JPEG.ymtlfet%2FIMG_8217.jpg&type=sc960_832',
      url: 'server Url',
    },
    {
      id: 6,
      createDate: '2023-04-02 16:38:28',
      title: '영상 6',
      imgUrl: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA5MjlfNDkg%2FMDAxNjAxMzMxNTMzNjMx.Tyu_XVVNiupD49m_Yw0KHrhfGVEjwMaqj-r8UBud7kIg.cXtW58WNP2Pq2e7w_27hNBvf5K_XUq28yiGtTYUx2hwg.PNG.connoharu%2FIMG_1700.PNG&type=sc960_832',
      url: 'server Url',
    },
    {
      id: 7,
      createDate: '2023-04-02 16:32:28',
      title: '영상 7',
      imgUrl: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzAxMTVfODgg%2FMDAxNjczNzYwNDUzMzYx.EywM1wOD_zwlfWejKsxkBTtDsiC8I1N64I_p14fWWJwg.VmU9Ld_cHYAujIWim0murkQCfEDJxBIur4tzpI7sOOAg.JPEG.g_minn%2Fa1a0a4a4492add9a5e7fe1228c93b0f8.jpg&type=sc960_832',
      url: 'server Url',
    },
    {
      id: 8,
      createDate: '2023-04-02 16:32:28',
      title: '영상 7',
      imgUrl: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzAxMTVfODgg%2FMDAxNjczNzYwNDUzMzYx.EywM1wOD_zwlfWejKsxkBTtDsiC8I1N64I_p14fWWJwg.VmU9Ld_cHYAujIWim0murkQCfEDJxBIur4tzpI7sOOAg.JPEG.g_minn%2Fa1a0a4a4492add9a5e7fe1228c93b0f8.jpg&type=sc960_832',
      url: 'server Url',
    },
    {
      id: 9,
      createDate: '2023-04-02 16:32:28',
      title: '영상 7',
      imgUrl: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzAxMTVfODgg%2FMDAxNjczNzYwNDUzMzYx.EywM1wOD_zwlfWejKsxkBTtDsiC8I1N64I_p14fWWJwg.VmU9Ld_cHYAujIWim0murkQCfEDJxBIur4tzpI7sOOAg.JPEG.g_minn%2Fa1a0a4a4492add9a5e7fe1228c93b0f8.jpg&type=sc960_832',
      url: 'server Url',
    },
    {
      id: 10,
      createDate: '2023-04-02 16:32:28',
      title: '영상 7',
      imgUrl: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzAxMTVfODgg%2FMDAxNjczNzYwNDUzMzYx.EywM1wOD_zwlfWejKsxkBTtDsiC8I1N64I_p14fWWJwg.VmU9Ld_cHYAujIWim0murkQCfEDJxBIur4tzpI7sOOAg.JPEG.g_minn%2Fa1a0a4a4492add9a5e7fe1228c93b0f8.jpg&type=sc960_832',
      url: 'server Url',
    }
  ],
});
