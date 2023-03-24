package com.ssafy.project.common.db.repository;

import org.apache.logging.log4j.core.script.Script;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {

}
