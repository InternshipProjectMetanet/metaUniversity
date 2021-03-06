package com.example.metauniversity.domain.board.dto;

import com.example.metauniversity.domain.File.File;
import com.example.metauniversity.domain.User.EnrollmentStatus;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UserTyped;
import com.example.metauniversity.domain.User.UsersData;
import com.example.metauniversity.domain.board.Board;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

public class boardDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class boardList {
    	private Long boardId;
        private String title;
        private LocalDateTime updatedDate;
        private String userName;

        
        public boardList(Board board) {
			this.boardId = board.getBoardId();
			this.title = board.getTitle();
			this.updatedDate = board.getUpdatedDate();
			this.userName = board.getUser().getUsersData().getUserName();
		}

    }
    
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getBoard {
    	private Long boardId;
        private String title;
        private String content;
        private String userName;
        private List<File> filesList;
        private String currentUserUrl;
        private String currentUserName;
    }
    
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class saveBoard {
    	private Long accountId;
        private String title;
        private String content;
    }
    
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updateBoard {
    	private Long boardId;
        private String title;
        private String content;
    }
    
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class pageBoardList {
    	private int pageSize; // ??? ????????? ????????? ???
    	private int pageNumber; // ?????? ?????????
    	private int totalPages; // ?????? ????????? ???
    	private Long totalElements; // ?????? ????????? ???
    	List<boardDto.boardList> boardDtoList;
    }

}
