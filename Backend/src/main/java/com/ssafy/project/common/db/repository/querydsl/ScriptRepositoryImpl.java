package com.ssafy.project.common.db.repository.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.project.common.db.dto.response.ScriptDTO;
import com.ssafy.project.common.db.entity.base.EmotionEnum;
import com.ssafy.project.common.db.entity.base.GenreEnum;
import com.ssafy.project.common.db.entity.common.Script;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.project.common.db.entity.common.QScript.script;

@RequiredArgsConstructor
public class ScriptRepositoryImpl implements ScriptRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    //반약 키워드가 빈 배열로 온다면...?
    @Override
    public Page<ScriptDTO> findAllWithFilter(List<String> emotions, List<String> genres, String sort, Pageable pageable) {

        Path<Script> fieldPath = Expressions.path(Script.class, sort);
        OrderSpecifier orderSpecifier = new OrderSpecifier(Order.DESC, fieldPath);

        QueryResults<ScriptDTO> results = queryFactory
                .select(Projections.constructor(ScriptDTO.class
                        , script.id
                        , script.author
                        , script.content
                        , script.registrant
                        , script.viewCnt
                        , script.emotion
                        , script.genre
                        , script.createdDate
                        ))
                .from(script)
                .where(emotionFilter(emotions).or(genreFilter(genres)))
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    private BooleanBuilder emotionFilter(List<String> keywords) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for(String keyword : keywords){
            booleanBuilder.or(script.emotion.eq(EmotionEnum.valueOf(keyword)));
        }
        return booleanBuilder;
    }

    private BooleanBuilder genreFilter(List<String> keywords) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for(String keyword : keywords){
            booleanBuilder.or(script.genre.eq(GenreEnum.valueOf(keyword)));
        }
        return booleanBuilder;
    }
}
