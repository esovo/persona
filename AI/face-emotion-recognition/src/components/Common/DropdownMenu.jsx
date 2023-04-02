import { useRecoilState } from 'recoil';
import { dropdownMenuState } from '../../states/loginState';
import style from './DropdownMenu.module.scss';

const DropdownMenu = (props) => {
  const [dropdownMenu, setDropdownMenu] = useRecoilState(dropdownMenuState);


  const handler = (item) => {
    setDropdownMenu('');
    props.onItemClick(item);
  };

  return (
    <div>
      <button className={style.btn} onClick={() => setDropdownMenu(dropdownMenu === '' ? 'active' : '')}>
        {dropdownMenu === '' ? '▼' : '▲'}
      </button>
      {dropdownMenu == 'active' ? (
        <ul className={style.show}>
          {props.items.map((item) => (
            <li key={item} onClick={() => handler(item)}>
              {item}
            </li>
          ))}
        </ul>
      ) : (
        <></>
      )}
    </div>
  );
};

export default DropdownMenu;
