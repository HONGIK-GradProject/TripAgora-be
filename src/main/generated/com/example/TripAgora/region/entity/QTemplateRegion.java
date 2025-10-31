package com.example.TripAgora.region.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTemplateRegion is a Querydsl query type for TemplateRegion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemplateRegion extends EntityPathBase<TemplateRegion> {

    private static final long serialVersionUID = -682197109L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTemplateRegion templateRegion = new QTemplateRegion("templateRegion");

    public final QTemplateRegionId id;

    public final QRegion region;

    public final com.example.TripAgora.template.entity.QTemplate template;

    public QTemplateRegion(String variable) {
        this(TemplateRegion.class, forVariable(variable), INITS);
    }

    public QTemplateRegion(Path<? extends TemplateRegion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTemplateRegion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTemplateRegion(PathMetadata metadata, PathInits inits) {
        this(TemplateRegion.class, metadata, inits);
    }

    public QTemplateRegion(Class<? extends TemplateRegion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QTemplateRegionId(forProperty("id")) : null;
        this.region = inits.isInitialized("region") ? new QRegion(forProperty("region"), inits.get("region")) : null;
        this.template = inits.isInitialized("template") ? new com.example.TripAgora.template.entity.QTemplate(forProperty("template"), inits.get("template")) : null;
    }

}

