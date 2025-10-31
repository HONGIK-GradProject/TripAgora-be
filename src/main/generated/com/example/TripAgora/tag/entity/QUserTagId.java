package com.example.TripAgora.tag.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserTagId is a Querydsl query type for UserTagId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserTagId extends BeanPath<UserTagId> {

    private static final long serialVersionUID = 96676383L;

    public static final QUserTagId userTagId = new QUserTagId("userTagId");

    public final NumberPath<Long> tagId = createNumber("tagId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserTagId(String variable) {
        super(UserTagId.class, forVariable(variable));
    }

    public QUserTagId(Path<? extends UserTagId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserTagId(PathMetadata metadata) {
        super(UserTagId.class, metadata);
    }

}

