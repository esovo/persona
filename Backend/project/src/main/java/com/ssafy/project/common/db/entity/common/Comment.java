package com.ssafy.project.common.db.entity.common;

import com.ssafy.project.common.db.entity.base.BaseTime;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "useur_id")
    private User user;

    private String content;

    @Builder.Default
    @OneToMany(mappedBy = "comment", orphanRemoval = true)
    private List<CommentLike> commentLikes = new ArrayList<>();
}
