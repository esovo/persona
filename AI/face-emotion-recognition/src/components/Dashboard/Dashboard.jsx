import { useEffect, useState } from 'react';
import { AnimatePresence } from 'framer-motion';
import VideoComponent from '../VideoComponent';
import RealTimeEmotion from '../RealTimeEmotion';
import { useDashboardContext } from './DashboardContext';
import { useRecoilState, useRecoilValue } from 'recoil';
import { Settings, SettingsModal, useSettingsContext } from '../Settings';
import RecordedExpressionsModal from '../Recording';
import Spinner from '../Spinner/Spinner';
import style from './Dashboard.module.scss';
import ScriptText from '../Script/ScriptText';
import { useLocation } from 'react-router';
import axios from 'axios';
import { tokenState, user } from '../../states/loginState';
import '../../index.css';

import { writeState } from '../../states/practiceFilterState';

const Dashboard = (props) => {
  const { pathname } = useLocation();
  const [text, setText] = useState();
  const { loadedModels, setLoadedModels, recordedExpressionsVisible } = useDashboardContext();
  const { settingsVisible, webcamOff } = useSettingsContext();
  const token = useRecoilValue(tokenState);
  const [write, setWrite] = useRecoilState(writeState);
  const name = pathname.substring(11);
  const [myuser, setMyuser] = useRecoilState(user);

  // Loads the essential models required for face detection, face landmarks detection
  // when the component is just mounted
  useEffect(() => {
    console.log(token);
    axios
      .get(`https://j8b301.p.ssafy.io/app/script?scriptId=${name}`, {
        headers: {
          Authorization: token,
        },
      })
      .then((response) => {
        setText(response.data.value.content);
      });
  }, []);

  return (
    // loadedModels?
    <div className={style.dashboard}>
      <div className={style.dashboardLeft}>
        <div className={style.video}>
          <VideoComponent text={text} scriptid={name} />
        </div>
      </div>

      <div className={style.flexBox}>
        <div className={style.emotiongraph}>
          {webcamOff ? (
            <></>
          ) : (
            <>
              <div className={style.dashboardRight}>
                <div className={style.realtimeEmotion}>
                  <RealTimeEmotion />
                </div>
              </div>
              <AnimatePresence
                // Disable any initial animations on children that
                // are present when the component is first rendered
                initial={false}
                // Only render one component at a time.
                // The exiting component will finish its exit
                // animation before entering component is rendered
                exitBeforeEnter={true}
                // Fires when all exiting nodes have completed animating out
                onExitComplete={() => null}>
                {settingsVisible && <SettingsModal />}
              </AnimatePresence>
              <AnimatePresence initial={false} exitBeforeEnter={true} onExitComplete={() => null}>
                {window.innerWidth >= 870 && recordedExpressionsVisible && <RecordedExpressionsModal />}
              </AnimatePresence>
            </>
          )}
        </div>

        {webcamOff ? (
          <></>
        ) : (
          <>
            <div className={style.textfield}>
              <ScriptText text={text}></ScriptText>
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default Dashboard;
