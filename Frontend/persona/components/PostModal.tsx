// import axios from 'axios';
import style from '../styles/PostModal.module.scss';
import { useRecoilState } from 'recoil';
import { postmodal } from '../states/communityState';
import QuillEditor from './QuillEditor';

export default function Modal() {
  const [showModal, setShowModal] = useRecoilState(postmodal);

  const shutModal = () => {
    setShowModal(false);
  };

  return (
    <>
      {showModal && (
        <div className={style.back}>
          <div className={style.container}>
            <div className={style.header}>
              <div className={style.title}>글쓰기</div>
              <button className={style.close} onClick={shutModal}>
                X
              </button>
            </div>
            <QuillEditor />
            <div className={style.bottom}>
              <button className={style.pull}>내 영상 가져오기</button>
              <button className={style.write}>게시</button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
