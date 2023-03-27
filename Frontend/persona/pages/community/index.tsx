/* eslint-disable react-hooks/rules-of-hooks */
import { useState, FormEvent } from 'react';
import { useRecoilState } from 'recoil';
import { postmodal } from '../../states/communityState';
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
  ];

  return (
    <>
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
                type="text"
                id="input_search"
                placeholder="글 제목, 글 내용, 작성자 검색"
                maxLength={200}
                autoComplete="off"
                className={style.searchText}
                value={searchQuery}
                onChange={(event) => setSearchQuery(event.target.value)}
              />
              <button type="submit" className="search">
                검색
              </button>
            </div>
          </form>
          <div className={style.writePost}>
            <button onClick={startHandler}>새로운 글을 남겨보세요.</button>
          </div>
          <div className={style.post}>
            {posts.map((post) => (
              <div key={post.id}>
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
        <div className="right"></div>
      </div>
      <Footer />
    </>
  );
}
