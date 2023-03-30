/* eslint-disable react-hooks/rules-of-hooks */
import { useState, FormEvent } from 'react';
import { useRecoilState } from 'recoil';
import { postsState, selectedPostState, postWriteModal, postDetailModal } from '../../states/communityState';
import { useRecoilValue } from 'recoil';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCrown, faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
// import Header from '@/components/Header';
import WriteModal from '../../components/CommunityPage/PostWriteModal';
import DetailModal from '../../components/CommunityPage/PostDetailModal';
import Post from '../../components/CommunityPage/Post';
// import Footer from '@/components/Footer';
import style from './Community.module.scss';
import { faCommentDots, faHeart } from '@fortawesome/free-regular-svg-icons';

export default function List() {
  const [searchQuery, setSearchQuery] = useState('');
  const [showWriteModal, setShowWriteModal] = useRecoilState(postWriteModal);
  const [showDetailModal, setShowDetailModal] = useRecoilState(postDetailModal);
  const posts = useRecoilValue(postsState); // Recoil atom에서 게시물 목록을 가져옵니다.

  const handleSubmit = (event) => {
    event.preventDefault();
    // 여기에 검색어를 사용하는 로직을 추가하세요.
    console.log(`Searching for: ${searchQuery}`);
  };

  const startWriteHandler = () => {
    setShowWriteModal(true);
  };

  const poposts= [
    {
      id: 1,
      user: 'ovo',
      date: '2023-03-24 11:04:30',
      title: '테스트 제목입니다.',
      content: '테스트 글입니다.',
      like: 1,
      comment: 0,
    },
    {
      id: 2,
      user: 'ovo6',
      date: '2023-03-28 11:04:30',
      title: '테스트2 제목입니다.',
      content: '테스트2 글입니다.',
      like: 2,
      comment: 0,
    },
    {
      id: 3,
      user: 'ovovo',
      date: '2023-03-28 11:04:30',
      title: '테스트3 제목입니다.',
      content: '테스트3 글입니다.',
      like: 3,
      comment: 0,
    },
  ];

  return (
    <div className={style.wrapper}>
      {/* <Header /> */}
      {showWriteModal && <WriteModal />}
      {showDetailModal && <DetailModal />}
      <div className={style.intro}>
        <div className={style.title}>커뮤니티</div>
        <div className={style.content}>궁금한 점부터 피드백까지 다양한 이야기를 나눌 수 있습니다.</div>
      </div>
      <div className={style.container}>
        <div className={style.left}>
          <form className={style.searchForm} onSubmit={handleSubmit} autoComplete="off">
            <div className={style.searchInput}>
              <input
                className={style.searchText}
                type="text"
                id="input_search"
                placeholder="글 제목, 글 내용, 작성자 검색"
                maxLength={200}
                autoComplete="off"
                value={searchQuery}
                onChange={(event) => setSearchQuery(event.target.value)}
              />
              <button type="submit" className={style.search}>
                <FontAwesomeIcon icon={faMagnifyingGlass} style={{ color: '#5e5e5e' }} />
              </button>
            </div>
          </form>
          <div className={style.writePost}>
            <button className={style.writeBtn} onClick={startWriteHandler}>
              새로운 글을 남겨보세요.
            </button>
          </div>
          <div className={style.posts}>
            <div className={style.sort}>최신순</div>
            {posts.map((post) => (
              <div key={post.id}>
                <Post key={post.id} {...post} />
              </div>
            ))}
          </div>
        </div>
        <div className={style.right}>
          <div className={style.popular}>
            <div className={style.more}>
              <FontAwesomeIcon icon={faCrown} style={{ color: '#ecc022' }} />
              <div className={style.title}>인기글 TOP3</div>
            </div>
            <div className={style.posts}>
              {poposts.map((post) => (
                <div key={post.id} className={style.post}>
                  <div className={style.content}>
                    <div className={style.title}>{post.title}</div>
                    <div className={style.body}>{post.content}</div>
                    <div className={style.info}>
                      <div className={style.user}>{post.user}</div> |<div className={style.date}>{post.date}</div>
                    </div>
                  </div>
                  <div className={style.itmes}>
                    <div className={style.like}>
                      <FontAwesomeIcon icon={faHeart} style={{ color: '#ce4040' }} />
                      <div>{post.like}</div>
                    </div>
                    <div className={style.comment}>
                      <FontAwesomeIcon icon={faCommentDots} style={{ color: '#5e5e5e' }} />
                      <div>{post.comment}</div>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
      {/* <Footer /> */}
    </div>
  );
}
