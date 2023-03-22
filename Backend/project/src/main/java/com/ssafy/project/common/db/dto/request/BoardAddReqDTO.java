package com.ssafy.project.common.db.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardAddReqDTO {
    private Long videoId;
    private String title;
    private String content;
}
