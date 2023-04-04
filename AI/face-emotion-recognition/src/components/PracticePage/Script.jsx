import React from 'react';
import style from './Script.module.scss';
import { useNavigate } from 'react-router-dom';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart, faEye, faUsers } from '@fortawesome/free-solid-svg-icons';
import { faHeart as empty } from '@fortawesome/free-regular-svg-icons';
import { useRecoilState } from 'recoil';
import { detailState } from '../../states/practiceFilterState';
import { tokenState } from '../../states/loginState';

import axios from 'axios';

export default function Script({ data }) {

    const [detail, setDetail] = useRecoilState(detailState);
    const [token, setToken] = useRecoilState(tokenState);
    const navigate = useNavigate();

    const bookmark = false ? <FontAwesomeIcon icon={faHeart} /> : <FontAwesomeIcon icon={empty} />

    const move = () => {
        setDetail(data.id);
        navigate('/practice/detail');
    }


    const check = () => {
        axios.get(`https://j8b301.p.ssafy.io/app/bookmark/check`, {
          headers: {
            'Authorization': token
          },
          params: {
            scriptId: data.id
          }
        }).then((res) => {
          let result = res.data.value;
          if (result) {
            axios.delete(`https://j8b301.p.ssafy.io/app/bookmark`, {
              headers: {
                'Authorization': token
              },
              params: {
                'scriptId': data.id
              }
            }).then((res) => {
              bookmark = false;
            })
      
          } else {
            axios.post('https://your-server-url/bookmark', null, {
                headers: {
                    'Authorization': token
                },
                params: {
                    scriptId: data.id
                }
                }).then((response) => {
                console.log(response);
                }).catch((error) => {
                console.error(error);
                });
          }
        })
      }
      

    

    return (
        <div className={style.container} >
            <div className={style.newandbookmark}>
                <div className={style.new}>NEW</div>
                <div className={style.bookmark} onClick={check}>{bookmark}</div>
            </div>
            <div className={style.gogo} onClick={move}>

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
        </div>
    );
}