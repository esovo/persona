// import { useState } from 'react';
import style from './LoginModal.module.scss';
import { useRecoilState } from 'recoil';
import { modal } from '../../states/loginState';

const API_BASE_URL = 'j8b301.p.ssafy.io:8080';

export default function Modal() {
  const [showModal, setShowModal] = useRecoilState(modal);
  // const [loading, setLoading] = useState(false);

  const shutModal = () => {
    setShowModal(false);
  };

  return (
    <>
      {showModal && (
        <div className={style.back} onClick={shutModal}>
          <div className={style.container}>
            <div className={style.banner}>로그인 고르셈</div>
            <div className={style.loginSelect}>
              <a href={`http://${API_BASE_URL}/oauth2/authorization/google`}>
                <img src="Modal_google.png" alt="구글로그인버튼" className={style.google} width="300" />
              </a>
              <a href={`http://${API_BASE_URL}/oauth2/authorization/naver`}>
                <img src="Modal_naver.png" alt="네이버로그인버튼" className={style.naver} width="300" />
              </a>
              <a href={`http://${API_BASE_URL}/oauth2/authorization/google`}>
                <img src="Modal_kakao.png" alt="카카오로그인버튼" className={style.kakao} width="300" />
              </a>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
