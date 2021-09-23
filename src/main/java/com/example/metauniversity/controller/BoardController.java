package com.example.metauniversity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String boardWrite(int boardId){
        return "boardForm";
    }
}
