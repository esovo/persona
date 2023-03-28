import React from 'react';
import style from '../styles/Script.module.scss';

interface ScriptProps {
  id: number;
  title: string;
  author: string;
  content: string;
  registrant: string;
  view_num: number;
  register_date: string;
  update_date: string;
  genre: string;
  emotion: string;
}

const Script: React.FC<ScriptProps> = ({
  title,
  author,
  content,
  registrant,
  view_num,
  register_date,
  update_date,
  genre,
  emotion,
}) => {
  return (
    <div className={style.container}>
      <div className={style.newandmark}></div>
      <div className={style.date}>작성일 | {register_date}</div>
      <div className={style.title}>{title}</div>
      <div className={style.actor}></div>
      <div className={style.category}>
        <div className={style.card}>#{genre}</div>
        <div className={style.card}>#{emotion}</div>
      </div>
      <div className={style.line} />
      <div className={style.writerandinfo}>
        <div className={style.writer}>{registrant}</div>
        <div className={style.info}>{view_num}</div>
      </div>
      <div className={style.etc}>
        {author} {content} {update_date}
      </div>
    </div>
  );
};

export default Script;
