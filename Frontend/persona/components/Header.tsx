import Link from 'next/link';
import style from '../styles/Header.module.scss';
import { useRecoilState } from 'recoil';
import User from '../models/user';
import { user, modal } from '../states/loginState';
import { useState } from 'react';
import Modal from './Modal';
import DropdownMenu from './DropdownMenu';
import { useRouter } from 'next/router';

export default function Header() {
  const [loginUser, setLoginUser] = useRecoilState(user);
  const [showModal, setShowModal] = useRecoilState(modal);
  const [isLogin, setIsLogin] = useState(false);
  const [isDropdown, setIsDropdown] = useState(false);

  const router = useRouter();

  const startHandler = () => {
    setShowModal(true);
    setLoginUser(new User('로그인 유저', '내 닉네임이다요', '인증정보'));
    setIsLogin(!isLogin);
  };

  const logoutHandler = () => {
    setLoginUser(null);
    setIsLogin(!isLogin);
  };

  const itemClickHandler = (item: string) => {
    if (item === 'My Page') {
      router.push('/mypage');
    } else if (item === 'Log Out') {
      logoutHandler();
    }
  };

  const dropdownHandler = () => {
    setIsDropdown(!isDropdown);
  };

  return (
    <nav className={style.nav}>
      <div className={style.home}>
        <div className={style.logo}>
          <img src="Header_logo.png" alt="로고다요" width="60px" height="40px" />
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
