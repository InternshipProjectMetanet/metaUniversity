package com.example.metauniversity.domain.subject;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * Qsubject is a Querydsl query type for subject
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class Qsubject extends EntityPathBase<subject> {

    private static final long serialVersionUID = 315942922L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final Qsubject subject = new Qsubject("subject");

    public final com.example.metauniversity.domain.Base.QBaseEntity _super = new com.example.metauniversity.domain.Base.QBaseEntity(this);

    public final StringPath classRoom = createString("classRoom");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath day = createString("day");

    public final StringPath endTime = createString("endTime");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isMajor = createBoolean("isMajor");

    public final NumberPath<Integer> limited = createNumber("limited", Integer.class);

    public final StringPath startTime = createString("startTime");

    public final StringPath subjectDepartment = createString("subjectDepartment");

    public final NumberPath<Integer> subjectGrades = createNumber("subjectGrades", Integer.class);

    public final StringPath subjectIntro = createString("subjectIntro");

    public final NumberPath<Integer> subjectPoints = createNumber("subjectPoints", Integer.class);

    public final StringPath subjectTitle = createString("subjectTitle");

    public final ListPath<timeTable, QtimeTable> timeTables = this.<timeTable, QtimeTable>createList("timeTables", timeTable.class, QtimeTable.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final com.example.metauniversity.domain.User.QUser user;

    public Qsubject(String variable) {
        this(subject.class, forVariable(variable), INITS);
    }

    public Qsubject(Path<? extends subject> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public Qsubject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public Qsubject(PathMetadata metadata, PathInits inits) {
        this(subject.class, metadata, inits);
    }

    public Qsubject(Class<? extends subject> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.metauniversity.domain.User.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

