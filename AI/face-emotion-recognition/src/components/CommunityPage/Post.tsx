/* eslint-disable @typescript-eslint/no-unused-vars */
import { useRecoilState } from 'recoil';
import { postDetailModal, selectedPostState } from '../states/communityState';
import style from '../styles/Community.module.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCommentDots, faHeart } from '@fortawesome/free-regular-svg-icons';

interface PostProps {
  id: number;
  user: string;
  date: string;
  title: string;
  content: string;
  like: number;
  comment: number;
}

const Post = ({ id, user, date, title, content, like, comment }: PostProps) => {
  const [selectedPost, setSelectedPost] = useRecoilState(selectedPostState);
  const [showDetailModal, setShowDetailModal] = useRecoilState(postDetailModal);

  const openModal = () => {
    setSelectedPost({ id, user, date, title, content, like, comment });
    setShowDetailModal(true);
  };

  return (
    <div className={style.post}>
      <div className={style.content} onClick={openModal}>
        <div className={style.title}>{title}</div>
        <div className={style.body}>{content}</div>
        <div className={style.info}>
          <div className={style.user}>{user}</div> |<div className={style.date}>{date}</div>
        </div>
      </div>
      <div className={style.itmes}>
        <div className={style.like}>
          <FontAwesomeIcon icon={faHeart} style={{ color: '#ce4040' }} />
          <div>{like}</div>
        </div>
        <div className={style.comment}>
          <FontAwesomeIcon icon={faCommentDots} style={{ color: '#5e5e5e' }} />
          <div>{comment}</div>
        </div>
      </div>
    </div>
  );
};

export default Post;
