import style from "./MyInfo.module.scss";
import { useRecoilState } from "recoil";
import { user } from "../../states/loginState"; 

const MyInfo = () => {
    const [userInfo, setUserInto] = useRecoilState(user);
    return(
        <div className={style.box}>
            <h1>내정보</h1>
            <hr/>
            <div className={style.userInfo}>
                {/* <img src={userInfo.img}/> */}
                <div className={style.flexBox}>
                    <div>
                        <div>닉네임</div>
                        <div>이메일</div>
                        <div>소셜로그인</div>
                    </div>
                    <div className={style.userText}>
                        {/* <div>{userInfo.nickname}</div>
                        <div>{userInfo.email} dumydumy@naver.com</div>
                        <div>{userInfo.email} Naver</div> */}
                    </div>
                </div>
            </div>
        </div>
    );
} 

export default MyInfo;