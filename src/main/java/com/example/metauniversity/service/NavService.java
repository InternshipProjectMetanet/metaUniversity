package com.example.metauniversity.service;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.exception.NoSuchUserException;
import com.example.metauniversity.repository.UserRepository;
import com.example.metauniversity.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class NavService {

    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public String getUserImg(CustomUserDetails currentUser){
        String url = "";
        Long userId = null;

        User user = userRepository.findById(currentUser.getUser().getId()).orElseThrow(() -> new NoSuchUserException("로그인중인 사용자가 없습니다"));
            try{
                url = user.getUserfile().getFile().getUrl();
            }catch (Exception e){
                url = "/img/account_circle.svg";
            }

        return url;
    }
}
