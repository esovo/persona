/* eslint-disable @typescript-eslint/no-unused-vars */
import axios from 'axios';
import style from '../styles/PostModal.module.scss';
import { useRecoilState } from 'recoil';
import { postDetailModal, selectedPostState } from '../states/communityState';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';

export default function Modal() {
  const [showModal, setShowModal] = useRecoilState(postDetailModal);
  const [selectedPost, setSelectedPost] = useRecoilState(selectedPostState);
  const shutModal = () => {
    setShowModal(false);
  };

  return (
    <>
      {showModal && (
        <div className={style.back}>
          <div className={style.container}>
            <div className={style.header}>
              <button className={style.close} onClick={shutModal}>
                <FontAwesomeIcon icon={faXmark} style={{ color: '#545454' }} />
              </button>
            </div>
            <p>{selectedPost.user}</p>
            <p>{selectedPost.date}</p>
            <h2>{selectedPost.title}</h2>
            <p>{selectedPost.content}</p>
            <p>{selectedPost.like}</p>
            <p>{selectedPost.comment}</p>
            <div className={style.bottom}>
              <button className={style.write}>게시</button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
