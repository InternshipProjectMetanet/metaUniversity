package com.example.metauniversity.repository;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.dto.userDto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    User findByUserName(String userName);

    User getMyInfo(Long id);
    
    User getStudentInfo(String userCode);
    
    Page<User> searchUser(userDto.search searchDto, Pageable pageable);
}
