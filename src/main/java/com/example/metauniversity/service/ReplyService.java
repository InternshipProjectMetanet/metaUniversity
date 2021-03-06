package com.example.metauniversity.service;

import com.example.metauniversity.domain.Reply.Reply;
import com.example.metauniversity.domain.Reply.dto.ReplyDto;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.exception.NoSuchBoardException;
import com.example.metauniversity.repository.BoardRepository;
import com.example.metauniversity.repository.ReplyRepository;
import com.example.metauniversity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
                    .boardId(boardRepository.findById(insertReply.getBoardId()).orElseThrow(() -> new NoSuchBoardException("없는 게시글 입니다")))
                    .replyContent(insertReply.getReplyContent())
                    .user(user)
                    .isDeleted(false)
                    .build());
    }
    @Transactional
    public List<ReplyDto.GetReply> getReplies(Long boardId) {


        List<ReplyDto.GetReply> replies = replyRepository.findByBoardId(boardRepository.findById(boardId).orElseThrow(() -> new NoSuchBoardException("게시글이 없습니다."))).stream().map(
            (reply) -> {

                String url = "/img/account_circle.svg";
                try {
                    url = reply.getUser().getUserfile().getFile().getUrl();
                } catch (Exception e) {

                }
                return ReplyDto.GetReply.builder()
                    .replyId(reply.getReplyId())
                    .boardId(boardId)
                    .userName(reply.getUser().getUsersData().getUserName())
                    .replyContent(reply.getReplyContent())
                    .isDeleted(reply.isDeleted())
                    .imgUrl(url)
                    .build();
            }
        ).collect(Collectors.toList());


        return replies;
    }
    public void deleteReply(Long replyId){
        replyRepository.deleteById(replyId);
    }

}
