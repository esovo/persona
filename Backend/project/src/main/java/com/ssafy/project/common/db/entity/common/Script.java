package com.ssafy.project.common.db.entity.common;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Script {

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
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String gerne;
    private String emotion;


}
