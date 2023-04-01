package com.ssafy.project.common.db.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class VideoListResDTO {

    Long id;
    String title;
    String thumbnailUrl;
    String analysis;
    LocalDateTime createdDate;

}
