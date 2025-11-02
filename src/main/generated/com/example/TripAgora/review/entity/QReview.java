package com.example.TripAgora.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 1000634681L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final com.example.TripAgora.common.entity.QBaseEntity _super = new com.example.TripAgora.common.entity.QBaseEntity(this);

    public final com.example.TripAgora.user.entity.QUser author;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.example.TripAgora.guideProfile.entity.QGuideProfile guideProfile;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final com.example.TripAgora.session.entity.QSession session;

    public final com.example.TripAgora.template.entity.QTemplate template;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.example.TripAgora.user.entity.QUser(forProperty("author"), inits.get("author")) : null;
        this.guideProfile = inits.isInitialized("guideProfile") ? new com.example.TripAgora.guideProfile.entity.QGuideProfile(forProperty("guideProfile"), inits.get("guideProfile")) : null;
        this.session = inits.isInitialized("session") ? new com.example.TripAgora.session.entity.QSession(forProperty("session"), inits.get("session")) : null;
        this.template = inits.isInitialized("template") ? new com.example.TripAgora.template.entity.QTemplate(forProperty("template"), inits.get("template")) : null;
    }

}

