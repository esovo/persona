import React, { useEffect, useState } from 'react';
import { useRecoilValue } from 'recoil';
import { detailState } from '../../states/practiceFilterState';
import { tokenState } from "../../states/loginState";
import axios from 'axios';
import style from './PracticeDetail.module.scss';
import Header from '../../components/Common/Header';
import QuillEditor from '../../components/CommunityPage/QuillEditor';

export default function PracticeDetail() {

    // const API_BASE_URL = 'https://j8b301.p.ssafy.io/app';
    const API_BASE_URL = 'http://j8b301.p.ssafy.io:8080/app';
    const getId = useRecoilValue(detailState);
    const token = useRecoilValue(tokenState);

    const [data, setData] = useState([]);
    // {
    //     actor: '',
    //     author: '',
    //     bookmarkCnt: 0,
    //     content: '',
    //     createdDate: '',
    //     emotion: '',
    //     id: 0,
    //     participantCnt: 0,
    //     title: '',

    // }
    

    useEffect(() => {
        let receivedData;

        axios.get(`${API_BASE_URL}/script`, {
            headers: {
                'Authorization': token
              },
            params: {
                scriptId: getId
            }
        })
        .then((res) => {
            receivedData = res.data.value;
            setData(receivedData);
            // console.log(receivedData);
        });

        return () => {
            console.log(receivedData);
        }

    }, []);

    console.log(data);

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
