import React, { useState } from 'react';
import { commentsState } from '../../states/communityState';
import { useRecoilState, useRecoilValue } from 'recoil';
import { postDetailModal, selectedPostState } from '../../states/communityState';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCommentDots, faEye, faHeart as regularHeart } from '@fortawesome/free-regular-svg-icons';
import { faXmark, faEllipsisVertical, faHeart as solidHeart } from '@fortawesome/free-solid-svg-icons';
import style from './PostDetailModal.module.scss';
import Comment from './Comment';
import axios from 'axios';

export default function Modal() {
  const [showModal, setShowModal] = useRecoilState(postDetailModal);
  const [selectedPost, setSelectedPost] = useRecoilState(selectedPostState);
  const [isClicked, setIsClicked] = useState(false);
  const comments = useRecoilValue(commentsState); // Recoil atom에서 게시물 목록을 가져옵니다.

  const shutModal = () => {
    setShowModal(false);
  };
  const heartClickHandler = () => {
    setIsClicked(!isClicked);
  };
  return (
    <>
      {showModal && (
        <div className={style.back}>
          <div className={style.container}>
            <div className={style.body}>
              <div className={style.header}>
                <button className={style.close} onClick={shutModal}>
                  <FontAwesomeIcon icon={faXmark} style={{ color: '#f5f5f5' }} />
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
                <div className={style.btn}>
                  <button className={style.likebtn} onClick={heartClickHandler}>
                    <FontAwesomeIcon icon={isClicked ? solidHeart : regularHeart} style={{ color: '#ce4040' }} />
                  </button>
                  <button className={style.menubtn}>
                    <FontAwesomeIcon icon={faEllipsisVertical} style={{ color: '#5d5d5d' }} />
                  </button>
                </div>
              </div>
              <div className={style.title}>{selectedPost.title}</div>
              <div className={style.content}>{selectedPost.content}</div>
              <div className={style.items}>
                <div className={style.like}>
                  <FontAwesomeIcon icon={regularHeart} style={{ color: '#ce4040' }} />
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
            </div>
            <div className={style.bottom}>
              <div className={style.input}>
                <input className={style.commentInput} type="text" placeholder="댓글을 입력하세요." />
                <button className={style.push}>보내기</button>
              </div>

              {comments.map((comment) => (
                <div className={style.commentItem} key={comment.id}>
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
