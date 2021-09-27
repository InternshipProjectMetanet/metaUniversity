package com.example.metauniversity.service;

import java.util.List;

import com.example.metauniversity.domain.Reply.dto.replyDto;
import com.example.metauniversity.domain.Reply.dto.replyDto.insertReply;

public interface ReplyService {

	public boolean registerReply(insertReply params);
	
	public List<replyDto.insertReply> getReplyList(replyDto.insertReply params);

	boolean deleteReply(Long replyId);

	
}
