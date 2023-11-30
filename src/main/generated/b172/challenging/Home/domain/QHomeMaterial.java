package b172.challenging.Home.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHomeMaterial is a Querydsl query type for HomeMaterial
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHomeMaterial extends EntityPathBase<HomeMaterial> {

    private static final long serialVersionUID = 485568211L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHomeMaterial homeMaterial = new QHomeMaterial("homeMaterial");

    public final QHome home;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> needed = createNumber("needed", Long.class);

    public QHomeMaterial(String variable) {
        this(HomeMaterial.class, forVariable(variable), INITS);
    }

    public QHomeMaterial(Path<? extends HomeMaterial> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHomeMaterial(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHomeMaterial(PathMetadata metadata, PathInits inits) {
        this(HomeMaterial.class, metadata, inits);
    }

    public QHomeMaterial(Class<? extends HomeMaterial> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.home = inits.isInitialized("home") ? new QHome(forProperty("home")) : null;
    }

}

