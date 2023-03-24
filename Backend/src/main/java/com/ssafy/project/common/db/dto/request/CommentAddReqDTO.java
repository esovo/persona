package com.ssafy.project.common.db.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentAddReqDTO {
    @Schema(description = "게시글 id", example = "1")
    private Long boardId;
    @Schema(description = "유저 id", example = "1")
    private Long userId;
    @Schema(description = "수정 내용", example = "댓글 수정합니다.")
    private String content;
}