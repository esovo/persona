import React from 'react';
import style from './BookScript.module.scss';
import { useNavigate } from 'react-router-dom';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart, faEye, faUsers } from '@fortawesome/free-solid-svg-icons';
import { faHeart as empty } from '@fortawesome/free-regular-svg-icons';
import { useRecoilState } from 'recoil';
import { detailState } from '../../states/practiceFilterState';

export default function Script({ id, actor, author, createdDate, emotion, genre, title, bookmarkCnt, participantCnt }) {
  const [detail, setDetail] = useRecoilState(detailState);
  const navigate = useNavigate();

  const move = () => {
    setDetail(id);
    navigate('/practice/detail');
  };

  const bookmark = false ? <FontAwesomeIcon icon={faHeart} /> : <FontAwesomeIcon icon={empty} />;

  return (
    <div className={style.container} onClick={move}>
      <div className={style.newandbookmark}>
        <div className={style.bookmark}>{bookmark}</div>
      </div>
      <div className={style.scriptContent}>
        <div className={style.date}>작성일 | {createdDate}</div>
        <div className={style.title}>{title}</div>
        <div className={style.actor}>{actor}</div>
        <div className={style.category}>
          <div className={style.round}>#{emotion}</div>
          <div className={style.round}>#{genre}</div>
        </div>
        <div className={style.line}></div>
        <div className={style.subinfo}>
          <div className={style.author}>{author}</div>
          <div className={style.cntinfo}>
            <FontAwesomeIcon icon={faEye} />
            {bookmarkCnt}
            <FontAwesomeIcon icon={faUsers} />
            {participantCnt}
          </div>
        </div>
      </div>
    </div>
  );
}
