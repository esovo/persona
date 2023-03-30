import { atom } from 'recoil';
// import User from '../models/user';

export const user = atom({
  key: 'user',
  default: null,
});

export const modal = atom({
  key: 'loginmodal',
  default: false,
});

export const dropdownMenuState = atom({
  key: 'dropdownMenuState',
  default: '',
});

export const tokenState = atom({
  key: 'tokenState',
  default: '',
});
