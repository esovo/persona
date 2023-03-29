import style from '../styles/Footer.module.scss';

export default function Footer() {
  return (
    <>
      <div className={style.items}>
        <div>이용약관</div>
        <div>개인정보 처리방침</div>
        <div>Contact Us</div>
        <div>About Us</div>
      </div>
    </>
  );
}
