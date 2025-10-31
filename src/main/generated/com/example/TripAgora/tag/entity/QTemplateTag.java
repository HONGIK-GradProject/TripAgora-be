package com.example.TripAgora.tag.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTemplateTag is a Querydsl query type for TemplateTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemplateTag extends EntityPathBase<TemplateTag> {

    private static final long serialVersionUID = 2037556309L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTemplateTag templateTag = new QTemplateTag("templateTag");

    public final QTemplateTagId id;

    public final QTag tag;

    public final com.example.TripAgora.template.entity.QTemplate template;

    public QTemplateTag(String variable) {
        this(TemplateTag.class, forVariable(variable), INITS);
    }

    public QTemplateTag(Path<? extends TemplateTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTemplateTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTemplateTag(PathMetadata metadata, PathInits inits) {
        this(TemplateTag.class, metadata, inits);
    }

    public QTemplateTag(Class<? extends TemplateTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QTemplateTagId(forProperty("id")) : null;
        this.tag = inits.isInitialized("tag") ? new QTag(forProperty("tag")) : null;
        this.template = inits.isInitialized("template") ? new com.example.TripAgora.template.entity.QTemplate(forProperty("template"), inits.get("template")) : null;
    }

}

