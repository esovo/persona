package com.ssafy.project.common.db.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class VideoCreateReqDTO {

    MultipartFile file;

    String title;

    Long participantId;

    String analysis;
}
