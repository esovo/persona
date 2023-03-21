import Header from '@/components/Header';
import style from '../styles/main.module.scss';
import Better from '../components/Better';

export default function Home() {
  return (
    <>
      <Header />
      <div className={style.container}>
        <div className={style.centerBanner}> your Persona</div>
      </div>
      <div id="modal-root" />
      <div>이곳은 메인 페이지입니다</div>
      <Better />
    </>
  );
}
