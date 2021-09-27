package com.example.metauniversity.domain.board;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.metauniversity.domain.Base.BaseEntity;
import com.example.metauniversity.domain.File.BoardFile;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.board.dto.boardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardId")
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    
    @OneToMany(mappedBy = "board")
    private List<BoardFile> boardfile;
    
    private String title;
    @Column(length = 2000)
    private String content;
    
    // 게시글 수정
    public void update(boardDto.updateBoard updateDto) {
        this.title = updateDto.getTitle();
    	this.content = updateDto.getContent();
	}
}
