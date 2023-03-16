import React, { useRef, useEffect } from "react";
import { useCallback } from "react";
import { useDashboardContext } from "../components/Dashboard";
import { useSettingsContext } from "../components/Settings";
import Webcam from "react-webcam";
import { drawConnectors,drawLandmarks, drawRectangle } from "@mediapipe/drawing_utils/drawing_utils";
import { Camera } from "@mediapipe/camera_utils/camera_utils";
import { FaceDetection } from '@mediapipe/face_detection';
import {
  FaceMesh,
  FACEMESH_TESSELATION,
  FACEMESH_RIGHT_EYE,
  FACEMESH_RIGHT_EYEBROW,
  FACEMESH_LEFT_EYE,
  FACEMESH_LEFT_EYEBROW,
  FACEMESH_FACE_OVAL,
  FACEMESH_LIPS,
} from "@mediapipe/face_mesh/face_mesh";

const FaceDetect = () => {
  const webcamRef = useRef(null);
  
  const {
    setCurrentExpression,
    setEmoji,
    setRecordedExpressions,
    setMountedVideoComponent,
    canvasRef
  } = useDashboardContext();
  const {
    webcamOn,
    overlayOn,
  } = useSettingsContext();
  let faceDetectionArray = [];
  useEffect(() => {
    const faceMesh = new FaceMesh({
      locateFile: (file) => {
        console.log(`${file}`);
        return `https://cdn.jsdelivr.net/npm/@mediapipe/face_mesh@0.4.1633559619/${file}`;
      },
    });

    const faceDetection = new FaceDetection(
      {locateFile: (file) => {
        return `https://cdn.jsdelivr.net/npm/@mediapipe/face_detection@0.4/${file}`;
    }});

    faceMesh.setOptions({
      refineLandmarks: true,
      // selfie: true,
      minDetectionConfidence: 0.5,
      minTrackingConfidence: 0.5,
      maxNumFaces: 1,
      // maxNumFaces: 1,
      // minDetectionConfidence: 0.5,
      // minTrackingConfidence: 0.5,
    });
    faceMesh.onResults(onResults);

    faceDetection.setOptions({
      model: 'short',
      minDetectionConfidence: 0.5
    });

    faceDetection.onResults(faceDetectionOnResults);

    if (
      typeof webcamRef.current !== "undefined" &&
      webcamRef.current !== null
    ) {
      const camera = new Camera(webcamRef.current.video, {
        onFrame: async () => {
          await faceDetection.send({ image: webcamRef.current.video });
          await faceMesh.send({ image: webcamRef.current.video });
          
        },
        width: 1280,
        height: 720,
      });
      camera.start();


      
    }
  }, []);




    /**
   * 
   * @param {Object} info - Object with details of Age, Expressions, ...
   * @returns {Object} - Returns expression of the first face recorded.
   */
    const formatExpression = (info) => {
      if (
        info === undefined ||
        info === null ||
        info.predictions === undefined ||
        info.predictions=== null 
      ) {
        return null;
      }
      const expression = [];
      for (const [key, value] of Object.entries(info.predictions)) {
        expression.push({
          expression: key,
          percent: value*100
        });
      }
      return expression;
    };
  
    /**
     * 
     * @param {Array} formattedExpression - Formatted expression
     * @returns {String} - Returns the emoji name.
     */
    const getEmojiName = (formattedExpression) => {
      if (formattedExpression === null || formattedExpression === undefined || formattedExpression.length < 1) {
        return null;
      }
      let emojiName = null, maxPercent = Number.NEGATIVE_INFINITY;
      formattedExpression.forEach((expr) => {
        if (expr.percent > maxPercent) {
          emojiName = expr.expression;
          maxPercent = expr.percent;
        }
      });
      return emojiName;
    };
  
    /**
     * 
     * @param {Array} recordedExpressions - Expressions recorded upto this point of time.
     * @param {Object} currentExpression - Current expression from the video stream
     * @returns {Array} - Returns an array of recorded expressions
     */
    const recordExpression = (recordedExpressions, currentExpression) => {
      if (currentExpression === undefined || currentExpression === null) {
        return recordedExpressions;
      }
      // console.log(currentExpression)
      if (recordedExpressions.length < 1) {
        currentExpression.forEach((current) => {
          recordedExpressions.push({
            id: current.expression,
            data: [{
              x: 0,
              y: current.percent
            }]
          });
        });
        return recordedExpressions;
      }
      currentExpression.forEach((current, index) => {
        recordedExpressions[index].data.push({
          x: recordedExpressions[index].data.length+1,
          y: current.percent
        });
      });
      return recordedExpressions;
    };
  

  const onResults = async (results) => {

  const videoWidth = webcamRef.current.video.videoWidth;
  const videoHeight = webcamRef.current.video.videoHeight;
  canvasRef.current.width = videoWidth;
  canvasRef.current.height = videoHeight;
  const canvasElement = canvasRef.current;
  const canvasCtx = canvasElement.getContext("2d");
  canvasCtx.save();
  canvasCtx.clearRect(0, 0, videoWidth, videoHeight);
  canvasCtx.translate(videoWidth, 0);
  canvasCtx.scale(-1, 1);
  canvasCtx.drawImage(
    results.image,
    0,
    0,
    canvasElement.width,
    canvasElement.height
  );

  // Websocket
  var socket = new WebSocket('ws://localhost:8000')
  var imageSrc = webcamRef.current.getScreenshot()
  var apiCall = {
    event: "localhost:subscribe",
    data: { 
      image: imageSrc
    },
  };
  socket.onopen = () => socket.send(JSON.stringify(apiCall))
  socket.onmessage = function(event) {
    var pred_log = JSON.parse(event.data)
    console.log(pred_log);
    const formattedExpression = formatExpression(pred_log);
    setEmoji((previousEmoji) => {
      if (formattedExpression === undefined || formattedExpression === null) {
        return previousEmoji;
      }
      const name = getEmojiName(formattedExpression);
      if (name === null) {
        return previousEmoji;
      }
      return name;
    });

    
    setCurrentExpression((previousExpression) => {
      if (formattedExpression === undefined || formattedExpression === null) {
        return previousExpression;
      }
      return formattedExpression;
    });
    setRecordedExpressions((recordedExpressions) => {
      if (formattedExpression === undefined || formattedExpression === null) {
        return recordedExpressions;
      }
      return recordExpression(recordedExpressions, formatExpression(pred_log));
    });
  }

  if(overlayOn){
    if (results.multiFaceLandmarks) {
      for (const landmarks of results.multiFaceLandmarks) {
        drawConnectors(canvasCtx, landmarks, FACEMESH_TESSELATION, {
          color: "#C0C0C070",
          lineWidth: 1,
        });
        drawConnectors(canvasCtx, landmarks, FACEMESH_RIGHT_EYE, {
          color: "#E0E0E0",
          lineWidth: 1,
        });
        drawConnectors(canvasCtx, landmarks, FACEMESH_RIGHT_EYEBROW, {
          color: "#E0E0E0",
          lineWidth: 1,
        });
        drawConnectors(canvasCtx, landmarks, FACEMESH_LEFT_EYE, {
          color: "#E0E0E0",
          lineWidth: 1,
        });
        drawConnectors(canvasCtx, landmarks, FACEMESH_LEFT_EYEBROW, {
          color: "#E0E0E0",
          lineWidth: 1,
        });
        drawConnectors(canvasCtx, landmarks, FACEMESH_FACE_OVAL, {
          color: "#E0E0E0",
          lineWidth: 1,
        });
        drawConnectors(canvasCtx, landmarks, FACEMESH_LIPS, {
          color: "#E0E0E0",
          lineWidth: 1,
        });
      }

    }
    for (const faceDetection of faceDetectionArray) {
      drawRectangle(canvasCtx, faceDetection.boundingBox, {color: 'blue', lineWidth: 4, fillColor: '#00000000'});
    };
  }
        
    canvasCtx.restore();

  };



  const faceDetectionOnResults = (results) => {
    if (results.detections) {
      faceDetectionArray = results.detections;
    }
  }


  return (
    <div>
      <Webcam
        audio={false}
        mirrored={true}
        ref={webcamRef}
        
      />
    </div>
  );
};

export default FaceDetect;
