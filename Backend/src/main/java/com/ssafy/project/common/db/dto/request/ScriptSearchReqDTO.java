package com.ssafy.project.common.db.dto.request;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class ScriptSearchReqDTO {

    private String option;
    private String keyword;
    private List<String> emotion = new ArrayList<>();
    private List<String> genre = new ArrayList<>();
    private int page;
    private String sort = "id";
}
