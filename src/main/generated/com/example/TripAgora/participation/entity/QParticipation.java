package com.example.TripAgora.participation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParticipation is a Querydsl query type for Participation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParticipation extends EntityPathBase<Participation> {

    private static final long serialVersionUID = -850400945L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParticipation participation = new QParticipation("participation");

    public final com.example.TripAgora.common.entity.QBaseEntity _super = new com.example.TripAgora.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final EnumPath<com.example.TripAgora.user.entity.Role> role = createEnum("role", com.example.TripAgora.user.entity.Role.class);

    public final com.example.TripAgora.session.entity.QSession session;

    public final com.example.TripAgora.user.entity.QUser user;

    public QParticipation(String variable) {
        this(Participation.class, forVariable(variable), INITS);
    }

    public QParticipation(Path<? extends Participation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParticipation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParticipation(PathMetadata metadata, PathInits inits) {
        this(Participation.class, metadata, inits);
    }

    public QParticipation(Class<? extends Participation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.session = inits.isInitialized("session") ? new com.example.TripAgora.session.entity.QSession(forProperty("session"), inits.get("session")) : null;
        this.user = inits.isInitialized("user") ? new com.example.TripAgora.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

