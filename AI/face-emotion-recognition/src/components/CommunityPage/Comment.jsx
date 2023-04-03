import { useRecoilState } from 'recoil';
import style from './Comment.module.scss';

const Comment = ({ id, nickname, createDate, content }) => {
  return (
    <div className={style.comment}>
      <div className={style.info}>
        <div className={style.profile}>
          <img src="user.png" alt="user" width="32" />
        </div>
        <div className={style.items}>
          <div className={style.nickname}>{nickname}</div>
          <div className={style.content}>{content}</div>
          <div className={style.date}>{createDate}</div>
        </div>
      </div>
    </div>
  );
};

export default Comment;
