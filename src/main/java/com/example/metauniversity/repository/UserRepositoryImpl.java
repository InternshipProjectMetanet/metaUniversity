package com.example.metauniversity.repository;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UserTyped;
import com.example.metauniversity.domain.User.dto.userDto.search;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import static com.example.metauniversity.domain.User.QUser.user;

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
	public List<User> searchUser(search searchDto) {
		return queryFactory.select(user)
				.from(user)
				.join(user.usersData).fetchJoin()
				.where(searchUserCode(searchDto.getUserCode()),
						searchUserName(searchDto.getUserName()),
						searchUserMajor(searchDto.getUserMajor()),
						user.usersData.userType.eq(UserTyped.STUDENT))
				.fetch();
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
    
    
}
