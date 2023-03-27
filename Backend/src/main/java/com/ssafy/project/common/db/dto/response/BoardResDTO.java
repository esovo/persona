package com.ssafy.project.common.db.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardResDTO {
    private Long id;
    private int likes;
    private String videoUrl;
    private String title;
    private String content;
    private Long viewCnt;
    private LocalDateTime createdDate;
    private String nickName;
    private List<CommentDTO> commentDTOList;
}
