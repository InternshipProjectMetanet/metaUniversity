package com.example.metauniversity.domain.Reply.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class replyDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class insertReply {
    	private Long replyId;
    	private Long boardId;
    	private String userName;
        private String replyContent;
        private String deleteYn;
    }


}