package com.ssafy.project.common.db.repository;

import org.apache.logging.log4j.core.script.Script;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

//@Repository
public interface ScriptRepository {
    //정렬기준이 있을 때 페이징 - 쿼리 dsl로 구현해보기
    Page<Script> findAllByEmotionAndGerne(String emotion, String gerne, Pageable pageable);
    Page<Script> findAllByEmotion(String emotion, Pageable pageable);
    Page<Script> findAllByGerne(String gerne, Pageable pageable);
}
