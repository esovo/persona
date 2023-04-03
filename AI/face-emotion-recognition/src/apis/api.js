import axios from 'axios';
import { useRecoilValue } from 'recoil';
import { tokenState } from '../states/loginState';

const BASE_URL = 'http://j8b301.p.ssafy.io:8080/';
export const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

instance.interceptors.request.use(
  (config) => {
    const token = useRecoilValue(tokenState);
    if (token) {
      config.headers['Authorization'] = `${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);

instance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    return Promise.reject(error);
  },
);

// export const fetchData = {
//   get: async (url, option) => await instance.get(url, option),
//   post: async (url, body, option) => await instance.post(url, body, option),
//   put: async (url, body, option) => await instance.put(url, body, option),
//   delete: async (url, body, option) => await instance.delete(url, body, option),
// };
