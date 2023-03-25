package com.ssafy.project.common.db.entity.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Hit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hit_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; //양방향 맵핑해라~~
}
