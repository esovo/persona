import { useRecoilState } from 'recoil';

import style from './Savepage.module.scss';
import Button from '@mui/material/Button';
import ReactDiffViewer,{ DiffMethod } from 'react-diff-viewer';
import { useState } from 'react';

export default function Savepage(props) {
  const [bloburl,setbloburl]=useState("");
  const [text,setText] =useState();
  const [recordtext,setRecordtext] =useState("");

  return(
    <div>    
      <div>
        <video 
          className="recordvideo" 
          src={bloburl} 
          autoPlay
          controls
        />
        <img src=""></img>
        <div className="scriptComponent">
          <ReactDiffViewer
            styles={{
              marker: {
                display:"none"
              },
              line: {
                // display:"none"
              }}
            }
            oldValue={text} 
            newValue={recordtext} 
            splitView={true} 
            compareMethod={DiffMethod.WORDS}
           />

        </div>
        
      </div>
    </div>
  );
};

