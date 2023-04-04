import style from './Mypage.module.scss';
import BasicList from '../../components/Mypage/BasicList';
import MyInfo from '../../components/Mypage/MyInfo';

const Mypage = () => {
  return(
    <div className={style.flexBox}>    
    <BasicList/>
    <MyInfo/>
    </div>
  );
};

export default Mypage;


