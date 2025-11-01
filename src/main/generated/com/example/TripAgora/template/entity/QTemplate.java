package com.example.TripAgora.template.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTemplate is a Querydsl query type for Template
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemplate extends EntityPathBase<Template> {

    private static final long serialVersionUID = 988856381L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTemplate template = new QTemplate("template");

    public final com.example.TripAgora.common.entity.QBaseEntity _super = new com.example.TripAgora.common.entity.QBaseEntity(this);

    public final NumberPath<Double> avgRating = createNumber("avgRating", Double.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.example.TripAgora.guideProfile.entity.QGuideProfile guideProfile;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Integer> reviewCount = createNumber("reviewCount", Integer.class);

    public final ListPath<com.example.TripAgora.review.entity.Review, com.example.TripAgora.review.entity.QReview> reviews = this.<com.example.TripAgora.review.entity.Review, com.example.TripAgora.review.entity.QReview>createList("reviews", com.example.TripAgora.review.entity.Review.class, com.example.TripAgora.review.entity.QReview.class, PathInits.DIRECT2);

    public final ListPath<com.example.TripAgora.session.entity.Session, com.example.TripAgora.session.entity.QSession> sessions = this.<com.example.TripAgora.session.entity.Session, com.example.TripAgora.session.entity.QSession>createList("sessions", com.example.TripAgora.session.entity.Session.class, com.example.TripAgora.session.entity.QSession.class, PathInits.DIRECT2);

    public final ListPath<TemplateImage, QTemplateImage> templateImages = this.<TemplateImage, QTemplateImage>createList("templateImages", TemplateImage.class, QTemplateImage.class, PathInits.DIRECT2);

    public final ListPath<TemplateItinerary, QTemplateItinerary> templateItineraries = this.<TemplateItinerary, QTemplateItinerary>createList("templateItineraries", TemplateItinerary.class, QTemplateItinerary.class, PathInits.DIRECT2);

    public final ListPath<com.example.TripAgora.region.entity.TemplateRegion, com.example.TripAgora.region.entity.QTemplateRegion> templateRegions = this.<com.example.TripAgora.region.entity.TemplateRegion, com.example.TripAgora.region.entity.QTemplateRegion>createList("templateRegions", com.example.TripAgora.region.entity.TemplateRegion.class, com.example.TripAgora.region.entity.QTemplateRegion.class, PathInits.DIRECT2);

    public final ListPath<com.example.TripAgora.tag.entity.TemplateTag, com.example.TripAgora.tag.entity.QTemplateTag> templateTags = this.<com.example.TripAgora.tag.entity.TemplateTag, com.example.TripAgora.tag.entity.QTemplateTag>createList("templateTags", com.example.TripAgora.tag.entity.TemplateTag.class, com.example.TripAgora.tag.entity.QTemplateTag.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QTemplate(String variable) {
        this(Template.class, forVariable(variable), INITS);
    }

    public QTemplate(Path<? extends Template> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTemplate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTemplate(PathMetadata metadata, PathInits inits) {
        this(Template.class, metadata, inits);
    }

    public QTemplate(Class<? extends Template> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guideProfile = inits.isInitialized("guideProfile") ? new com.example.TripAgora.guideProfile.entity.QGuideProfile(forProperty("guideProfile"), inits.get("guideProfile")) : null;
    }

}

