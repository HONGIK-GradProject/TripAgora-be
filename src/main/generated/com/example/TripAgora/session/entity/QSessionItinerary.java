package com.example.TripAgora.session.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSessionItinerary is a Querydsl query type for SessionItinerary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSessionItinerary extends EntityPathBase<SessionItinerary> {

    private static final long serialVersionUID = 1135058332L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSessionItinerary sessionItinerary = new QSessionItinerary("sessionItinerary");

    public final com.example.TripAgora.common.entity.QBaseEntity _super = new com.example.TripAgora.common.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> day = createNumber("day", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final QSession session;

    public final TimePath<java.time.LocalTime> startTime = createTime("startTime", java.time.LocalTime.class);

    public final StringPath title = createString("title");

    public QSessionItinerary(String variable) {
        this(SessionItinerary.class, forVariable(variable), INITS);
    }

    public QSessionItinerary(Path<? extends SessionItinerary> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSessionItinerary(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSessionItinerary(PathMetadata metadata, PathInits inits) {
        this(SessionItinerary.class, metadata, inits);
    }

    public QSessionItinerary(Class<? extends SessionItinerary> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.session = inits.isInitialized("session") ? new QSession(forProperty("session"), inits.get("session")) : null;
    }

}

