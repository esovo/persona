// import axios from 'axios';
import style from './PostWriteModal.module.scss';
import { useRecoilState } from 'recoil';
import { postWriteModal } from '../../states/communityState';
import QuillEditor from './QuillEditor';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCirclePlay } from '@fortawesome/free-regular-svg-icons';

export default function Modal() {
  const [showModal, setShowModal] = useRecoilState(postWriteModal);

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
            </div>
            <input className={style.input} type="text" placeholder="제목을 입력하세요." />
            <QuillEditor />
            <div className={style.bottom}>
              <div className={style.video}>
                <FontAwesomeIcon icon={faCirclePlay} style={{ color: '#5c5c5c' }} />
                <button className={style.pull}>내 영상 가져오기</button>
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
