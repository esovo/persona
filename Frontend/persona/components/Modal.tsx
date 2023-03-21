import axios from 'axios';
import style from './Modal.module.scss';
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
            <div className={style.banner}>Start with your Social...</div>
            <div className={style.loginSelect}>
              <div className={style.google} onClick={googleLogin}>
                구글이요
              </div>
              <div className={style.naver} onClick={naverLogin}>
                네이버요
              </div>
              <div className={style.kakao} onClick={kakaoLogin}>
                카카오요
              </div>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
