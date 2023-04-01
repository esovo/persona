import React from 'react';
import style from './Script.module.scss';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart } from '@fortawesome/free-solid-svg-icons';

export default function Script(props) {
    return (
        <div className={style.container}>
            <div className={style.newandbookmark}>
                <div className={style.new}>NEW</div>
                <div className={style.bookmark}><FontAwesomeIcon icon={faHeart} /></div>
            </div>
            <div className={style.date}>

            </div>
            <div className={style.title}>

            </div>
            <div className={style.actor}>

            </div>
            <div className={style.category}>

            </div>
            <div className={style.line}></div>
            <div className={style.subinfo}>

            </div>

        </div>
    );
}