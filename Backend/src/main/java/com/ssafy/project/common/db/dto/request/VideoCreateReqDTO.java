package com.ssafy.project.common.db.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class VideoCreateReqDTO {

    String videoUrl;
    String thumbnailUrl;
    String graphUrl;
    String title;
    Long participantId;
    String analysis;
}
