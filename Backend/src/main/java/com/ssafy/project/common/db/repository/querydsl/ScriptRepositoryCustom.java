package com.ssafy.project.common.db.repository.querydsl;

import com.ssafy.project.common.db.dto.request.ScriptSearchReqDTO;
import com.ssafy.project.common.db.dto.response.ScriptDTO;
import com.ssafy.project.common.db.entity.common.Script;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScriptRepositoryCustom {

    Page<ScriptDTO> findAllWithFilter(ScriptSearchReqDTO scriptSearchReqDTO);
}
