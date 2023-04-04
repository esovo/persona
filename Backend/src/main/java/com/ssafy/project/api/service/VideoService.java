package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.VideoCreateReqDTO;
import com.ssafy.project.common.db.dto.request.VideoDeleteReqDTO;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface VideoService {
    void saveVideo(VideoCreateReqDTO videoCreateReqDTO) throws FileNotFoundException;
    void deleteVideo(VideoDeleteReqDTO videoDeleteReqDTO);

}
