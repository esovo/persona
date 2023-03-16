package com.ssafy.project.common.db.dto.social;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor

// 공통속성이 아니므로, interface보단 abstract가 용도에 맞음
public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;

    public abstract String getId();
    public abstract String getEmail();
    public abstract String getName();
    public abstract String getImageUrl();

}
