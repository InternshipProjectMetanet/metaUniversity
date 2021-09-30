package com.example.metauniversity.controller;

import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.NavService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class NavController {

    private NavService navService;

    @GetMapping("/get/user/icon")
    public String auth(@AuthenticationPrincipal CustomUserDetails currentUser){

        if (currentUser == null){
            return "/img/account_circle.svg";
        }else {
            return navService.getUserImg(currentUser);
        }

    }
}
