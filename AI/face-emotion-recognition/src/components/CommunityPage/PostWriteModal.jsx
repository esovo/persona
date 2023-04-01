// import axios from 'axios';
import React, { useState } from 'react';
import style from './PostWriteModal.module.scss';
import { useRecoilState } from 'recoil';
import { postWriteModal, videoModal } from '../../states/communityState';
import QuillEditor from './QuillEditor';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCirclePlay } from '@fortawesome/free-regular-svg-icons';

export default function Modal() {
  const [showModal, setShowModal] = useRecoilState(postWriteModal);
  const [showVideoModal, setShowVideoModal] = useRecoilState(videoModal);
  const shutModal = () => {
    setShowModal(false);
  };

  const openModal = () => {
    setShowVideoModal(true);
  };

  return (
    <>
      {showModal && (
        <div className={style.back}>
          <div className={style.container}>
            <div className={style.header}>
              <div className={style.title}>글쓰기</div>
            </div>
            <input className={style.input} type="text" placeholder="제목을 입력하세요." />
            <QuillEditor />
            <div className={style.bottom}>
              <div className={style.video} onClick={openModal}>
                <FontAwesomeIcon icon={faCirclePlay} style={{ color: '#5c5c5c' }} />
                <div className={style.pull}>내 영상 가져오기</div>
              </div>
              <button className={style.close} onClick={shutModal}>
                취소
              </button>
              <button className={style.write}>게시</button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
