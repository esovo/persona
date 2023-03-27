package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.ScriptSearchReqDTO;
import com.ssafy.project.common.db.dto.response.ScriptDTO;
import com.ssafy.project.common.db.entity.common.Script;
import com.ssafy.project.common.db.repository.ScriptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScriptServiceImpl implements ScriptService{

    private final ScriptRepository scriptRepository;

    @Override
    public Page<ScriptDTO> findAllScript(ScriptSearchReqDTO scriptSearchReqDTO) {
        Pageable pageable = PageRequest.of(scriptSearchReqDTO.getPage(), 10);
        Page<ScriptDTO> scripts = scriptRepository.findAllWithFilter(scriptSearchReqDTO.getEmotion(), scriptSearchReqDTO.getGenre(),
                scriptSearchReqDTO.getSort(), pageable);
        return scripts;
    }
}
