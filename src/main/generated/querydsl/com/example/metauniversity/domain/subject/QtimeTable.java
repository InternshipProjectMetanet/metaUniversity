package com.example.metauniversity.domain.subject;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QtimeTable is a Querydsl query type for timeTable
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QtimeTable extends EntityPathBase<timeTable> {

    private static final long serialVersionUID = -1554185121L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QtimeTable timeTable = new QtimeTable("timeTable");

    public final com.example.metauniversity.domain.Base.QBaseEntity _super = new com.example.metauniversity.domain.Base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath status = createBoolean("status");

    public final Qsubject subject;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final com.example.metauniversity.domain.User.QUser user;

    public QtimeTable(String variable) {
        this(timeTable.class, forVariable(variable), INITS);
    }

    public QtimeTable(Path<? extends timeTable> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QtimeTable(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QtimeTable(PathMetadata metadata, PathInits inits) {
        this(timeTable.class, metadata, inits);
    }

    public QtimeTable(Class<? extends timeTable> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.subject = inits.isInitialized("subject") ? new Qsubject(forProperty("subject"), inits.get("subject")) : null;
        this.user = inits.isInitialized("user") ? new com.example.metauniversity.domain.User.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

