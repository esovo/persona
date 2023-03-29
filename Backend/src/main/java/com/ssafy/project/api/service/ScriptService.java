package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.ScriptSearchReqDTO;
import com.ssafy.project.common.db.dto.response.ScriptDTO;
import com.ssafy.project.common.db.entity.common.Script;
import org.springframework.data.domain.Page;

public interface ScriptService {

    public Page<ScriptDTO> findAllScript(ScriptSearchReqDTO scriptSearchReqDTO);
    public ScriptDTO detailScript(Long scriptId);
}
