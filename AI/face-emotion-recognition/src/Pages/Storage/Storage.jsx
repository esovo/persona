/* eslint-disable react-hooks/rules-of-hooks */
import { useState, useEffect } from 'react';
import { useRecoilState } from 'recoil';
import { useRecoilValue } from 'recoil';
import { videosState } from '../../states/communityState';
import Header from '../../components/Common/Header';
import Video from '../../components/Storage/Video';
// import Footer from "../../components/Common/Footer";
import style from './Storage.module.scss';
import Pagination from "@mui/material/Pagination";
import { videoApis } from '../../apis/videoApis';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function List() {
  let videos = useRecoilValue(videosState);
  const [currentPage, setCurrentPage] = useState(1);
  const token = useRecoilValue(tokenState);
  const [totalPage, setTotalPage] = useState(3);
  const navigate = useNavigate();
  // let videos = [];

  useEffect(() => {
    console.log(videos);
    axios.get(videoApis.VIDEO_GET_API(currentPage-1), {
      headers: { Authorization: token }}).then((res) => {
      console.log(res);
      console.log(res.data.value.content);
      videos = res.data.value.content;
      setTotalPage(res.data.value.totalPages); //받아온 totalPage 넣어주기
      setCurrentPage(1);
      console.log(totalPage);
      console.log(currentPage);
    })
  }, [])

  const pagenationHandler = (event, page) => {
    //axios 요청해서 videos 바꿔주기
    setCurrentPage(page);
    console.log(event + " " + page + " " + currentPage);

    axios.get(videoApis.VIDEO_GET_API(currentPage-1), {
      headers: { Authorization: token }}).then((res) => {
      console.log(res);
      console.log(res.data.value.content);
      videos = res.data.value.content;
      setTotalPage(res.data.value.totalPages); //받아온 totalPage 넣어주기
      setCurrentPage(page);
      console.log(totalPage);
      console.log(currentPage);
    })
  }

  const selectedVideo = (videoId) =>{
    //화면전환
    console.log(videoId);
    navigate('/savepage', {
      state: {
        id: videoId
      }
    })
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
            <Video key={video.id} {...video} onSelect={selectedVideo} />
          ))}
        </div>
      </div>
      <Pagination sx={{margin: "0 auto"}} count={totalPage} page={currentPage} onChange={pagenationHandler}/>
      {/* <Footer /> */}
    </div>
  );
}
