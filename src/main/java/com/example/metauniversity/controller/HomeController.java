package com.example.metauniversity.controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.metauniversity.domain.board.dto.boardDto;
import com.example.metauniversity.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final BoardService boardService;
	
    /**
     * 홈화면
     */
    @GetMapping("/home")
    public String home(@PageableDefault Pageable pageable, Model model) {
    	boardDto.pageBoardList boardDtoList = boardService.getBoardListHome(pageable);
    	model.addAttribute("boardList", boardDtoList);
    	return "home";
    }

    /**
     * 로그인 페이지
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 회원가입 페이지
     */
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 에러페이지
     */
    @GetMapping("/denied")
    public String denied() {
        return "error";
    }


}