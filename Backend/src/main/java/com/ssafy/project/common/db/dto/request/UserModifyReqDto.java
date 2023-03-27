package com.ssafy.project.common.db.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class UserModifyReqDto {

    @NonNull
    private String nickname;
}
