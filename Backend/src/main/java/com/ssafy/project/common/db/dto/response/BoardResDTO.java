package com.ssafy.project.common.db.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "비디오 아이디")
    private Long id;
    @Schema(description = "조회수")
    private Long viewCnt;
    @Schema(description = "좋아요수")
    private Long likeCnt;
    @Schema(description = "생성일자")
    private LocalDateTime createdDate;
    @Schema(description = "닉네임")
    private String nickName;
    @Schema(description = "제목")
    private String title;
    @Schema(description = "내용")
    private String content;
    @Schema(description = "비디오url")
    private String videoUrl;

    @Schema(description = "좋아요여부")
    private boolean isLike;
}
