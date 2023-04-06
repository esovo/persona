import axios from 'axios';
import React, { useEffect, useState } from 'react';
import style from './VideoModal.module.scss';
import { useRecoilState, useRecoilValue } from 'recoil';
import { videoModal } from '../../states/communityState';
import { videoApis } from '../../apis/videoApis';
import { tokenState } from '../../states/loginState';
import Video from './Video';

export default function Modal() {
  const [showModal, setShowModal] = useRecoilState(videoModal);
  const [videos, setVideos] = useState([]);
  const [selectedVideo, setSelectedVideo] = useState(null);
  const token = useRecoilValue(tokenState);

  const shutModal = () => {
    setShowModal(false);
  };

  useEffect(() => {
    const page = 0;
    axios
      .get(videoApis.VIDEO_GET_API(page), {
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res.data.value.content);
        setVideos(res.data.value.content);
      });
  }, []);
  const handleWrite = () => {
    // 선택된 비디오 데이터를 사용하여 다른 컴포넌트에서 처리할 수 있습니다.
    console.log(selectedVideo);
  };

  return (
    <>
      {showModal && (
        <div className={style.back}>
          <div className={style.container}>
            <div className={style.header}>
              <div className={style.title}>내 영상 가져오기</div>
            </div>
            <div className={style.videos}>
              {videos.map((video) => (
                <Video key={video.id} {...video} onSelect={setSelectedVideo} />
              ))}
            </div>
            <div className={style.bottom}>
              <button className={style.close} onClick={shutModal}>
                취소
              </button>
              <button className={style.write} onClick={handleWrite}>
                확인
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
