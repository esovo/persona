/* eslint-disable react-hooks/rules-of-hooks */
import { useState, FormEvent } from 'react';
import { useRecoilState } from 'recoil';
import { postmodal } from '../../states/communityState';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { IconProp } from '@fortawesome/fontawesome-svg-core';
import Header from '@/components/Header';
import Modal from '@/components/PostModal';
import Footer from '@/components/Footer';
import style from '../../styles/Community.module.scss';

export default function List() {
  const [searchQuery, setSearchQuery] = useState<string>('');
  const [showModal, setShowModal] = useRecoilState(postmodal);

  type Post = {
    id: number;
    nickname: string;
    time: string;
    title: string;
    body: string;
    like: number;
    comment: number;
  };

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    // 여기에 검색어를 사용하는 로직을 추가하세요.
    console.log(`Searching for: ${searchQuery}`);
  };

  const startHandler = () => {
    setShowModal(true);
  };

  const posts: Post[] = [
    { id: 1, nickname: 'ovo', time: '2023-03-24', title: '테스트', body: '테스트 글입니다.', like: 1, comment: 0 },
    { id: 2, nickname: 'ovo6', time: '2023-03-28', title: '테스트2', body: '테스트2 글입니다.', like: 2, comment: 0 },
    { id: 3, nickname: 'ovovo', time: '2023-03-28', title: '테스트3', body: '테스트3 글입니다.', like: 3, comment: 0 },
    { id: 4, nickname: 'ovovo', time: '2023-03-28', title: '테스트4', body: '테스트4 글입니다.', like: 4, comment: 0 },
    { id: 5, nickname: 'ovovo', time: '2023-03-28', title: '테스트5', body: '테스트5 글입니다.', like: 5, comment: 0 },
  ];

  return (
    <div className={style.wrapper}>
      <Header />
      {showModal && <Modal />}
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
                <FontAwesomeIcon icon={faMagnifyingGlass as IconProp} style={{ color: '#5e5e5e' }} />
              </button>
            </div>
          </form>
          <div className={style.writePost}>
            <button className={style.writeBtn} onClick={startHandler}>
              새로운 글을 남겨보세요.
            </button>
          </div>
          <div className={style.posts}>
            {posts.map((post) => (
              <div key={post.id} className={style.post}>
                <h3>{post.title}</h3>
                <div>{post.nickname}</div>
                <div>{post.body}</div>
                <div className={style.itmes}>
                  <div>{post.like}</div>
                  <div>{post.comment}</div>
                </div>
              </div>
            ))}
          </div>
        </div>
        <div className={style.right}>
          <div className={style.popular}>
            <div className={style.more}>
              <div>인기글</div>
              <div>더보기</div>
            </div>
            <div className={style.posts}>
              {posts.map((post) => (
                <div key={post.id} className={style.post}>
                  <div>{post.nickname}</div>
                  <h3>{post.title}</h3>
                  <div>{post.body}</div>
                  <div className={style.itmes}>
                    <div>{post.like}</div>
                    <div>{post.comment}</div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}
