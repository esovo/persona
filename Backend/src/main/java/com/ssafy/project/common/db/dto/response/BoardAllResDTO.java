package com.ssafy.project.common.db.dto.response;

import com.ssafy.project.common.db.entity.common.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardAllResDTO {
    @Schema(description = "게시글 아이디")
    private Long id;
    @Schema(description = "닉네임")
    private String nickName;
    @Schema(description = "생성일자")
    private LocalDateTime createdDate;
    @Schema(description = "제목")
    private String title;
    @Schema(description = "내용")
    private String content;
    @Schema(description = "좋아요수")
    private int likeCnt;
    @Schema(description = "댓글수")
    private int commentCnt;
}
