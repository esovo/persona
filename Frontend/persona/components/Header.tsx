import Link from 'next/link';
import style from './Header.module.scss';
import { atom, useRecoilState } from 'recoil';
import { recoilPersist } from 'recoil-persist';

export default function Header() {
  const { persistAtom } = recoilPersist();

  const loginState = atom({
    key: 'loginState',
    default: true,
    effects_UNSTABLE: [persistAtom],
  });

  const userName = atom({
    key: 'userName',
    default: 'user',
    effects_UNSTABLE: [persistAtom],
  });

  const [isLogin, setIsLogin] = useRecoilState(loginState);
  const user = useRecoilState(userName);

  const btnHandler = () => {
    setIsLogin(!isLogin);
  };

  return (
    <nav className={style.nav}>
      <div className={style.logo}>
        <Link href="/">PERSONA</Link>
      </div>
      {!isLogin ? (
        <div className={style.menu}>
          <Link href="/practice">연기연습</Link>
          <Link href="/community">커뮤니티</Link>
          <Link href="/storage">보관함</Link>
          <Link href="/bookmark">북마크</Link>
        </div>
      ) : (
        <div className={style.menu} />
      )}
      {isLogin ? (
        <button className={style.login} onClick={btnHandler}>
          시작하기
        </button>
      ) : (
        <button className={style.login} onClick={btnHandler}>
          {user}
        </button>
      )}
    </nav>
  );
}
