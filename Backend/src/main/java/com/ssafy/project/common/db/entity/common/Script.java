package com.ssafy.project.common.db.entity.common;

import com.ssafy.project.common.db.entity.base.BaseTime;
import com.ssafy.project.common.db.entity.base.EmotionEnum;
import com.ssafy.project.common.db.entity.base.GenreEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Script extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "script_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private EmotionEnum emotion;

    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    private String title;

    private String author;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String registrant;

    private Long viewCnt;

    private Long bookmarkCnt;
}
