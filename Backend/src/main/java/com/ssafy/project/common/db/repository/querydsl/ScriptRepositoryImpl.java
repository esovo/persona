//package com.ssafy.project.common.db.repository.querydsl;
//
//import antlr.collections.impl.BitSet;
//import com.querydsl.core.types.OrderSpecifier;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.ssafy.project.common.db.entity.common.Script;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//
//import javax.persistence.EntityManager;
//
//import java.util.List;
//
//import static com.ssafy.project.common.db.entity.common.QScript.script;
//
//@RequiredArgsConstructor
//public class ScriptRepositoryImpl implements ScriptRepositoryCustom {
//
//    private final JPAQueryFactory queryFactory;
//
//    @Override
//    public Page<Script> findAllWithFilter(List<String> keywords, String sort, Long page) {
//
////        OrderSpecifier
////
////        return queryFactory
////                .select(script)
////                .from(script)
////                .where(emotionFilter(keywords).or(genreFilter(keywords)))
////                .orderBy(sort.desc())
////                .
//
//        return null;
//    }
//
//    private BitSet emotionFilter(List<String> keywords) {
//        return null;
//    }
//}
