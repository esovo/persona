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

     private double time;

    private double pleasure;

    private double embarrassed;

    private double anger;

    private double anxiety;

    private double hurt;

    private double sad;

    private double neutrality;
}
