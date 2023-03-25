package com.ssafy.project.common.db.repository.querydsl;

import com.ssafy.project.common.db.entity.common.Script;
import org.springframework.data.domain.Page;

public interface ScriptRepositoryCustom {

    public Page<Script> findAllWithFilter();
}
