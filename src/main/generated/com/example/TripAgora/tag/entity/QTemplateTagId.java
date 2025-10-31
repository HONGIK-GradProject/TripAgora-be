package com.example.TripAgora.tag.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTemplateTagId is a Querydsl query type for TemplateTagId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QTemplateTagId extends BeanPath<TemplateTagId> {

    private static final long serialVersionUID = -413471664L;

    public static final QTemplateTagId templateTagId = new QTemplateTagId("templateTagId");

    public final NumberPath<Long> tagId = createNumber("tagId", Long.class);

    public final NumberPath<Long> templateId = createNumber("templateId", Long.class);

    public QTemplateTagId(String variable) {
        super(TemplateTagId.class, forVariable(variable));
    }

    public QTemplateTagId(Path<? extends TemplateTagId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTemplateTagId(PathMetadata metadata) {
        super(TemplateTagId.class, metadata);
    }

}

