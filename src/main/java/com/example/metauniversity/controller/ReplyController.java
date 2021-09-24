package com.example.metauniversity.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.metauniversity.domain.Reply.dto.replyDto;
import com.example.metauniversity.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/replies/*")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
	

    /**
     *  댓글 등록
     */



}
