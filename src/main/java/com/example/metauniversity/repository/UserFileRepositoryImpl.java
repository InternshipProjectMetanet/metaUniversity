package com.example.metauniversity.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.example.metauniversity.domain.File.QUserFile.userFile;

@RequiredArgsConstructor
public class UserFileRepositoryImpl implements UserFileRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean userFileExists(Long userId) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(userFile)
                .where(userFile.user.id.eq(userId))
                .fetchFirst();

        return fetchOne != null;
    }
}
