import { useRecoilState } from 'recoil';
import style from '../../Pages/Community/Community.module.scss';

const Comment = ({ id, nickname, createDate, content }) => {
  return (
    <div className={style.comment}>
      <div className={style.content}>
        <div className={style.content}>{content}</div>
        <div className={style.info}>
          <div className={style.nickname}>{nickname}</div>|<div className={style.date}>{createDate}</div>
        </div>
      </div>
    </div>
  );
};

export default Comment;
