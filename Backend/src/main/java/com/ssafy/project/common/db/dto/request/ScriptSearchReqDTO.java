package com.ssafy.project.common.db.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ScriptSearchReqDTO {
    private List<String> emotion;
    private List<String> genre;
    private int page;
    private String sort;
}
