import style from './Mypage.module.scss';
import BasicList from '../../components/Mypage/BasicList';
import MyInfo from '../../components/Mypage/MyInfo';
import { useState } from 'react';
import Bookmark from '../../components/Mypage/BookMark';

const Mypage = () => {
  const [data, setData] = useState("1");
  
  function Component2(){
    switch (data){
      case '1' :
        return <MyInfo/>
      case '2' :
        return <Bookmark/>
      case '3' : 
        return <h4>그냥</h4>
    }
  }

  return(
    <div className={style.flexBox}>    
    <BasicList setData={setData}/>
    <Component2></Component2>
    </div>
  );
};

export default Mypage;


