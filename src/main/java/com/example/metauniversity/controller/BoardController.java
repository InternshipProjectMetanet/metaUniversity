package com.example.metauniversity.controller;

import com.example.metauniversity.domain.User.EnrollmentStatus;
import com.example.metauniversity.domain.User.dto.userDto;
import com.example.metauniversity.domain.board.dto.boardDto;
import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.BoardService;
import com.example.metauniversity.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
	

    /**
     * 공지사항 목록 조회
     */
    @GetMapping("/boardList")
    public String boardList(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
    	List<boardDto.boardList> boardDtoList = boardService.getBoardList();
        model.addAttribute("boardList", boardDtoList);
        return "boardList";
    }
    
    /**
     * 공지사항 상세 조회
     */
    @GetMapping("/boardDetail/{no}")
    public String boardDetail(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
    	List<boardDto.boardList> boardDtoList = boardService.getBoardList();
        model.addAttribute("boardList", boardDtoList);
        return "boardList";
    }

   
}
