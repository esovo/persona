import React from 'react';
import { useRecoilState } from 'recoil';
import { clickedFilterState } from '../states/practiceFilterState';

interface ButtonProps {
  id: number;
  label: string;
}

const FilterBtn: React.FC<ButtonProps> = ({ id, label }) => {
  const [clickedBtnIds, setClickedBtnIds] = useRecoilState(clickedFilterState);

  const clickHandler = () => {
    if (clickedBtnIds.includes(id)) {
      setClickedBtnIds(clickedBtnIds.filter((ids) => ids !== id));
    } else {
      setClickedBtnIds([...clickedBtnIds, id]);
    }
  };

  return <button onClick={clickHandler}>{label}</button>;
};

export default FilterBtn;
