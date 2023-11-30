package b172.challenging.App.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAppNotificationTemplate is a Querydsl query type for AppNotificationTemplate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAppNotificationTemplate extends EntityPathBase<AppNotificationTemplate> {

    private static final long serialVersionUID = -1349449701L;

    public static final QAppNotificationTemplate appNotificationTemplate = new QAppNotificationTemplate("appNotificationTemplate");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final EnumPath<AppNotificationType> type = createEnum("type", AppNotificationType.class);

    public QAppNotificationTemplate(String variable) {
        super(AppNotificationTemplate.class, forVariable(variable));
    }

    public QAppNotificationTemplate(Path<? extends AppNotificationTemplate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppNotificationTemplate(PathMetadata metadata) {
        super(AppNotificationTemplate.class, metadata);
    }

}

