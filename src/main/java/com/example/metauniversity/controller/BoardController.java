package com.example.metauniversity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.annotation.RequestScope;

@Controller
public class BoardController {

    @GetMapping("/boardList")
    public String boardList(){
        return "boardList";
    }
    @GetMapping("/board/write")
    public String boardWrite(){
        return "boardForm";
    }
    @GetMapping("/board/content/{boardId}")
    public String boardContent(@PathVariable int boardId){
        System.out.println(boardId);
        return "boardContent";
    }
}
