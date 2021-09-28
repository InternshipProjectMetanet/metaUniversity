package com.example.metauniversity.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.metauniversity.domain.Reply.dto.replyDto;
import com.example.metauniversity.domain.Reply.dto.replyDto.insertReply;
import com.example.metauniversity.domain.Reply.mapper.ReplyMapper;

@Service
public class ReplyServicempl implements ReplyService {
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public boolean registerReply(replyDto.insertReply params) {
		int queryResult = 0;

		if (params.getReplyId() == null) {
			queryResult = replyMapper.insertReply(params);
		} else {
			queryResult = replyMapper.updateReply(params);
		}
		return (queryResult == 1) ? true : false;
	}

	@Override
	public boolean deleteReply(Long replyId) {
		int queryResult = 0;

		replyDto.insertReply reply = replyMapper.selectReplyDatail(replyId);

		if (reply != null && "N".equals(reply.getDeleteYn())) {
			queryResult = replyMapper.deleteReply(replyId);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<replyDto.insertReply> getReplyList(replyDto.insertReply params) {
		List<replyDto.insertReply> replyList = new ArrayList<>();
		
		Integer replyTotalCount = replyMapper.selectReplyTotalCount(params);
		
		if(replyTotalCount > 0) {
			replyList = replyMapper.selectReplyList(params);
		}
		return replyList;
	}


}
