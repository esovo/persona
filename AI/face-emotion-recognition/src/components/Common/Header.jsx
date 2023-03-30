import { useRecoilState, useRecoilValue } from 'recoil';
import { user, modal, tokenState } from '../../states/loginState';
import { useState, useEffect } from 'react';

import style from './Header.module.scss';
// import User from '../models/user';
import Modal from './LoginModal';
import DropdownMenu from './DropdownMenu';
import { Link, useNavigate, useLocation } from "react-router-dom";


export default function Header() {
  const [loginUser, setLoginUser] = useRecoilState(user);
  const [showModal, setShowModal] = useRecoilState(modal);
  const [isLogin, setIsLogin] = useState(false);
  const [isDropdown, setIsDropdown] = useState(false);
  const token = useRecoilValue(tokenState);

  const navigate = useNavigate();
  const location = useLocation();

  const startHandler = () => {
    setShowModal(true);
    setLoginUser('로그인 유저', '내 닉네임이다요', '인증정보');
    setIsLogin(!isLogin);
  };

  const logoutHandler = () => {
    setLoginUser(null);
    setIsLogin(!isLogin);
  };

  const itemClickHandler = (item) => {
    if (item === 'My Page') {
      navigate('/mypage');
    } else if (item === 'Log Out') {
      logoutHandler();
    }
  };

  const dropdownHandler = () => {
    setIsDropdown(!isDropdown);
  };

  useEffect(() => {
    const unlisten = navigate((location) => {
      const headers = new Headers();
      for (const [key, value] of Object.entries(location.state.headers)) {
        headers.append(key, value);
      }

      console.log('헤더 : ', headers);
      // 토큰, 에러 키값 가져와 로직 구현하기

    });
    return () => {
      unlisten();
    }
  }, [navigate]);
  // 위 로직은 메인페이지에 옮겨야 할 수 있음

  return (
    <nav className={style.nav}>
      <div className={style.home}>
        <div className={style.logo}>
          <img src="/Header_logo.png" alt="로고다요" width="80" height="70" />
        </div>
        <div className={style.title}>
          <Link to="/">PERSONA</Link>
        </div>
      </div>
      {showModal && <Modal />}
      {!isLogin ? (
        <div className={style.menu} />
      ) : (
        <div className={style.menu}>
          <div className={style.menuItem}>
            <Link to="/practice">연기연습</Link>
          </div>
          <div className={style.menuItem}>
            <Link to="/community">커뮤니티</Link>
          </div>
          <div className={style.menuItem}>
            <Link to="/storage">보관함</Link>
          </div>
          <div className={style.menuItem}>
            <Link to="/bookmark">북마크</Link>
          </div>
        </div>
      )}

      <div className={style.loginArea}>
        {!isLogin ? (
          <button className={style.btn} onClick={startHandler}>
            시작하기
          </button>
        ) : (
          <div className={style.login} onClick={dropdownHandler}>
            <div className={style.profile} />
            <div className={style.nick}>{loginUser?.nickname}</div>
            {/* <div className={style.left}>
            </div> */}
            <div className={style.right}>
              <DropdownMenu items={['My Page', 'Log Out']} onItemClick={itemClickHandler} />
            </div>
          </div>
        )}
      </div>
    </nav>
  );
}
