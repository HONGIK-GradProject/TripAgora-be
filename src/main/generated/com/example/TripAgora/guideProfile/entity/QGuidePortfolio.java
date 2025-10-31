package com.example.TripAgora.guideProfile.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGuidePortfolio is a Querydsl query type for GuidePortfolio
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGuidePortfolio extends EntityPathBase<GuidePortfolio> {

    private static final long serialVersionUID = -1299133566L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuidePortfolio guidePortfolio = new QGuidePortfolio("guidePortfolio");

    public final QGuideProfile guideProfile;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<GuidePortfolio.PortfolioType> type = createEnum("type", GuidePortfolio.PortfolioType.class);

    public final StringPath url = createString("url");

    public QGuidePortfolio(String variable) {
        this(GuidePortfolio.class, forVariable(variable), INITS);
    }

    public QGuidePortfolio(Path<? extends GuidePortfolio> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGuidePortfolio(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGuidePortfolio(PathMetadata metadata, PathInits inits) {
        this(GuidePortfolio.class, metadata, inits);
    }

    public QGuidePortfolio(Class<? extends GuidePortfolio> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guideProfile = inits.isInitialized("guideProfile") ? new QGuideProfile(forProperty("guideProfile"), inits.get("guideProfile")) : null;
    }

}

