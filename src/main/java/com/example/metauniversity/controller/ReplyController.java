package com.example.metauniversity.controller;


import com.example.metauniversity.domain.Reply.dto.ReplyDto;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/reply/get/{boardId}")
    public List<ReplyDto.GetReply> replies(@PathVariable Long boardId){

        return replyService.getReplies(boardId);
    }

    @PostMapping("/reply/add")
    public void insertReply(@AuthenticationPrincipal CustomUserDetails currentUser, ReplyDto.InsertReply insertReply){
        replyService.saveReply(insertReply, currentUser.getUser());

    }

    @PostMapping("/reply/delete")
    public void replyDelete(Long replyId){
        replyService.deleteReply(replyId);


    }

}
    
 

