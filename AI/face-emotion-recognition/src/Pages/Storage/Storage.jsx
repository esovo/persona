/* eslint-disable react-hooks/rules-of-hooks */
import { useState } from 'react';
import { useRecoilState } from 'recoil';
import { useRecoilValue } from 'recoil';
import { videoModal, videosState } from '../../states/communityState';
import Header from '../../components/Common/Header';
import Video from '../../components/Storage/Video';
// import Footer from "../../components/Common/Footer";
import style from './Storage.module.scss';

export default function List() {
  const videos = useRecoilValue(videosState);
  const [selectedVideo, setSelectedVideo] = useState(null);

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
      {/* <Footer /> */}
    </div>
  );
}
