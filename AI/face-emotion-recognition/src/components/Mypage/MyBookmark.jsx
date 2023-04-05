import { useEffect, useState } from 'react';
import { mypageApis } from '../../apis/mypageApis';
import { tokenState } from '../../states/loginState';
import { useRecoilValue } from 'recoil';
import style from './MyBookmark.module.scss';
import BookScript from './BookScript';
import axios from 'axios';

export default function MyPost() {
  const [scripts, setScripts] = useState([]);
  const BASE_URL = 'https://j8b301.p.ssafy.io';
  const token = useRecoilValue(tokenState);

  useEffect(() => {
    const page = 0;
    axios
      .get(BASE_URL + mypageApis.MY_BOOKMARK_GET_API(page), {
        headers: {
          Authorization: token,
        },
      })
      .then((res) => {
        console.log(res.data.value.content);
        setScripts(res.data.value.content);
      });
  }, []);

  return (
    <div className={style.outline}>
      <div className={style.header}>북마크</div>
      <div className={style.scripts}>
        {scripts.map((script) => (
          <BookScript key={script.id} {...script} />
        ))}
      </div>
    </div>
  );
}
