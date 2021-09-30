package com.example.metauniversity.repository.subject;

import com.example.metauniversity.domain.subject.Qsubject;
import com.example.metauniversity.domain.subject.QtimeTable;
import com.example.metauniversity.domain.subject.dto.QsubjectDto_getList;
import com.example.metauniversity.domain.subject.dto.subjectDto;
import com.example.metauniversity.domain.subject.subject;
import com.example.metauniversity.domain.subject.timeTable;
import com.example.metauniversity.repository.subject.subjectRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.example.metauniversity.domain.subject.Qsubject.subject;

@RequiredArgsConstructor
public class subjectRepositoryImpl implements subjectRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<subject> findAllByClassRoom(String classRoom) {
        return queryFactory.select(subject)
                .from(subject)
                .where(subject.classRoom.eq(classRoom))
                .fetch();
    }

    @Override
    public Page<subject> findAllBySearch(subjectDto.search search, Pageable pageable) { // 페이징
//        return queryFactory.select(subject)
//                .from(subject)
//                .where(subjectTitleContains(search.getSubjectTitle()),
//                        subjectPointsEq(search.getSubjectPoints()),
//                        isMajorEq(search.getIsMajor()))
//                .fetch();

        QueryResults<subject> subjectQueryResults = queryFactory.select(subject)
                .from(subject)
                .where(subjectTitleContains(search.getSubjectTitle()),
                        subjectPointsEq(search.getSubjectPoints()),
                        isMajorEq(search.getIsMajor()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<subject> content = subjectQueryResults.getResults();
        long total = subjectQueryResults.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<subjectDto.getList> getAllSubject(Pageable pageable) { // 페이징
//        return queryFactory.select(subject)
//                .from(subject)
//                .join(subject.user).fetchJoin()
//                .fetch();
        QueryResults<subjectDto.getList> getListQueryResults =
                queryFactory.select(new QsubjectDto_getList(subject))
                .from(subject)
                .join(subject.user).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<subjectDto.getList> content = getListQueryResults.getResults();
        long total = getListQueryResults.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Optional<subject> findbyIdFetch(Long subjectId) {
        return Optional.ofNullable(queryFactory.select(subject)
                .from(subject)
                .join(subject.user).fetchJoin()
                .where(subject.id.eq(subjectId))
                .fetchOne());
    }

    private BooleanExpression subjectTitleContains(String subjectTitle) {
        if(subjectTitle == null) {
            return null;
        }

        return subject.subjectTitle.contains(subjectTitle);
    }

    private BooleanExpression subjectPointsEq(Integer subjectPoints) {
        if(subjectPoints == null) {
            return null;
        }

        return subject.subjectPoints.eq(subjectPoints);
    }

    private BooleanExpression isMajorEq(String isMajor) {
        if(isMajor == null) {
            return null;
        } else if(isMajor.equals("false")) {
            return subject.isMajor.eq(false);
        } else if(isMajor.equals("true")) {
            return subject.isMajor.eq(true);
        }

        return null;
    }
}
