package com.example.metauniversity.service;

import com.example.metauniversity.config.FolderConfig;
import com.example.metauniversity.domain.File.BoardFile;
import com.example.metauniversity.domain.File.File;
import com.example.metauniversity.domain.File.dto.fileDto;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.board.Board;
import com.example.metauniversity.domain.board.dto.boardDto;
import com.example.metauniversity.domain.board.dto.boardDto.getBoard;
import com.example.metauniversity.domain.board.dto.boardDto.saveBoard;
import com.example.metauniversity.domain.board.dto.boardDto.updateBoard;
import com.example.metauniversity.exception.NoSuchBoardException;
import com.example.metauniversity.exception.NoSuchUserException;
import com.example.metauniversity.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final BoardFileRepository boardFileRepository;
	private final FileRepository fileRepository;
	private final UserFileRepository userFileRepository;
	private final FileService fileService;
	private final FolderConfig folderConfig;
	private final UserRepository userRepository;
	private final UsersDataRepository usersDataRepository;
	private final ReplyRepository replyRepository;

	
	// 게시글 목록 조회
	public boardDto.pageBoardList getBoardList(Pageable pageable) {
		
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "boardId"));
		
		Page<Board> boards = boardRepository.findAll(pageable);
		
		List<boardDto.boardList> boardDtoList = boards.getContent()
				.stream().map(b -> new boardDto.boardList(b)).collect(Collectors.toList());
				
		boardDto.pageBoardList boardList = boardDto.pageBoardList.builder()
				.pageSize(boards.getSize())
				.pageNumber(boards.getNumber())
				.totalPages(boards.getTotalPages())
				.totalElements(boards.getTotalElements())
				.boardDtoList(boardDtoList)
				.build();
		
		return boardList;
	}
	
	// 홈화면 게시글 목록 조회
	public boardDto.pageBoardList getBoardListHome(Pageable pageable) {
		
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "boardId"));
		
		Page<Board> boards = boardRepository.findAll(pageable);
		
		List<boardDto.boardList> boardDtoList = new ArrayList<>();
		
		for (Board board : boards.getContent()) {
			boardDto.boardList boarddto = boardDto.boardList.builder()
					.boardId(board.getBoardId())
					.updatedDate(board.getCreatedDate())
					.title(board.getTitle())
					.userName(board.getUser().getUsersData().getUserName())
					.build();
			
			boardDtoList.add(boarddto);
		}
		
		boardDto.pageBoardList boardList = boardDto.pageBoardList.builder()
				.pageSize(boards.getSize())
				.pageNumber(boards.getNumber())
				.totalPages(boards.getTotalPages())
				.totalElements(boards.getTotalElements())
				.boardDtoList(boardDtoList)
				.build();
		
		return boardList;
	}

	// 게시글 상세 조회
	@Transactional
	public getBoard getBoard(Long boardId, User currentUser) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new NoSuchBoardException("해당 게시물이 없습니다."));

		User user = userRepository.findById(currentUser.getId())
			.orElseThrow(() -> new NoSuchUserException("해당 유저가 없습니다."));


		String url = "";

		try{
			url = user.getUserfile().getFile().getUrl();

		}catch (Exception e){
			url= "/img/account_circle.svg";
		}


		boardDto.getBoard boarddto = boardDto.getBoard.builder()
					.boardId(board.getBoardId())
					.content(board.getContent())
					.title(board.getTitle())
					.userName(board.getUser().getUsersData().getUserName())
					.currentUserUrl(url)
					.currentUserName(user.getUsersData().getUserName())
					.filesList(board.getBoardfile().stream().map(f -> f.getFile()).collect(Collectors.toList()))
					.build();
				
		return boarddto;
	}

	// 게시글 삭제
	@Transactional
	public void deleteBoard(Long boardId) {
		replyRepository.deleteByBoardIdBoardId(boardId);
		
		List<BoardFile> deleteBoardFile = boardFileRepository.findByBoardBoardId(boardId);
		for (BoardFile boardFile : deleteBoardFile) {
			fileRepository.deleteById(boardFile.getFile().getId());
		}
		
		boardFileRepository.deleteByBoardBoardId(boardId);
		boardRepository.deleteById(boardId);		
	}

	// 게시글 등록
	@Transactional
	public void saveBoard(saveBoard boarddto, List<MultipartFile> uploadFiles, User user) {
		
		Board saveBoard =  boardRepository.save(Board.builder()
				.user(user)
				.title(boarddto.getTitle())
				.content(boarddto.getContent())
				.build());
		
		System.out.println("file size : " + uploadFiles.size());
		if(uploadFiles.size() != 0) {
            List<File> files = fileService.uploadFiles(folderConfig.getBoard(), uploadFiles);
            for (File file : files) {
            	boardFileRepository.save(BoardFile.create(file, saveBoard));				
			}
        }
	}

	// ckEditor 이미지 저장
	@Transactional
	public fileDto.fileUrl saveBoardImage(MultipartFile uploadImage) {
		if(uploadImage.getOriginalFilename().length() != 0) {
            File file = fileService.uploadThumbnailImage(folderConfig.getBoard(), uploadImage);
            
            fileDto.fileUrl filedto = fileDto.fileUrl.builder()
            					.url(file.getUrl())
            					.build();
            // boardFileRepository.save(BoardFile.create(file, user));
            
            return filedto;
        }
		
		return fileDto.fileUrl.builder()
				.url("https://example.com/images/foo.jpg")
				.build();
		
	}

	// 게시글 수정
	@Transactional
	public void updateBoard(updateBoard boarddto, List<MultipartFile> uploadFiles, String[] deleteFileId, User user) {
		// 파일 삭제
		if (deleteFileId != null) {
			for (String fileId : deleteFileId) {
				boardFileRepository.deleteByFileId(Long.parseLong(fileId));
				fileRepository.deleteById(Long.parseLong(fileId));
			}			
		}
		
		// 보드 테이블 수정
		Board updateBoard = boardRepository.findById(boarddto.getBoardId())
					.orElseThrow(() -> new NoSuchBoardException("해당 게시물이 없습니다."));
		updateBoard.update(boarddto);
		
		// 추가 파일 저장
		if(uploadFiles.size() != 0) {
            List<File> files = fileService.uploadFiles(folderConfig.getBoard(), uploadFiles);
            for (File file : files) {
            	boardFileRepository.save(BoardFile.create(file, updateBoard));				
			}
        }
	}
	
}
