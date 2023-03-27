package com.ssafy.project.common.db.entity.common;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emotion_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant participant;
     private int time;
    private float pleasure;
    private float embarrassed;
    private float anger;
    private float anxiety;
    private float hurt;
    private float sad;
    private float neutrality;
}
