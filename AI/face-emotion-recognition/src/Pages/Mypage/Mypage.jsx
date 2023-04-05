/* eslint-disable default-case */
import style from './Mypage.module.scss';
import BasicList from '../../components/Mypage/BasicList';
import MyInfo from '../../components/Mypage/MyInfo';
import { useState } from 'react';
import Bookmark from '../../components/Mypage/BookMark';
import MyPost from '../../components/Mypage/MyPost';
import Header from '../../components/Common/Header';

const Mypage = () => {
  const [data, setData] = useState('1');

  function Component() {
    switch (data) {
      case '1':
        return <MyInfo />;
      case '2':
        return <MyPost />;
      case '3':
        return <Bookmark />;
    }
  }

  return (
    <div className={style.wrapper}>
      <Header />
      <div className={style.flexBox}>
        <BasicList setData={setData} />
        <Component></Component>
      </div>
    </div>
  );
};

export default Mypage;
