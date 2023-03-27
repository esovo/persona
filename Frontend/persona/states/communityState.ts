import { atom } from 'recoil';

export const postmodal = atom<boolean>({
  key: 'postmodal',
  default: false,
});

export const posttext = atom<string>({
  key: 'posttext',
  default: '',
});
