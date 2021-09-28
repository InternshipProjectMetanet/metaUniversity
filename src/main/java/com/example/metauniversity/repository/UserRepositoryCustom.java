package com.example.metauniversity.repository;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.dto.userDto;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    User findByUserName(String userName);

    User getMyInfo(Long id);
    
    List<User> searchUser(userDto.search searchDto);
}
