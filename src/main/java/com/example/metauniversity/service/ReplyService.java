package com.example.metauniversity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.metauniversity.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {
	
	private final ReplyRepository replyRepository;
	
	// 댓글 등록
	

	
}
