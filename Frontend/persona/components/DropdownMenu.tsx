import { useRecoilState } from 'recoil';
import { dropdownMenuState } from '../states/loginState';
import style from '../styles/DropdownMenu.module.scss';

const DropdownMenu: React.FC<{ items: string[]; onItemClick: (item: string) => void }> = (props) => {
  const [dropdownMenu, setDropdownMenu] = useRecoilState(dropdownMenuState);

  const handler = (item: string) => {
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

// export default function DropdownMenu() {
//   return (
//     <div>
//       <li>마이페이지</li>
//       <li>로그아웃</li>
//     </div>
//   );
// }
