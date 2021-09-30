package com.example.metauniversity.controller;

import com.example.metauniversity.domain.User.dto.userDto;
import com.example.metauniversity.domain.subject.dto.subjectData;
import com.example.metauniversity.domain.subject.dto.subjectDto;
import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.UserService;
import com.example.metauniversity.service.subjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class subjectController {

    private final subjectService subjectService;
    private final UserService userService;

    /**
     * 수강목록 / 내가 신청한 목록 조회
     */
    @GetMapping("/schedule")
    public String gotoSugang(Model model, @PageableDefault(size = 5) Pageable pageable
            , @AuthenticationPrincipal CustomUserDetails currentUser) {

        model.addAttribute("subjectDatas", subjectService.getAll(pageable));
        model.addAttribute("mySubjectDatas", subjectService.getMySubject(currentUser.getUser()));
        model.addAttribute("myPoints", subjectService.getMySubjectPoint(currentUser.getUser()));

        return "scheduleManagement.html";
    }

    /**
     * 수업 등록(관리자) POST
     */
    @PostMapping("/schedule/create")
    public String createSubjectPOST(subjectDto.create createDto,
                                @AuthenticationPrincipal CustomUserDetails currentUser) {
        subjectService.addSubject(createDto, currentUser.getUser());
        return "redirect:/schedule/create";
    }

    /**
     * 수업 등록(관리자) GET
     */
    @GetMapping("/schedule/create")
    public String createSubjectGET(Model model, subjectDto.search searchDto, @PageableDefault(size = 5) Pageable pageable
            ,@AuthenticationPrincipal CustomUserDetails currentUser) {

        model.addAttribute("subjectDatas", subjectService.getAllBySearch(searchDto, pageable));
        model.addAttribute("user",
                new subjectDto.userdata(currentUser.getUser().getId(), currentUser.getUsername()));

        return "scheduleAdd";
    }

    /**
     * 이번학기 열린 수업 목록
     */
    @GetMapping("/schedule/list")
    public String subjectListBySemester(Model model, Pageable pageable) {
        subjectDto.pageSubjectList all = subjectService.getAll(pageable);
        model.addAttribute("slist", new subjectData<>(all.getSubjectDtoList().size(), all));
        return "subjectList";
    }

    /**
     *  직원 -> 특정 수업을 듣는 학생 조회
     */
    @GetMapping("/schedule/{subjectId}/studentList")
    public String getStudentList(@PathVariable Long subjectId, Model model) {
        model.addAttribute("studentList", userService.getStudent(subjectId));
        return "subjectStudentList";
    }

    /**
     * 수업 수정
     */

    /**
     * 수업 삭제
     */
}
