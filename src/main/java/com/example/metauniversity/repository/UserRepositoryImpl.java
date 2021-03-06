package com.example.metauniversity.repository;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UserTyped;
import com.example.metauniversity.domain.User.dto.userDto.search;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

import static com.example.metauniversity.domain.File.QUserFile.userFile;
import static com.example.metauniversity.domain.User.QUser.user;
import static com.example.metauniversity.domain.subject.QtimeTable.timeTable;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public User findByUserName(String userName) {
        return queryFactory.select(user)
                .from(user)
                .join(user.usersData).fetchJoin()
                .where(user.usersData.userName.eq(userName))
                .fetchOne();
    }

    @Override
    public User getMyInfo(Long id) {
        return queryFactory.select(user)
                .from(user)
                .join(user.usersData).fetchJoin()
                .where(user.id.eq(id))
                .fetchOne();
    }

	@Override
	public Page<User> searchUser(search searchDto, Pageable pageable) {
		QueryResults<User> searchResult = queryFactory.select(user)
				.from(user)
				.join(user.usersData).fetchJoin()
				.where(searchUserCode(searchDto.getUserCode()),
						searchUserName(searchDto.getUserName()),
						searchUserMajor(searchDto.getUserMajor()),
						user.usersData.userType.eq(UserTyped.STUDENT))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(searchResult.getResults(), pageable, searchResult.getTotal());
	}

	@Override
	public Boolean existsId(String accountId) {
		Integer fetchOne = queryFactory
				.selectOne()
				.from(user)
				.where(user.accountId.eq(accountId))
				.fetchFirst();

		return fetchOne != null;
	}

	private BooleanExpression searchUserCode(String userCode) {
	
		if(userCode == null) {
			return null;
		}
		
		return user.usersData.userCode.contains(userCode);
	}
	
	private BooleanExpression searchUserName(String userName) {
		
		if(userName == null) {
			return null;
		}
		
		return user.usersData.userName.contains(userName);
		
	}
	
	private BooleanExpression searchUserMajor(String userMajor) {
		
		if(userMajor == null) {
			return null;
		}
		
		return user.usersData.userMajor.contains(userMajor);
		
	}

	@Override
	public User getStudentInfo(String userCode) {
		return queryFactory.select(user)
                .from(user)
                .join(user.usersData).fetchJoin()
                .where(user.usersData.userCode.eq(userCode))
                .fetchOne();
	}
    
    
}
