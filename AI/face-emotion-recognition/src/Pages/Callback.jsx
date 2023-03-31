import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { tokenState } from '../states/loginState';

export default function Callback() {
    const navigate = useNavigate();
    const location = useLocation();
    const param = new URLSearchParams(location.search).get('token');
    const setToken = useSetRecoilState(tokenState);

    useEffect(() => {
        console.log("안녕");
        console.log(param);
        setToken(param);
        // navigate('/');
    }, [])
    return <div>Now Loading...</div>
};