package b172.challenging.gathering.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGatheringSavingCertification is a Querydsl query type for GatheringSavingCertification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGatheringSavingCertification extends EntityPathBase<GatheringSavingCertification> {

    private static final long serialVersionUID = 1259445290L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGatheringSavingCertification gatheringSavingCertification = new QGatheringSavingCertification("gatheringSavingCertification");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final QGatheringSavingLog gatheringSavingLog;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public QGatheringSavingCertification(String variable) {
        this(GatheringSavingCertification.class, forVariable(variable), INITS);
    }

    public QGatheringSavingCertification(Path<? extends GatheringSavingCertification> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGatheringSavingCertification(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGatheringSavingCertification(PathMetadata metadata, PathInits inits) {
        this(GatheringSavingCertification.class, metadata, inits);
    }

    public QGatheringSavingCertification(Class<? extends GatheringSavingCertification> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gatheringSavingLog = inits.isInitialized("gatheringSavingLog") ? new QGatheringSavingLog(forProperty("gatheringSavingLog"), inits.get("gatheringSavingLog")) : null;
    }

}

