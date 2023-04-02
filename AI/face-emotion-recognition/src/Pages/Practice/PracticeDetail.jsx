import React from 'react';
import { useRecoilValue } from 'recoil';
import style from './PracticeDetail.module.scss';
import Header from '../../components/Common/Header';
import QuillEditor from '../../components/CommunityPage/QuillEditor';

export default function PracticeDetail() {

    return (
        <>
            <Header />
            <div className={style.container}>
                <div className={style.info}>대본 분석</div>
                <div className={style.subinfo}>대본을 자유롭게 분석해보세요.</div>
                <div className={style.script}>
                    <div className={style.detail}>

                    </div>
                    <div className={style.edit}><QuillEditor /></div>
                </div>
                <div className={style.route}>
                    <div className={style.button}>목록</div>
                    <div className={style.button}>연습</div>
                </div>

            </div>
        </>
    );
}
