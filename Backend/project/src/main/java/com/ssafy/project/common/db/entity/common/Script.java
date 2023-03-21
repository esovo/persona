package com.ssafy.project.common.db.entity.common;

import com.ssafy.project.common.db.entity.base.BaseTime;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String title;
    private String author;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String registrant;
    private Long viewNum;
    private String gerne;
    private String emotion;


}
