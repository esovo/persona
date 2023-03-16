package com.ssafy.project.common.db.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardModifyReqDTO {
    private Long boardId;
    private Long videoId;
    private String title;
    private String content;
}
