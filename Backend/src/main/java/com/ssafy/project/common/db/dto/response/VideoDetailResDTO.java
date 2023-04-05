package com.ssafy.project.common.db.dto.response;

import com.ssafy.project.common.db.entity.base.EmotionEnum;
import com.ssafy.project.common.db.entity.base.GenreEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
public class VideoDetailResDTO {

    private String title;
    private String emotion;
    private String genre;
    private String actor;
    private String author;
    private String videoUrl;
    private String graphUrl;
    private String createdDate;
}
