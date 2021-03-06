package com.example.metauniversity.service;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UserTyped;
import com.example.metauniversity.domain.board.Board;
import com.example.metauniversity.domain.board.dto.boardDto;
import com.example.metauniversity.domain.subject.dto.subjectDto;
import com.example.metauniversity.domain.subject.subject;
import com.example.metauniversity.domain.subject.timeTable;
import com.example.metauniversity.exception.CannotAddSubjectException;
import com.example.metauniversity.exception.NoCreateSubjectException;
import com.example.metauniversity.exception.NoSuchSubjectException;
import com.example.metauniversity.exception.NoSuchTimeTableException;
import com.example.metauniversity.repository.subject.subjectRepository;
import com.example.metauniversity.repository.subject.timeTableRepository;
import com.example.metauniversity.util.AboutTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class subjectService {

    private final subjectRepository subjectRepository;
    private final timeTableRepository timeTableRepository;

    @Transactional
    public void addSubject(subjectDto.create createDto, User user) {

        /**
         * 예외 처리
         * 강의실이 같은 subject 데이터를 불러와서
         * 요일-시간 겹치면 오류
         */

        if(!user.getUsersData().getUserType().equals(UserTyped.WORKER)) {
            throw new NoCreateSubjectException("관리자만 등록이 가능합니다.");
        }

        if(AboutTime.BooleanCreateSubject(createDto, subjectRepository.findAllByClassRoom(createDto.getClassRoom()))) {
            System.out.println("isMajor : " + createDto.getIsMajor());
            subjectRepository.save(subject.create(createDto, user));
        } else {
            throw new NoCreateSubjectException("해당 강의실의 원하시는 시간대는 이미 예약 되었습니다.");
        }
    }

    public subjectDto.pageSubjectList getAll(Pageable pageable) {

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 5);

        Page<subjectDto.getList> subjectList = subjectRepository.getAllSubject(pageable);

        List<subjectDto.getList> returnlist = new ArrayList<>();

        for (subjectDto.getList list : subjectList.getContent()) {
            subjectDto.getList listdto = subjectDto.getList.builder()
                    .subjectId(list.getSubjectId())
                    .professor(list.getProfessor())
                    .subjectTitle(list.getSubjectTitle())
                    .subjectPoints(list.getSubjectPoints())
                    .isMajor(list.getIsMajor())
                    .subjectDepaetment(list.getSubjectDepaetment())
                    .subjectGrades(list.getSubjectGrades())
                    .limited(list.getLimited())
                    .classRoom(list.getClassRoom())
                    .day(list.getDay())
                    .startTime(list.getStartTime())
                    .endTime(list.getEndTime())
                    .build();

            returnlist.add(listdto);
        }

        subjectDto.pageSubjectList pageList = subjectDto.pageSubjectList.builder()
                    .pageSize(subjectList.getSize())
                    .pageNumber(subjectList.getNumber())
                    .totalPages(subjectList.getTotalPages())
                    .totalElements(subjectList.getTotalElements())
                    .subjectDtoList(returnlist)
                    .build();

        return pageList;
    }

    public List<subjectDto.getList> getMySubject(User user) {
        return timeTableRepository.getAllMySubject(user.getId()).stream()
                .map(s -> new subjectDto.getList(s)).collect(Collectors.toList());
    }

    public Integer getMySubjectPoint(User user) {
        Integer myPoints = 0;
        List<timeTable> allMySubject = timeTableRepository.getAllMySubject(user.getId());
        for (timeTable timeTable : allMySubject) {
            myPoints += timeTable.getSubject().getSubjectPoints();
        }
        return myPoints;
    }

    public subjectDto.pageSubjectList getAllBySearch(subjectDto.search searchDto, Pageable pageable) {

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 5);

        Page<subject> subjectList = subjectRepository.findAllBySearch(searchDto, pageable);

        List<subjectDto.getList> returnlist = new ArrayList<>();

        for (subject list : subjectList.getContent()) {
            subjectDto.getList listdto = subjectDto.getList.builder()
                    .subjectId(list.getId())
                    .professor(list.getUser().getUsersData().getUserName())
                    .subjectTitle(list.getSubjectTitle())
                    .subjectPoints(list.getSubjectPoints())
                    .isMajor(list.getIsMajor() ? "전공" : "교양")
                    .subjectDepaetment(list.getSubjectDepartment())
                    .subjectGrades(list.getSubjectGrades())
                    .limited(list.getLimited())
                    .classRoom(list.getClassRoom())
                    .day(list.getDay())
                    .startTime(list.getStartTime())
                    .endTime(list.getEndTime())
                    .build();

            returnlist.add(listdto);
        }

        subjectDto.pageSubjectList pageList = subjectDto.pageSubjectList.builder()
                .pageSize(subjectList.getSize())
                .pageNumber(subjectList.getNumber())
                .totalPages(subjectList.getTotalPages())
                .totalElements(subjectList.getTotalElements())
                .subjectDtoList(returnlist)
                .build();

        return pageList;
    }

    @Transactional
    public subjectDto.enroll EnrollSubject(Long subjectId, User user) {

        Integer currentPoints = 0;
        subject subject = subjectRepository.findbyIdFetch(subjectId)
                .orElseThrow(() -> new NoSuchSubjectException("등록되어있는 수업이 아닙니다."));
        List<timeTable> mySubjectList = timeTableRepository.getMySubjectToEnroll(user.getId()); // 현재 내가 수강신청한 과목 목록
        for (timeTable timeTable : mySubjectList) {
            if(timeTable.getSubject().getId() == subjectId && timeTable.getUser().getId() == user.getId()) {
                throw new CannotAddSubjectException("이미 수강신청된 과목입니다.");
            }
            currentPoints += timeTable.getSubject().getSubjectPoints();
        }

        if(!AboutTime.BooleanEnroll(subject, mySubjectList)) {
            throw new CannotAddSubjectException("시간대 중복입니다.");
        }

        if(currentPoints + subject.getSubjectPoints() < 18) {
            // 현재 학점 + 선택한 과목 학점이 18학점 미만이라면
            if(timeTableRepository.exist(subjectId, user.getId())) {
                //과거에 신청한 적이 있다(테이블이 수강신청상태 == false인 row가 존재한다.
                Boolean aBoolean = timeTableRepository.getMyPreSubject(subjectId, user.getId()).setStatus();
                return new subjectDto.enroll(subject, aBoolean);
            }

            timeTable myNewTimeTable = timeTable.builder()
                    .user(user)
                    .subject(subject)
                    .status(true)
                    .build();

            timeTableRepository.save(myNewTimeTable);

            return new subjectDto.enroll(subject, myNewTimeTable.getStatus());
        } else {
            throw new CannotAddSubjectException("신청 학점이 18학점을 넘을 수 없습니다.");
        }
    }

    @Transactional
    public subjectDto.cancel cancelSubject(Long subjectId, Long userId) {
        timeTable mySubject = timeTableRepository.getMySubject(userId, subjectId)
                .orElseThrow(() -> new NoSuchTimeTableException("현재 수강목록에 없습니다."));
        mySubject.setStatus();

        return new subjectDto.cancel(subjectId, mySubject.getSubject().getSubjectTitle(), mySubject.getStatus());
    }

}
