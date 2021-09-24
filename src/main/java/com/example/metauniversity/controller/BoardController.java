package com.example.metauniversity.controller;


import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.metauniversity.domain.board.dto.boardDto;
import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
	

    /**
     * 공지사항 목록 조회
     */
    @GetMapping("/boardList")
    public String boardList(Model model) {
    	List<boardDto.boardList> boardDtoList = boardService.getBoardList();
        model.addAttribute("boardList", boardDtoList);
        return "boardList";
    }
    
    /**
     * 공지사항 상세 조회
     */
    @GetMapping("/boardDetail/{boardId}")
    public String boardDetail(@PathVariable("boardId") Long boardId, Model model) {
//    	boardDto.getBoard boardDto = boardService.getBoard(boardId);
//
//        model.addAttribute("boardDto", boardDto);
        return "boardContent";
    }

   

}
