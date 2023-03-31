import axios from 'axios';
import style from './PostDetailModal.module.scss';
import Comment from './Comment';
import { commentsState } from '../../states/communityState';
import { useRecoilState, useRecoilValue } from 'recoil';
import { postDetailModal, selectedPostState } from '../../states/communityState';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCommentDots, faHeart, faEye } from '@fortawesome/free-regular-svg-icons';
import { faXmark } from '@fortawesome/free-solid-svg-icons';

export default function Modal() {
  const [showModal, setShowModal] = useRecoilState(postDetailModal);
  const [selectedPost, setSelectedPost] = useRecoilState(selectedPostState);
  const shutModal = () => {
    setShowModal(false);
  };
  const comments = useRecoilValue(commentsState); // Recoil atom에서 게시물 목록을 가져옵니다.

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
            <div className={style.info}>
              <div className={style.profile}>
                <img src="user.png" alt="user" width="50" />
              </div>
              <div className={style.userdate}>
                <div className={style.nickname}>{selectedPost.user}</div>
                <div className={style.date}>{selectedPost.date}</div>
              </div>
            </div>
            <div className={style.title}>{selectedPost.title}</div>
            <div className={style.content}>{selectedPost.content}</div>
            <div className={style.items}>
              <div className={style.like}>
                <FontAwesomeIcon icon={faHeart} style={{ color: '#ce4040' }} />
                <div className={style.num}>{selectedPost.like}</div>
              </div>
              <div className={style.visited}>
                <FontAwesomeIcon icon={faEye} style={{ color: '#5e5e5e' }} />
                <div className={style.num}>10</div>
              </div>
              <div className={style.comment}>
                <FontAwesomeIcon icon={faCommentDots} style={{ color: '#5e5e5e' }} />
                <div className={style.num}>{selectedPost.comment}</div>
              </div>
            </div>
            <div className={style.bottom}>
              <div className={style.input}>
                <input className={style.commentInput} type="text" />
                <button className={style.push}>보내기</button>
              </div>
              {comments.map((comment) => (
                <div key={comment.id}>
                  <Comment key={comment.id} {...comment} />
                </div>
              ))}
            </div>
          </div>
        </div>
      )}
    </>
  );
}
