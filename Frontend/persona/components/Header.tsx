import Link from 'next/link';
import style from '../styles/Header.module.scss';
import { useRecoilState } from 'recoil';
import User from '../models/user';
import { user, modal } from '../states/loginState';
import { useState } from 'react';
import Modal from './Modal';

export default function Header() {
  const [loginUser, setLoginUser] = useRecoilState(user);
  const [isLogin, setIsLogin] = useState(false);

  const [showModal, setShowModal] = useRecoilState(modal);

  const btnHandler = () => {
    // setShowModal(true);
    setShowModal(true);
    setLoginUser(new User('로그인 유저', '내 닉네임', '인증정보'));
    setIsLogin(!isLogin);
  };
  const logout = () => {
    setLoginUser(null);
    setIsLogin(!isLogin);
  };

  return (
    <nav className={style.nav}>
      <div className={style.home}>
        <div className={style.logo}>
          <img src="logoimg.png" alt="로고다요" width="" height="36" />
        </div>
        <div className={style.title}>
          <Link href="/">PERSONA</Link>
        </div>
      </div>
      {showModal && <Modal />}
      {!isLogin ? (
        <div className={style.menu} />
      ) : (
        <div className={style.menu}>
          <div className={style.menuItem}>
            <Link href="/practice">연기연습</Link>
          </div>
          <div className={style.menuItem}>
            <Link href="/community">커뮤니티</Link>
          </div>
          <div className={style.menuItem}>
            <Link href="/storage">보관함</Link>
          </div>
          <div className={style.menuItem}>
            <Link href="/bookmark">북마크</Link>
          </div>
        </div>
      )}

      <div className={style.loginArea}>
        {!isLogin ? (
          <button className={style.btn} onClick={btnHandler}>
            시작하기
          </button>
        ) : (
          <div className={style.login} onClick={logout}>
            {loginUser?.nickname}
          </div>
        )}
      </div>
    </nav>
  );
}
