package com.example.TripAgora.template.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTemplateImage is a Querydsl query type for TemplateImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemplateImage extends EntityPathBase<TemplateImage> {

    private static final long serialVersionUID = 701929246L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTemplateImage templateImage = new QTemplateImage("templateImage");

    public final com.example.TripAgora.common.entity.QBaseEntity _super = new com.example.TripAgora.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> displayOrder = createNumber("displayOrder", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final QTemplate template;

    public QTemplateImage(String variable) {
        this(TemplateImage.class, forVariable(variable), INITS);
    }

    public QTemplateImage(Path<? extends TemplateImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTemplateImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTemplateImage(PathMetadata metadata, PathInits inits) {
        this(TemplateImage.class, metadata, inits);
    }

    public QTemplateImage(Class<? extends TemplateImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.template = inits.isInitialized("template") ? new QTemplate(forProperty("template"), inits.get("template")) : null;
    }

}

