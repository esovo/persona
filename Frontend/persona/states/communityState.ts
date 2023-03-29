import { atom } from 'recoil';

export const postwritemodal = atom<boolean>({
  key: 'postwritemodal',
  default: false,
});

export const postdetailmodal = atom<boolean>({
  key: 'postdetailmodal',
  default: false,
});

export const posttext = atom<string>({
  key: 'posttext',
  default: '',
});
