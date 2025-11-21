package com.example.TripAgora.location.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLocation is a Querydsl query type for UserLocation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLocation extends EntityPathBase<UserLocation> {

    private static final long serialVersionUID = 1031288478L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLocation userLocation = new QUserLocation("userLocation");

    public final com.example.TripAgora.common.entity.QBaseEntity _super = new com.example.TripAgora.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.example.TripAgora.room.entity.QRoom room;

    public final com.example.TripAgora.user.entity.QUser user;

    public QUserLocation(String variable) {
        this(UserLocation.class, forVariable(variable), INITS);
    }

    public QUserLocation(Path<? extends UserLocation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLocation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLocation(PathMetadata metadata, PathInits inits) {
        this(UserLocation.class, metadata, inits);
    }

    public QUserLocation(Class<? extends UserLocation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new com.example.TripAgora.room.entity.QRoom(forProperty("room"), inits.get("room")) : null;
        this.user = inits.isInitialized("user") ? new com.example.TripAgora.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

