package com.example.metauniversity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @GetMapping("/test")
    public Object auth(Principal principal){
        Object auth = SecurityContextHolder.getContext().getAuthentication();


        return auth;

    }
}
