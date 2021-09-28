package com.example.metauniversity.repository;

import com.example.metauniversity.domain.Reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
}
