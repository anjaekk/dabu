package b172.challenging.Home.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHome is a Querydsl query type for Home
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHome extends EntityPathBase<Home> {

    private static final long serialVersionUID = -1474842228L;

    public static final QHome home = new QHome("home");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Long> level = createNumber("level", Long.class);

    public final StringPath name = createString("name");

    public QHome(String variable) {
        super(Home.class, forVariable(variable));
    }

    public QHome(Path<? extends Home> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHome(PathMetadata metadata) {
        super(Home.class, metadata);
    }

}

