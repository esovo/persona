/* eslint-disable react-hooks/rules-of-hooks */
import { useState, useEffect } from 'react';
import { useRecoilState } from 'recoil';
import { useRecoilValue } from 'recoil';
import { videoModal, videosState } from '../../states/communityState';
import { tokenState } from '../../states/loginState';
import Header from '../../components/Common/Header';
import Video from '../../components/Storage/Video';
// import Footer from "../../components/Common/Footer";
import style from './Storage.module.scss';
import Pagenation from "@mui/material/Pagination";
import { useEffect } from 'react';
import { videoApis } from '../../apis/videoApis';
import axios from 'axios';
import { tokenState } from '../../states/loginState';

export default function List() {
  const videos = useRecoilValue(videosState);
  const [selectedVideo, setSelectedVideo] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const token = useRecoilValue(tokenState);

  useEffect(() => {
    console.log(videos);
    axios.get(videoApis.VIDEO_GET_API(currentPage), {
      headers: {  Authorization: token }}).then((res) => {
      videos = res;
      console.log(videos);
    })
  }, [])

  const pagenationHandler = () => {
    //axios 요청해서 videos 바꿔주기

  }

  return (
    <div className={style.wrapper}>
      <Header />
      <div className={style.intro}>
        <div className={style.title}>보관함</div>
        <div className={style.content}>내가 연습한 연기 영상과 분석을 볼 수 있어요.</div>
      </div>
      <div className={style.container}>
        <div className={style.videos}>
          {videos.map((video) => (
            <Video key={video.id} {...video} onSelect={setSelectedVideo} />
          ))}
        </div>
      </div>
      <Pagenation count={1} onClick={pagenationHandler}/>
      {/* <Footer /> */}
    </div>
  );
}
