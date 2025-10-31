package com.example.TripAgora.template.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTemplateItinerary is a Querydsl query type for TemplateItinerary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemplateItinerary extends EntityPathBase<TemplateItinerary> {

    private static final long serialVersionUID = 719005006L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTemplateItinerary templateItinerary = new QTemplateItinerary("templateItinerary");

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

    public final TimePath<java.time.LocalTime> startTime = createTime("startTime", java.time.LocalTime.class);

    public final QTemplate template;

    public final StringPath title = createString("title");

    public QTemplateItinerary(String variable) {
        this(TemplateItinerary.class, forVariable(variable), INITS);
    }

    public QTemplateItinerary(Path<? extends TemplateItinerary> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTemplateItinerary(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTemplateItinerary(PathMetadata metadata, PathInits inits) {
        this(TemplateItinerary.class, metadata, inits);
    }

    public QTemplateItinerary(Class<? extends TemplateItinerary> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.template = inits.isInitialized("template") ? new QTemplate(forProperty("template"), inits.get("template")) : null;
    }

}

