import { atom } from 'recoil';

export const clickedFilterState = atom<number[]>({
  key: 'clickedFilterState',
  default: [],
});
