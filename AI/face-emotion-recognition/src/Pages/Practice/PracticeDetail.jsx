import React, { useEffect, useState } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';
import { detailState, writeState } from '../../states/practiceFilterState';
import { tokenState } from "../../states/loginState";
import axios from 'axios';
import style from './PracticeDetail.module.scss';
import Header from '../../components/Common/Header';
import QuillEditor from '../../components/CommunityPage/QuillEditor';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart, faEye, faUsers } from '@fortawesome/free-solid-svg-icons';
import { faHeart as empty } from '@fortawesome/free-regular-svg-icons';

import { useNavigate } from 'react-router-dom';


export default function PracticeDetail() {

    const API_BASE_URL = 'https://j8b301.p.ssafy.io/app';
    // const API_BASE_URL = 'http://j8b301.p.ssafy.io:8080/app';
    const getId = useRecoilValue(detailState);
    const token = useRecoilValue(tokenState);
    
    const [date, setDate] = useState('');
    const [data, setData] = useState([]);

    const [write, setWrite] = useRecoilState(writeState);

    const [heart, setHeart] = useState(false);

    const navigate = useNavigate();

    const back = () => {
        navigate('/practice');
    }

    const go = () => {
        const content = document.querySelector(`.ql-editor`).innerHTML;
        setWrite(content);
        navigate(`/dashboard/${getId}`);
    }

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
            console.log(typeof(receivedData.createdDate))
            let text =receivedData.createdDate.substring(0,10);
            console.log(text);
            setDate(text)
        });

        return () => {
            console.log(receivedData);
        }

    }, []);

    const check = () => {
        axios.get(`https://j8b301.p.ssafy.io/app/bookmark/check`, {
            headers: {
                Authorization: token
            },      
            params: {
                scriptId: data.id
            } 
        }).then((res) => {
            let result = res.data.value;
            console.log(result);
            if (result) {
                axios.delete(`https://j8b301.p.ssafy.io/app/bookmark?scriptId=${data.id}`, {
                    headers: {
                        Authorization: token
                    }
                }).then((res) => {
                    setHeart(false);
                })         
                
            } else {
                axios.post(`https://j8b301.p.ssafy.io/app/bookmark?scriptId=${data.id}`, null, {
                    headers: {
                        'Authorization': token
                    }
                }).then((res) => {
                    setHeart(true);
                }) 
            }
        })
      }

    console.log(data);
    const bookmark = heart ? <FontAwesomeIcon icon={faHeart} /> : <FontAwesomeIcon icon={empty} />

    return (
        <>
            <Header />
            <div className={style.container}>
                <h2 className={style.maintext}>대본 분석</h2>
                <h3 className={style.subtext}>대본을 자유롭게 분석해보세요.</h3>
                <div className={style.script}>
                    <div className={style.detail}>
                        <div className={style.wrap}>
                            <div className={style.newandbookmark}>
                                <div className={style.new}>NEW</div>
                                <div className={style.bookmark} onClick={check}>{bookmark}</div>
                            </div>
                            <h2 className={style.title}>{data.title}</h2>
                            <h2 className={style.actor}>{data.actor} 역</h2>
                            
                            
                            <div className={style.info}>
                                <div className={style.author}>극본 | {data.author}</div>
                                <div className={style.data}>작성일 | {date}</div>

                                <div className={style.cnt}>
                                    <FontAwesomeIcon icon={faEye} />
                                    {data.bookmarkCnt}
                                    <FontAwesomeIcon icon={faUsers} />
                                    {data.participantCnt}
                                </div>
                            </div>

                            <div className={style.line} />

                            <h3 className={style.content}>
                                {data.content}
                            </h3>
                        </div>


                        

                    </div>
                    <div className={style.edit}><QuillEditor /></div>
                </div>
                <div className={style.route}>
                    <div className={style.button} onClick={back} >목록</div>
                    <div className={style.button} onClick={go}>연습</div>
                </div>
            </div>
        </>
    );
}
