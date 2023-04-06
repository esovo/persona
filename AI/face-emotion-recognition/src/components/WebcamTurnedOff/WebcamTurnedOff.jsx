import { Settings } from '../Settings';

const WebcamTurnedOff = () => {
  return (
    <div className="margin">
      <div className="rounded-lg bg-bg-2 m-4 shadow-2xl p-10 md:p-20 text-sm sm:text-xl text-gray-600">
        녹화를 시작하면 화면이 켜집니다.
      </div>
      <Settings></Settings>
    </div>
  );
};

export default WebcamTurnedOff;
