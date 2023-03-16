package com.ssafy.project.common.db.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDTO {

    private Long id;
    private String title;
    private String content;
    private Long viewCnt;
    private int likes;
    private LocalDateTime registerDate;
    private String videoUrl;
    private String nickName;
    private Long userId;
}
