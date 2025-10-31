package com.example.TripAgora.region.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTemplateRegionId is a Querydsl query type for TemplateRegionId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QTemplateRegionId extends BeanPath<TemplateRegionId> {

    private static final long serialVersionUID = 1538576902L;

    public static final QTemplateRegionId templateRegionId = new QTemplateRegionId("templateRegionId");

    public final NumberPath<Long> regionId = createNumber("regionId", Long.class);

    public final NumberPath<Long> templateId = createNumber("templateId", Long.class);

    public QTemplateRegionId(String variable) {
        super(TemplateRegionId.class, forVariable(variable));
    }

    public QTemplateRegionId(Path<? extends TemplateRegionId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTemplateRegionId(PathMetadata metadata) {
        super(TemplateRegionId.class, metadata);
    }

}

