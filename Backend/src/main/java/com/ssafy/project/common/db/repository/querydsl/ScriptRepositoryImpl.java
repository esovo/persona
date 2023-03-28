package com.ssafy.project.common.db.repository.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.project.common.db.dto.request.ScriptSearchReqDTO;
import com.ssafy.project.common.db.dto.response.ScriptDTO;
import com.ssafy.project.common.db.entity.base.EmotionEnum;
import com.ssafy.project.common.db.entity.base.GenreEnum;
import com.ssafy.project.common.db.entity.common.Script;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.project.common.db.entity.common.QScript.script;

@RequiredArgsConstructor
public class ScriptRepositoryImpl implements ScriptRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    //반약 키워드가 빈 배열로 온다면...?
    @Override
    public Page<ScriptDTO> findAllWithFilter(ScriptSearchReqDTO scriptSearchReqDTO) {

        Pageable pageable = PageRequest.of(scriptSearchReqDTO.getPage(), 10);
//        if(scriptSearchReqDTO.getSort().equals("viewCnt") || scriptSearchReqDTO.getSort().equals("id")){
//        Path<Script> fieldPath = Expressions.path(Script.class, scriptSearchReqDTO.getSort());
//
//        OrderSpecifier orderSpecifier = new OrderSpecifier(Order.DESC, fieldPath);
//        QueryResults<ScriptDTO> results = queryFactory
//                .select(Projections.constructor(ScriptDTO.class
//                        , script.id
//                        , script.author
//                        , script.actor
//                        , script.content
//                        , script.viewCnt
//                        , script.emotion
//                        , script.genre
//                        , script.createdDate
//                        , script.bookmarks.size().as("bookmarkCnt")
//                        ))
//                .from(script)
//                .where(emotionFilter(scriptSearchReqDTO.getEmotion())
//                        ,genreFilter(scriptSearchReqDTO.getGenre())
//                        ,titleFilter(scriptSearchReqDTO.getOption(), scriptSearchReqDTO.getKeyword()))
//                .orderBy(orderSpecifier)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetchResults();
//
//        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
//        }

        QueryResults<ScriptDTO> results = queryFactory
                .select(Projections.constructor(ScriptDTO.class
                        , script.id
                        , script.title
                        , script.author
                        , script.actor
                        , script.content
                        , script.viewCnt
                        , script.emotion
                        , script.genre
                        , script.createdDate
                        , script.bookmarks.size()
                        , script.participants.size()
                ))
                .from(script)
                .where(emotionFilter(scriptSearchReqDTO.getEmotion()), genreFilter(scriptSearchReqDTO.getGenre())
                        ,keywordFilter(scriptSearchReqDTO.getOption(), scriptSearchReqDTO.getKeyword()))
                .orderBy(makeOrder(scriptSearchReqDTO.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    private OrderSpecifier<?> makeOrder(String sort) {
        if(sort.equals("bookmarkCnt")) return script.bookmarks.size().desc();
        else if(sort.equals("participantCnt")) return script.participants.size().desc();
        else if(sort.equals("viewCnt")) return script.viewCnt.desc();
        else return script.id.desc();
    }

    private BooleanExpression keywordFilter(String option, String keyword) {
        if(option == null || keyword == null) return null;

        if(option.equals("content")) return script.content.contains(keyword);
        if(option.equals("title")) return script.title.contains(keyword);
        if(option.equals("author")) return script.author.contains(keyword);
        if(option.equals("actor")) return script.author.contains(keyword);
        else return null;
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
