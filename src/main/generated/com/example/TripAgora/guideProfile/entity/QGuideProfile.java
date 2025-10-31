package com.example.TripAgora.guideProfile.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGuideProfile is a Querydsl query type for GuideProfile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGuideProfile extends EntityPathBase<GuideProfile> {

    private static final long serialVersionUID = 1846711907L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuideProfile guideProfile = new QGuideProfile("guideProfile");

    public final com.example.TripAgora.common.entity.QBaseEntity _super = new com.example.TripAgora.common.entity.QBaseEntity(this);

    public final StringPath bio = createString("bio");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<GuidePortfolio, QGuidePortfolio> portfolios = this.<GuidePortfolio, QGuidePortfolio>createList("portfolios", GuidePortfolio.class, QGuidePortfolio.class, PathInits.DIRECT2);

    public final ListPath<com.example.TripAgora.template.entity.Template, com.example.TripAgora.template.entity.QTemplate> templates = this.<com.example.TripAgora.template.entity.Template, com.example.TripAgora.template.entity.QTemplate>createList("templates", com.example.TripAgora.template.entity.Template.class, com.example.TripAgora.template.entity.QTemplate.class, PathInits.DIRECT2);

    public final NumberPath<Double> totalAvgRating = createNumber("totalAvgRating", Double.class);

    public final NumberPath<Integer> totalReviewCount = createNumber("totalReviewCount", Integer.class);

    public final com.example.TripAgora.user.entity.QUser user;

    public QGuideProfile(String variable) {
        this(GuideProfile.class, forVariable(variable), INITS);
    }

    public QGuideProfile(Path<? extends GuideProfile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGuideProfile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGuideProfile(PathMetadata metadata, PathInits inits) {
        this(GuideProfile.class, metadata, inits);
    }

    public QGuideProfile(Class<? extends GuideProfile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.TripAgora.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

