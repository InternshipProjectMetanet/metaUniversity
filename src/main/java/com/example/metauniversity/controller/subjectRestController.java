package com.example.metauniversity.controller;

import com.example.metauniversity.domain.subject.dto.subjectData;
import com.example.metauniversity.domain.subject.dto.subjectDto;
import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.subjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class subjectRestController {

    private final subjectService subjectService;

    /**
     * 수강신청
     */
    @PostMapping("/subject/enroll/{subjectId}")
    public subjectDto.enroll subjectEnroll(@PathVariable Long subjectId,
                                           @AuthenticationPrincipal CustomUserDetails currentUser) {
        return subjectService.EnrollSubject(subjectId, currentUser.getUser());
    }

    /**
     * 수강취소
     */
    @PostMapping("/subject/cancel/{subjectId}")
    public subjectDto.cancel subjectCancel(@PathVariable Long subjectId,
                                           @AuthenticationPrincipal CustomUserDetails currentUser) {
        return subjectService.cancelSubject(subjectId, currentUser.getUser().getId());
    }

    /**
     * 수업 검색 필터링
     */
    @GetMapping("/subject/list/search")
    public subjectData<subjectDto.pageSubjectList> getAllSubjectBySearch(@ModelAttribute subjectDto.search searchDto, @PageableDefault(size = 5) Pageable pageable) {
        subjectDto.pageSubjectList allBySearch = subjectService.getAllBySearch(searchDto, pageable);
        return new subjectData<>(allBySearch.getSubjectDtoList().size(), allBySearch);
    }

    /**
     * Rest 현재 신청 학점
     */
    @PostMapping("/subject/mypoints")
    public subjectData<Integer> getmyPoint(@AuthenticationPrincipal CustomUserDetails currentUser) {
        Integer integer = subjectService.getMySubjectPoint(currentUser.getUser());
        return new subjectData<>(1, integer);
    }
}
