package com.ssafy.project.common.db.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class UserResDto {

    @NonNull
    private String email;
    @NonNull
    private String nickname;
    @NonNull
    private String imageUrl;
    @NonNull
    private String socialType;
    @NonNull
    private LocalDateTime createdDate;
}
