import React from 'react';
import style from './Script.module.scss';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart, faEye, faUsers } from '@fortawesome/free-solid-svg-icons';
import { faHeart as empty } from '@fortawesome/free-regular-svg-icons';

export default function Script({ data }) {

    // const data = datas;
    // {
    //     id: 1234,
    //     title: '타이틀이다요',
    //     author: '작가',
    //     actor: '배우',
    //     content: '내용',
    //     viewCnt: 123456,
    //     emotion: '감정',
    //     genre: '장르',
    //     createdDate: '이건무슨타입일까?',
    //     bookmarkCnt: 123,
    //     participantCnt: 12
    // }

    const bookmark = false ? <FontAwesomeIcon icon={faHeart} /> : <FontAwesomeIcon icon={empty} />

    return (
        <div className={style.container}>
            <div className={style.newandbookmark}>
                <div className={style.new}>NEW</div>
                <div className={style.bookmark}>{bookmark}</div>
            </div>
            <div className={style.date}>작성일 | {data.createdDate}</div>
            <div className={style.title}>{data.title}</div>
            <div className={style.actor}>{data.actor}</div>
            <div className={style.category}>
                <div className={style.round}>#{data.emotion}</div>
                <div className={style.round}>#{data.genre}</div>
            </div>
            <div className={style.line}></div>
            <div className={style.subinfo}>
                <div className={style.author}>{data.author}</div>
                <div className={style.cntinfo}>
                    <FontAwesomeIcon icon={faEye} />
                    {data.bookmarkCnt}
                    <FontAwesomeIcon icon={faUsers} />
                    {data.participantCnt}
                </div>

            </div>

        </div>
    );
}