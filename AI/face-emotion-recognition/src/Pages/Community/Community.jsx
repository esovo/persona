/* eslint-disable react-hooks/rules-of-hooks */
import { useState } from 'react';
import { useRecoilState } from 'recoil';
import { useRecoilValue } from 'recoil';
import { postsState, postWriteModal, postDetailModal, popostsState } from '../../states/communityState';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCrown, faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import Header from '../../components/Common/Header';
import WriteModal from '../../components/CommunityPage/PostWriteModal';
import DetailModal from '../../components/CommunityPage/PostDetailModal';
import Post from '../../components/CommunityPage/Post';
// import Footer from "../../components/Common/Footer";
import style from './Community.module.scss';

export default function List() {
  const [searchQuery, setSearchQuery] = useState('');
  const [showWriteModal, setShowWriteModal] = useRecoilState(postWriteModal);
  const [showDetailModal, setShowDetailModal] = useRecoilState(postDetailModal);
  const posts = useRecoilValue(postsState); // Recoil atom에서 게시물 목록을 가져옵니다.
  const poposts = useRecoilValue(popostsState);

  const handleSubmit = (event) => {
    event.preventDefault();
    // 여기에 검색어를 사용하는 로직을 추가하세요.
    console.log(`Searching for: ${searchQuery}`);
  };

  const startWriteHandler = () => {
    setShowWriteModal(true);
  };

  return (
    <div className={style.wrapper}>
      <Header />
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
              {poposts.map((popost) => (
                <div key={popost.id}>
                  <Post key={popost.id} {...popost} />
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
