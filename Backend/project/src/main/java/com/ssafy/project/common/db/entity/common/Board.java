package com.ssafy.project.common.db.entity.common;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
@ToString
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", columnDefinition = "bigint")
    private Long id;
    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<BoardLike> boardLikes = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;
    private String title;
    private String content;
    @Column
    @ColumnDefault("0")
    private Long viewCnt;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
}
