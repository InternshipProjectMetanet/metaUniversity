package com.example.metauniversity.controller;

import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.NavService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NavController {

    private final NavService navService;

    @GetMapping("/nav/img")
    public String navImg(@AuthenticationPrincipal CustomUserDetails currentUser){

        String url = "/img/account_circle.svg";
        if(currentUser != null){
            url = navService.getUserImg(currentUser);
        }

        return url;
    }
}
