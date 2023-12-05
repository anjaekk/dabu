package b172.challenging.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserSample is a Querydsl query type for UserSample
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserSample extends EntityPathBase<UserSample> {

    private static final long serialVersionUID = -2025592529L;

    public static final QUserSample userSample = new QUserSample("userSample");

    public final StringPath email = createString("email");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public QUserSample(String variable) {
        super(UserSample.class, forVariable(variable));
    }

    public QUserSample(Path<? extends UserSample> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserSample(PathMetadata metadata) {
        super(UserSample.class, metadata);
    }

}

