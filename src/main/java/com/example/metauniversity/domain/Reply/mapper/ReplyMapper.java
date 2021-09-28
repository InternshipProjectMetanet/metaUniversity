package com.example.metauniversity.domain.Reply.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.metauniversity.domain.Reply.dto.replyDto;

@Mapper
public interface ReplyMapper {

	public int insertReply(replyDto.insertReply params);
	
	public com.example.metauniversity.domain.Reply.dto.replyDto.insertReply selectReplyDatail (Long replyId);

	public int updateReply(replyDto.insertReply params);

	public int deleteReply(Long replyId);

	public List<replyDto.insertReply> selectReplyList(replyDto.insertReply params);
	
	public int selectReplyTotalCount(replyDto.insertReply params);
}
