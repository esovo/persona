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
public class BoardAllResDTO {
    private Long id;
    private String title;
    private Long viewCnt;
    private int likes;
    private LocalDateTime createdDate;
    private String nickName;
}