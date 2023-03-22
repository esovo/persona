import axios from 'axios';
import style from '../styles/Modal.module.scss';
import { useRecoilState } from 'recoil';
import { modal } from '../states/loginState';

export default function Modal() {
  const [showModal, setShowModal] = useRecoilState(modal);

  const googleLogin = () => {
    axios.get('http://localhost:8080/oauth2/authorization?redirect_uri=http://localhost:8080/oauth2/callback/google');
  };

  const naverLogin = () => {
    axios.get(
      'http://localhost:8080/oauth2/authorization/naver?redirect_uri=http://localhost:8080/oauth2/callback/naver',
    );
  };

  const kakaoLogin = () => {
    axios.get('http://localhost:8080/oauth2/authorization/kakao?redirect_uri={baseUri}/oauth2/callback/kakao');
  };

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
              <img
                src="Modal_google.png"
                alt="구글로그인버튼"
                className={style.google}
                onClick={googleLogin}
                width="300"
              />
              <img
                src="Modal_naver.png"
                alt="네이버로그인버튼"
                className={style.naver}
                onClick={naverLogin}
                width="300"
              />
              <img
                src="Modal_kakao.png"
                alt="카카오로그인버튼"
                className={style.kakao}
                onClick={kakaoLogin}
                width="300"
              />
            </div>
          </div>
        </div>
      )}
    </>
  );
}
