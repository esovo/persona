import style from "./MyInfo.module.scss";
import { useRecoilState } from "recoil";
import { user } from "../../states/loginState"; 
import BookScript from './BookScript';

const BookMark = () => {
    const [userInfo, setUserInto] = useRecoilState(user);
    return(
        <div className={style.box}>
            <h1>내정보</h1>
            <hr/>
            <div className={style.userInfo}>
                <div className={style.flexBox}>
                  <div>
                      <BookScript></BookScript>
                    </div>
                    <div className={style.userText}>
                    </div>
                </div>
            </div>
        </div>
    );
} 

export default BookMark;