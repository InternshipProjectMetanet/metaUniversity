package com.example.metauniversity.repository.subject;

import com.example.metauniversity.domain.subject.dto.subjectDto;
import com.example.metauniversity.domain.subject.subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface subjectRepositoryCustom {

    List<subject> findAllByClassRoom(String classRoom);
    Page<subject> findAllBySearch(subjectDto.search search, Pageable pageable);
    Page<subjectDto.getList> getAllSubject(Pageable pageable);
    Optional<subject> findbyIdFetch(Long subjectId);
}
