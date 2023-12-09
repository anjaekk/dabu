package b172.challenging.app.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppNotification is a Querydsl query type for AppNotification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAppNotification extends EntityPathBase<AppNotification> {

    private static final long serialVersionUID = -778941343L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAppNotification appNotification = new QAppNotification("appNotification");

    public final QAppNotificationTemplate appNotificationTemplate;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final b172.challenging.auth.domain.QMember member;

    public final BooleanPath readStatus = createBoolean("readStatus");

    public QAppNotification(String variable) {
        this(AppNotification.class, forVariable(variable), INITS);
    }

    public QAppNotification(Path<? extends AppNotification> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAppNotification(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAppNotification(PathMetadata metadata, PathInits inits) {
        this(AppNotification.class, metadata, inits);
    }

    public QAppNotification(Class<? extends AppNotification> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.appNotificationTemplate = inits.isInitialized("appNotificationTemplate") ? new QAppNotificationTemplate(forProperty("appNotificationTemplate")) : null;
        this.member = inits.isInitialized("member") ? new b172.challenging.auth.domain.QMember(forProperty("member")) : null;
    }

}

