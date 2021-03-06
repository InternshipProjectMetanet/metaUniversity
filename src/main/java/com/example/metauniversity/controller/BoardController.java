package com.example.metauniversity.controller;


import com.example.metauniversity.domain.File.dto.fileDto;
import com.example.metauniversity.domain.board.dto.boardDto;
import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    /**
     * 공지사항 목록 조회
     */
    @GetMapping("/boardList")
    public String boardList(@PageableDefault Pageable pageable, Model model) {
        boardDto.pageBoardList boardDtoList = boardService.getBoardList(pageable);
        model.addAttribute("boardList", boardDtoList);

        return "board/boardList";

    }
    /**
     * 공지사항 상세 조회
     */
    @GetMapping("/boardDetail/{boardId}")
    public String boardDetail(@PathVariable("boardId") Long boardId, Model model,
    		@RequestParam(defaultValue="0") Integer page, @AuthenticationPrincipal CustomUserDetails currentUser) {

		boardDto.getBoard boardDto = boardService.getBoard(boardId,currentUser);

        
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("page", page);

        return "board/boardContent";
    }

    /**
     * 공지사항 삭제
     */
    @PostMapping("/boardDelete")
    public String boardDelete(@RequestParam("boardId") Long boardId, RedirectAttributes redirectAttributes) {
    	System.out.println(boardId);
        boardService.deleteBoard(boardId);

        redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
        return "redirect:/boardList";
    }

    /**
     * 공지사항 등록 페이지
     */
    @GetMapping("/boardForm")
    public String boardForm() {


    	return "board/boardForm";

    }

    /**
     * 공지사항 등록
     */
    @PostMapping("/boardForm")
    public String boardForm(@ModelAttribute boardDto.saveBoard boarddto
            , MultipartHttpServletRequest request, RedirectAttributes redirectAttributes
            , @AuthenticationPrincipal CustomUserDetails currentUser) {

        List<MultipartFile> uploadFiles = request.getFiles("uploadFile");

        boardDto.saveBoard boardInfo = boardDto.saveBoard.builder()
                .accountId(currentUser.getUser().getId())
                .title(boarddto.getTitle())
                .content(boarddto.getContent())
                .build();

        boardService.saveBoard(boardInfo, uploadFiles, currentUser.getUser());

        redirectAttributes.addFlashAttribute("message", "게시글이 등록되었습니다.");
        return "redirect:/boardList";
    }

    /**
     * 공지사항 작성 - 이미지 저장
     */
    @ResponseBody
    @RequestMapping("/board/imageSave")
    public Object boardImageSave(MultipartHttpServletRequest request) {

        MultipartFile uploadImage = request.getFile("upload");

        fileDto.fileUrl filedto = boardService.saveBoardImage(uploadImage);

        return filedto;
    }

    /**
     * 공지사항 수정
     */
    @GetMapping("/boardEdit/{boardId}")
    public String boardEdit(@PathVariable("boardId") Long boardId,
                            Model model,
                            @AuthenticationPrincipal CustomUserDetails currentUser) {
    	boardDto.getBoard boardDto = boardService.getBoard(boardId, currentUser);

        model.addAttribute("boardDto", boardDto);

    	return "board/boardEdit";

    }

    /**
     * 공지사항 수정
     */
    @PostMapping("/boardEdit/{boardId}")
    public String boardEdit(@ModelAttribute boardDto.updateBoard boarddto
            , MultipartHttpServletRequest request, RedirectAttributes redirectAttributes
            , @PathVariable Long boardId, @AuthenticationPrincipal CustomUserDetails currentUser) {

        List<MultipartFile> uploadFiles = request.getFiles("uploadFile");

        String[] deleteFileId = request.getParameterValues("deleteFileId");

        boardService.updateBoard(boarddto, uploadFiles, deleteFileId, currentUser.getUser());
        redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
        return "redirect:/boardDetail/" + boardId;
    }
}