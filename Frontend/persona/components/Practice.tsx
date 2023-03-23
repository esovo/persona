import style from '../styles/Practice.module.scss';

export default function Practice() {
  return (
    <div className={style.container}>
      <div className={style.banner}>
        <h1>대본</h1>
        <div className={style.subtitle}>내가 원하는 대본을 고를 수 있어요</div>
      </div>
      <div className={style.filter}>
        <select name="findby">
          <option value="title">제목</option>
          <option value="content">내용</option>
          <option value="work">작품</option>
          <option value="character">배역</option>
        </select>
        <div className={style.search}>
          <input className={style.searchbar} placeholder="search" />
          <button className={style.searchbtn}>
            <img src="Header_logo.png" alt="임시버튼" width="100px" height="60px" />
          </button>
        </div>
        <div className={style.options}></div>
      </div>
      <div className={style.script}>
        <div className={style.sorting}></div>
        <div className={style.scripts}></div>
      </div>
    </div>
  );
}
