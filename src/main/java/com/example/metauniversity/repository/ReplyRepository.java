package com.example.metauniversity.repository;

import com.example.metauniversity.domain.Reply.Reply;
import com.example.metauniversity.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    List<Reply> findByBoardId(Board BoardId);
    
    void deleteByBoardIdBoardId(Long boardId);
}
