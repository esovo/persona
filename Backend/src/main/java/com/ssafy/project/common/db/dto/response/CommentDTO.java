package com.ssafy.project.common.db.dto.response;

import com.ssafy.project.common.db.entity.common.Board;
import com.ssafy.project.common.db.entity.common.CommentLike;
import com.ssafy.project.common.db.entity.common.User;
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
    private Long id;

    private String userProfile;

    private String nickname;

    private String content;

    private int commentLikes;

    private LocalDateTime createdDate;

}
