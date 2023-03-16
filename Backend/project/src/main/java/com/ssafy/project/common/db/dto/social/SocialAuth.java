package com.ssafy.project.common.db.dto.social;

import com.ssafy.project.common.db.entity.base.SocialEnum;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SocialAuth {

    private String providerId;

    @Enumerated(value = EnumType.STRING)
    private SocialEnum socialType;

    private String email;

    private String name;

    private String imageUrl;

//    private String ip;
}