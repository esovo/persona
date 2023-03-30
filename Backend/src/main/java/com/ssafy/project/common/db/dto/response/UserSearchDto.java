package com.ssafy.project.common.db.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UserSearchDto {

    @NonNull
    private String email;
    @NonNull
    private String nickname;
    @NonNull
    private String imageUrl;
}
