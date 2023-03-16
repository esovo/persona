package com.ssafy.project.common.db.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardSearchReqDTO {

    private int page;
    private String column;
    private String word;
}
