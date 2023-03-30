package com.ssafy.project.common.db.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class VideoDetailResDTO {

    String id;

    String analysis;

    String VideoUrl;

    String thumbnailUrl;

}
