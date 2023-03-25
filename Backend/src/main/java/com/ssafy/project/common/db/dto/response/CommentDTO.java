package com.ssafy.project.common.db.dto.response;

import com.ssafy.project.common.db.entity.common.Board;
import com.ssafy.project.common.db.entity.common.CommentLike;
import com.ssafy.project.common.db.entity.common.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDTO {
    @Schema(description = "댓글 아이디")
    private Long id;
    @Schema(description = "유저 프로필 url")
    private String userProfile;
    @Schema(description = "닉네임")
    private String nickname;
    @Schema(description = "내용")
    private String content;
    @Schema(description = "좋아요수")
    private int commentLikes;

    private LocalDateTime createdDate;

}
