package com.example.metauniversity.domain.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -594116770L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final com.example.metauniversity.domain.Base.QBaseEntity _super = new com.example.metauniversity.domain.Base.QBaseEntity(this);

    public final ListPath<com.example.metauniversity.domain.File.BoardFile, com.example.metauniversity.domain.File.QBoardFile> boardfile = this.<com.example.metauniversity.domain.File.BoardFile, com.example.metauniversity.domain.File.QBoardFile>createList("boardfile", com.example.metauniversity.domain.File.BoardFile.class, com.example.metauniversity.domain.File.QBoardFile.class, PathInits.DIRECT2);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final ListPath<com.example.metauniversity.domain.Reply.Reply, com.example.metauniversity.domain.Reply.QReply> replies = this.<com.example.metauniversity.domain.Reply.Reply, com.example.metauniversity.domain.Reply.QReply>createList("replies", com.example.metauniversity.domain.Reply.Reply.class, com.example.metauniversity.domain.Reply.QReply.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final com.example.metauniversity.domain.User.QUser user;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.metauniversity.domain.User.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

