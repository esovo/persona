import { atom } from 'recoil';
import User from '../models/user';

export const user = atom<User | null>({
  key: 'user',
  default: null,
});

export const modal = atom<boolean>({
  key: 'loginmodal',
  default: false,
});

export const dropdownMenuState = atom({
  key: 'dropdownMenuState',
  default: '',
});

export const tokenState = atom<string>({
  key: 'tokenState',
  default: '',
});
