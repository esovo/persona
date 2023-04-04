import { useRecoilState } from 'recoil';

import style from './Savepage.module.scss';
import Button from '@mui/material/Button';
import ReactDiffViewer,{ DiffMethod } from 'react-diff-viewer';
import { useState } from 'react';
import Header from "../../components/Common/Header";

export default function Savepage(props) {
  const [videourl,setvideourl]=useState("https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/uservideo.mp4");
  const [imgurl,setimgurl]=useState("https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/fileName.jpg");
  const [text,setText] =useState("1");
  const [recordtext,setRecordtext] =useState("2");

  const handleClick = (event) => {
    const x = event.clientX;
    const video = document.querySelector('video');
    const hole = 1280 - 170;
    const k = x - 170;
    console.log(x);
    console.log(k);
    const res = (k * video.duration) / hole;
    console.log(video.duration);
    console.log(res);
    // const clickedData = data.find((d) => d.x === x);
    // const videoTime = clickedData.x / data.length * videoLength;

    // HTML5 비디오 요소를 찾는다.
    // 비디오를 이동.
    if (k >= 0 && k < 1280) {
      video.currentTime = res;
    }
  };

  return(
    <>
    <Header/>
      <div className={style.savepage}>    
        <video 
          className="recordvideo" 
          src={videourl} 
          autoPlay
          controls
        />
        <img src={imgurl} onClick={(e) => {
          handleClick(e);
        }}/>
        <div className={style.scriptComponent}>
        <div>
              <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                <h1 style={{ fontSize: '30px', color: 'black' }}>대본</h1>
                <h1 style={{ fontSize: '30px', color: 'black' }}>음성인식결과</h1>
              </div>
              <hr />
              <hr />

              <ReactDiffViewer
                styles={{
                  marker: {
                    display: 'none',
                  },
                  lineNumber: {
                    display: 'none',
                  },
                }}
                oldValue={text}
                newValue={recordtext}
                splitView={true}
                compareMethod={DiffMethod.WORDS}
              />
            </div>

        </div>
      </div>
      </>
  );
};

