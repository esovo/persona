import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import { postDetailModal, selectedPostState } from '../../states/communityState';
import style from './Post.module.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCommentDots, faHeart as regularHeart } from '@fortawesome/free-regular-svg-icons';
import { faHeart as solidHeart } from '@fortawesome/free-solid-svg-icons';
import moment from 'moment';

const Post = ({ id, nickName, createdDate, title, content, likeCnt, commentCnt }) => {
  const [selectedPost, setSelectedPost] = useRecoilState(selectedPostState);
  const [showDetailModal, setShowDetailModal] = useRecoilState(postDetailModal);
  const [isClicked, setIsClicked] = useState(false);
  const openModal = () => {
    setSelectedPost({ id, nickName, createdDate, title, content, likeCnt, commentCnt });
    setShowDetailModal(true);
  };
  const heartClickHandler = () => {
    setIsClicked(!isClicked);
  };
  return (
    <div className={style.post}>
      <div className={style.content} onClick={openModal}>
        <div className={style.title}>{title}</div>
        <div className={style.body}>{content}</div>
        <div className={style.info}>
          <div className={style.nickname}>{nickName}</div>|<div className={style.date}>{moment.utc(createdDate).utcOffset('+0900').format('YYYY-MM-DD HH:mm:ss')}</div>
        </div>
      </div>
      <div className={style.itmes}>
        <div className={style.like} onClick={heartClickHandler}>
          <FontAwesomeIcon icon={isClicked ? solidHeart : regularHeart} style={{ color: '#ce4040' }} />
          <div>{likeCnt}</div>
        </div>
        <div className={style.comment}>
          <FontAwesomeIcon icon={faCommentDots} style={{ color: '#5e5e5e' }} />
          <div className={style.commentbtn} onClick={openModal}>
            {commentCnt}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Post;
