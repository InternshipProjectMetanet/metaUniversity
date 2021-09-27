package com.example.metauniversity.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.Setter;

import com.example.metauniversity.domain.Reply.dto.replyDto;
import com.example.metauniversity.service.ReplyService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
public class ReplyController {

	@Autowired
    private ReplyService replyService;

    /**
     *  댓글 등록 및 수정
     */
	@RequestMapping(value = { "/reply", "/reply/{replyId}" }, method = { RequestMethod.POST, RequestMethod.PATCH })
	public JsonObject registerReply(@PathVariable(value = "replyId", required = false) Long replyId, @RequestBody final replyDto.insertReply params) {

		JsonObject jsonObj = new JsonObject();

		try {
			if (replyId != null) {
				params.setReplyId(replyId);
			}

			boolean isRegistered = replyService.registerReply(params);
			jsonObj.addProperty("result", isRegistered);

		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}

		return jsonObj;
	}	
	
	/**
	 *  댓글 리스트
	 */
	@GetMapping(value = "/reply/{boardId}")
	public JsonObject getRelyList(@PathVariable("boardId") Long boardId, @ModelAttribute("params") replyDto.insertReply params) {
		
		JsonObject jsonObj = new JsonObject();

		List<replyDto.insertReply> replyList = replyService.getReplyList(params);
		if (!CollectionUtils.isEmpty(replyList)) {
			JsonArray jsonArr = new Gson().toJsonTree(replyList).getAsJsonArray();
			jsonObj.add("replyList", jsonArr);
		}

		return jsonObj;
	}

	/**
	 *  댓글 삭제
	 */
	@DeleteMapping(value = "/reply/{replyId}")
	public JsonObject deleteComment(@PathVariable(value = "replyId", required = false) Long replyId, @RequestBody final replyDto.insertReply params) {

		JsonObject jsonObj = new JsonObject();

		try {
			boolean isDeleted = replyService.deleteReply(replyId);
			jsonObj.addProperty("result", isDeleted);

		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}

		return jsonObj;
	}

}
    
 

