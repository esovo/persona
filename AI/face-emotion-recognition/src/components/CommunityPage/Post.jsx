import React, { useState, useEffect } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';
import { tokenState, user } from '../../states/loginState';
import { communityApis } from '../../apis/communityApis';
import { postDetailModal, selectedPostState, isHeartState } from '../../states/communityState';
import style from './Post.module.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCommentDots, faHeart as regularHeart } from '@fortawesome/free-regular-svg-icons';
import { faHeart as solidHeart } from '@fortawesome/free-solid-svg-icons';
import moment from 'moment';
import axios from 'axios';

const Post = ({ id, nickName, createdDate, title, content, likeCnt, viewCnt, commentCnt }) => {
  const BASE_URL = 'https://j8b301.p.ssafy.io';
  const token = useRecoilValue(tokenState);
  const [selectedPost, setSelectedPost] = useRecoilState(selectedPostState);
  const [showDetailModal, setShowDetailModal] = useRecoilState(postDetailModal);
  const [showModal, setShowModal] = useRecoilState(postDetailModal);
  const [isHeart, setIsHeart] = useState(false);

  const openModal = () => {
    setSelectedPost({ id, nickName, createdDate, title, content, likeCnt, viewCnt, commentCnt });
    setShowDetailModal(true);
  };

  useEffect(() => {
    axios
      .get(BASE_URL + communityApis.BOARD_LIKE_GET_API(id), {
        headers: {
          Authorization: token,
        },
      })
      .then((res) => {
        setIsHeart(res.data.value);
      });
  }, [showModal]);

  const heartClickHandler = () => {
    if (isHeart) {
      // 좋아요가 이미 되어있는 경우 취소
      axios
        .delete(BASE_URL + communityApis.BOARD_LIKE_DELETE_API(id), {
          headers: {
            Authorization: token,
          },
        })
        .then((res) => {
          setIsHeart(false);
        });
    } else {
      // 좋아요가 되어있지 않은 경우
      axios
        .post(BASE_URL + communityApis.BOARD_LIKE_POST_API(id), null, {
          headers: {
            Authorization: token,
          },
        })
        .then((res) => {
          setIsHeart(true);
        });
    }
  };

  return (
    <div className={style.post}>
      <div className={style.content} onClick={openModal}>
        <div className={style.title}>{title}</div>
        <div className={style.body} dangerouslySetInnerHTML={{ __html: content }}></div>
        <div className={style.info}>
          <div className={style.nickname}>{nickName}</div>|
          <div className={style.date}>{moment.utc(createdDate).utcOffset('+0900').format('YYYY-MM-DD HH:mm:ss')}</div>
        </div>
      </div>
      <div className={style.itmes}>
        <div className={style.like} onClick={heartClickHandler}>
          <FontAwesomeIcon icon={isHeart ? solidHeart : regularHeart} style={{ color: '#ce4040' }} />
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
