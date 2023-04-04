package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.VideoCreateReqDTO;
import com.ssafy.project.common.db.dto.request.VideoDeleteReqDTO;
import com.ssafy.project.common.db.dto.response.VideoListResDTO;
import org.springframework.data.domain.Page;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface VideoService {
    void saveVideo(VideoCreateReqDTO videoCreateReqDTO) throws FileNotFoundException;
    void deleteVideo(VideoDeleteReqDTO videoDeleteReqDTO);
    Page<VideoListResDTO> findAllVideo(int page);
}
