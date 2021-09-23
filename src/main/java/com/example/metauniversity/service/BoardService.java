package com.example.metauniversity.service;

import com.example.metauniversity.config.FolderConfig;
import com.example.metauniversity.domain.File.File;
import com.example.metauniversity.domain.File.UserFile;
import com.example.metauniversity.domain.User.EnrollmentStatus;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UsersData;
import com.example.metauniversity.domain.User.dto.userDto;
import com.example.metauniversity.domain.board.Board;
import com.example.metauniversity.domain.board.dto.boardDto;
import com.example.metauniversity.domain.board.dto.boardDto.boardList;
import com.example.metauniversity.exception.NoSuchUserException;
import com.example.metauniversity.repository.BoardRepository;
import com.example.metauniversity.repository.UserFileRepository;
import com.example.metauniversity.repository.UserRepository;
import com.example.metauniversity.repository.UsersDataRepository;
import com.example.metauniversity.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	// 게시글 조회
	public List<boardList> getBoardList() {
		
		List<Board> boards = boardRepository.findAll();
		List<boardDto.boardList> boardDtoList = new ArrayList<>();
		
		for (Board board : boards) {
			boardDto.boardList boarddto = boardDto.boardList.builder()
					.boardId(board.getBoardId())
					.created_date(board.getCreatedDate())
					.title(board.getTitle())
					.userName(board.getUser().getUsersData().getUserName())
					.build();
			
			boardDtoList.add(boarddto);
		}
		
		return boardDtoList;
	}

	
}
