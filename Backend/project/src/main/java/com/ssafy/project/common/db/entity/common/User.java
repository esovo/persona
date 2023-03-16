package com.ssafy.project.common.db.entity.common;

import com.ssafy.project.common.db.dto.social.SocialAuth;
import com.ssafy.project.common.db.entity.base.BaseTime;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname", nullable = false, length = 16)
    private String nickname;

    @Embedded
    private BaseTime baseTime;

    @Embedded
    private SocialAuth socialAuth;

//    @ElementCollection(fetch = FetchType.LAZY)

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorities;
}
