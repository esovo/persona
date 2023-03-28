package com.ssafy.project.common.db.dto.response;

import com.ssafy.project.common.db.entity.base.EmotionEnum;
import com.ssafy.project.common.db.entity.base.GenreEnum;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScriptDTO {

    private Long id;
    private String author;
    private String content;
    private String registrant;
    private Long viewCnt;
    private EmotionEnum emotion;
    private GenreEnum genre;
    private LocalDateTime createdDate;


}
