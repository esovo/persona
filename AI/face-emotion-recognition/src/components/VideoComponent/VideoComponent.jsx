
import EmojiWidget from "../EmojiWidget";
import WebcamTurnedOff from "../WebcamTurnedOff";
import { useSettingsContext } from "../Settings";
import "./VideoComponent.css";
import FaceDetect from "../../utils/FaceDetect";
import FaceOverlay from "../../utils/FaceOverlay";

const VideoComponent = () => {
  const {
    webcamOn, overlayOn,
    emojiOn
  } = useSettingsContext();
  return(
    <>
      {/* <VideoStream></VideoStream> */}
      {webcamOn ? <FaceDetect /> : <WebcamTurnedOff />}
      {webcamOn && overlayOn && <FaceOverlay />}
      {webcamOn && emojiOn && <span className="absolute top-8 right-8"><EmojiWidget /></span>}
    </>
  );
};

export default VideoComponent;