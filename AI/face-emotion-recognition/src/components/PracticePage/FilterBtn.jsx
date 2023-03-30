import React from 'react';
import { useRecoilState } from 'recoil';
import { clickedEmotionState, clickedGenreState, clickedBtnState } from '../../states/practiceFilterState';


const FilterBtn= ({ id, label, value }) => {
  const [clickedEmotion, setClickedEmotion] = useRecoilState(clickedEmotionState);
  const [clickedGenre, setClickedGenre] = useRecoilState(clickedGenreState);
  const [clickedBtnIds, setClickedBtnIds] = useRecoilState(clickedBtnState);

  const clickHandler = () => {
    if (id != 1 && id < 8) {
      if (clickedEmotion.includes(value)) {
        setClickedEmotion(clickedEmotion.filter((target) => target !== value));
      } else {
        setClickedEmotion([...clickedEmotion, value]);
      }
    } else if (id > 7) {
      if (clickedGenre.includes(value)) {
        setClickedGenre(clickedGenre.filter((target) => target !== value));
      } else {
        setClickedGenre([...clickedGenre, value]);
      }
    }
    // else {
    //   if (clickedEmotion.length + clickedGenre.length == 11) {
    //     setClickedEmotion([]);
    //     setClickedGenre([]);
    //   }
    // }

    if (clickedBtnIds.includes(id)) {
      setClickedBtnIds(clickedBtnIds.filter((ids) => ids !== id));
    } else {
      setClickedBtnIds([...clickedBtnIds, id]);
    }
  };

  return <button onClick={clickHandler}>{label}</button>;
};

export default FilterBtn;
