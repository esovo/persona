package com.ssafy.project.common.db.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class UserDetailResDTO {

    private String email;
    private String nickname;
    private String imageUrl;
    private String socialType;
    private String createdDate;
}
