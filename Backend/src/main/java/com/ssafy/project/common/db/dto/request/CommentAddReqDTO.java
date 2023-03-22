package com.ssafy.project.common.db.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentAddReqDTO {
    private Long boardId;
    private Long userId;
    private String content;
}
