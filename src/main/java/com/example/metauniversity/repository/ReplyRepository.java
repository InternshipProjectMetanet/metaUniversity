package com.example.metauniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.metauniversity.domain.Reply.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	
}
