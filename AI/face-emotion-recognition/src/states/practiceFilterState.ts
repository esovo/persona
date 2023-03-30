import { atom } from 'recoil';
import script from '../models/script';

export const clickedEmotionState = atom<string[]>({
  key: 'clickedEmotionState',
  default: [],
});

export const clickedGenreState = atom<string[]>({
  key: 'clickedGenreState',
  default: [],
});

export const clickedBtnState = atom<number[]>({
  key: 'clickedBtnState',
  default: [],
});

export const pageState = atom<number>({
  key: 'pageState',
  default: 0,
});

export const scriptState = atom<script[]>({
  key: 'scriptState',
  default: [],
});

export const optionState = atom<string>({
  key: 'optionState',
  default: '제목',
});

export const keywordState = atom<string>({
  key: 'keywordState',
  default: '',
});

export const sortingState = atom<string>({
  key: 'sortingState',
  default: '최신순',
});
