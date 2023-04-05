const BASE_URL = 'https://j8b301.p.ssafy.io';

export const videoApis = {
    
    VIDEO_GET_API: (page) => {
      return BASE_URL + `/app/video?page=${page}`;
    },
};