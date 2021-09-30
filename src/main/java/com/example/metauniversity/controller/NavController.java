package com.example.metauniversity.controller;

import com.example.metauniversity.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @GetMapping("/test")
    public Principal auth(Principal principal){
        return principal.;

    }
}
