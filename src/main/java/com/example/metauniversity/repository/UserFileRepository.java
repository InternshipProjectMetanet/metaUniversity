package com.example.metauniversity.repository;

import com.example.metauniversity.domain.File.File;
import com.example.metauniversity.domain.File.UserFile;
import com.example.metauniversity.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserFileRepository extends JpaRepository<UserFile, Long> , UserFileRepositoryCustom{

    File findByUser(User user);
    UserFile findByUserId(Long userId);
}
