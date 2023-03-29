package com.ssafy.project.common.db.entity.common;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Speech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speech_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    //음석 인식된 내용
    //어떻게 저장될 건지는 더 봐야함`
    //인식한거 프론트에서 한번에 줄건지
    private String speechRecognition;
}
