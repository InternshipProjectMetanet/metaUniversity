package com.example.metauniversity.domain.Reply.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReplyDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InsertReply {
    	private Long replyId;
    	private Long boardId;
    	private String userName;
        private String replyContent;
        private boolean isDeleted;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetReply {
        private Long replyId;
        private Long boardId;
        private String userName;
        private String imgUrl;
        private String replyContent;
        private boolean isDeleted;
    }



}