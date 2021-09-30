package com.example.metauniversity.service;

import com.example.metauniversity.domain.File.File;
import com.example.metauniversity.domain.File.UserFile;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.exception.NoSuchUserException;
import com.example.metauniversity.repository.UserFileRepository;
import com.example.metauniversity.repository.UserRepository;
import com.example.metauniversity.repository.UsersDataRepository;
import com.example.metauniversity.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class NavService {

    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public String getUserImg(CustomUserDetails currentUser) {

        User user = userRepository.findById(currentUser.getUser().getId())
            .orElseThrow(() -> new NoSuchUserException("로그인중인 유저가 없습니다."));

        String url = user.getUsersData().getIsThumbnail() ? user.getUserfile().getFile().getUrl() : "/img/account_circle.svg" ;

        return url;

    }
}
