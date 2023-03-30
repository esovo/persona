import React from 'react';
import { useRecoilValue, useRecoilState, useSetRecoilState } from 'recoil';

import {
  clickedEmotionState,
  clickedGenreState,
  clickedBtnState,
  optionState,
  keywordState,
  sortingState,
  pageState,
} from '../../states/practiceFilterState';
// import axios from 'axios';

import Header from '../../components/Common/Header';
// import Footer from '@/components/Footer';
import FilterBtn from '../../components/PracticePage/FilterBtn';
// import Script from '@/components/Script';

// import scriptmodel from '@/models/script';
import style from './Practice.module.scss';

const List = () => {
  const clickedEmotion = useRecoilValue(clickedEmotionState);
  const clickedGenre = useRecoilValue(clickedGenreState);
  const clickedBtn = useRecoilValue(clickedBtnState);
  const setClickedOption = useSetRecoilState(optionState);
  const setClickedKeyword = useSetRecoilState(keywordState);
  const setClickedSorting = useSetRecoilState(sortingState);
  const [page, setPage] = useRecoilState(pageState)

  const searchHandler = (event) => {
    setClickedOption(event.target.value);
  };

  // const handler1 = (event: React.MouseEvent<HTMLElement>) => {
  //   setClickedSorting('최신순');
  // };

  // const handler2 = (event: React.MouseEvent<HTMLElement>) => {
  //   setClickedSorting('인기순');
  // };

  // const handler3 = (event: React.MouseEvent<HTMLElement>) => {
  //   setClickedSorting('참여순');
  // };

  // const handler4 = (event: React.MouseEvent<HTMLElement>) => {
  //   setClickedSorting('조회순');
  // };

  const keywordHandler = (event) => {
    setClickedKeyword(event.target.value);
  };

  return (
    <>
      <Header />
      <div className={style.container}>
        <div className={style.banner}>
          <img className={style.bookimg} src="./Practice_banner.png" alt="책" />
          <div className={style.text}>
            <h1>스크립트</h1>
            <div className={style.subtitle}>내가 원하는 스크립트를 고를 수 있어요</div>
          </div>
        </div>
        <div className={style.filter}>
          <div className={style.search}>
            <select name="findby" onChange={searchHandler}>
              <option value="title">제목</option>
              <option value="content">내용</option>
              <option value="work">작품</option>
              <option value="character">배역</option>
            </select>
            {/* <div className={style.searchInput}>
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
            </div> */}
            <input className={style.searchbar} placeholder="search" onChange={keywordHandler} />
            <button className={style.searchbtn}>
              <img src="Header_logo.png" alt="임시버튼" width="50px" height="30px" />
            </button>
          </div>
          {clickedEmotion.length > 0 ? <p>클릭된 감정 : {clickedEmotion.join(', ')}</p> : <p>비어있음</p>}
          {clickedGenre.length > 0 ? <p>클릭된 장르 : {clickedGenre.join(', ')}</p> : <p>비어있음</p>}
          {clickedBtn.length > 0 ? <p>클릭된 버튼 : {clickedBtn.join(', ')}</p> : <p>비어있음</p>}
          <div className={style.filterButton}>
            <FilterBtn id={1} label="전체" value="" />
            <FilterBtn id={2} label="#슬픈" value="슬픔" />
            <FilterBtn id={3} label="#당황한" value="당황" />
            <FilterBtn id={4} label="#화난" value="분노" />
            <FilterBtn id={5} label="#기쁜" value="기쁨" />
            <FilterBtn id={5} label="#무서운" value="불안" />
            <FilterBtn id={6} label="#혐오스러운" value="상처" />
            <FilterBtn id={7} label="#중립" value="중립" />
            <FilterBtn id={8} label="#영화" value="영화" />
            <FilterBtn id={9} label="#연극" value="연극" />
            <FilterBtn id={10} label="#뮤지컬" value="뮤지컬 " />
            <FilterBtn id={11} label="#드라마" value="드라마" />
          </div>
          <div className={style.options}></div>
        </div>
        <div className={style.script}>
          <div className={style.sorting}>
            {/* <div onClick={handler1}>최신순</div>|<div onClick={handler2}>인기순</div>|
            <div onClick={handler3}>참여순</div>|<div onClick={handler4}>조회순</div> */}
          </div>
          <div className={style.scripts}></div>
        </div>
      </div>
    </>
  );
};

export default List;



//   const scripts = await axios.put("/script/all", {
//     option: clickedOption,
//     keyword: clickedKeyword,
//     emotion: clickedEmotion,
//     genre: clickedGenre,
//     page: page,
//     sort: clickedSort,
//   })

