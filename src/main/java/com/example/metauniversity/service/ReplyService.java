package com.example.metauniversity.service;

import com.example.metauniversity.domain.Reply.Reply;
import com.example.metauniversity.domain.Reply.dto.ReplyDto;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.repository.BoardRepository;
import com.example.metauniversity.repository.ReplyRepository;
import com.example.metauniversity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    @Transactional
    public void saveReply(ReplyDto.InsertReply insertReply, User user){

        replyRepository.save(
            Reply
                .builder()
                    .boardId(boardRepository.getById(insertReply.getBoardId()))
                    .replyContent(insertReply.getReplyContent())
                    .user(user)
                    .isDeleted(false)
                    .build());
    }

    public List<ReplyDto.GetReply> getReplies(){

        return null;
    }


}
