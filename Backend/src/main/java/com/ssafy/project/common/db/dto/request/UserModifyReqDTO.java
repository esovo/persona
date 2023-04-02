package com.ssafy.project.common.db.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UserModifyReqDTO {

    @NonNull
    private String nickname;
}
