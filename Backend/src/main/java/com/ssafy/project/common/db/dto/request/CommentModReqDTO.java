package com.ssafy.project.common.db.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@NoArgsConstructor
@ToString
public class CommentModReqDTO {

    Long commentId;
    String content;
}
