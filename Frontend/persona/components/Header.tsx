import Link from 'next/link';
import style from './Header.module.scss';
import { atom } from 'recoil';
// import axios from 'axios';

export default function Header() {
  // const login = () => {
  //   axios
  // }

  const isLogin = atom({
    key: 'isLogin',
    default: false,
  });

  return (
    <nav className={style.nav}>
      <div className={style.logo}>
        <Link href="/">PERSONA</Link>
      </div>
      {isLogin ? (
        <>
          <div className={style.menu}>
            <Link href="/practice">연기연습</Link>
            <Link href="/community">커뮤니티</Link>
            <Link href="/storage">보관함</Link>
            <Link href="/bookmark">북마크</Link>
          </div>
          <button className={style.login}>시작하기</button>
        </>
      ) : (
        <button>후하후하</button>
      )}
    </nav>
  );
}
