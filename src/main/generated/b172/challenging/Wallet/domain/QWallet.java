package b172.challenging.Wallet.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWallet is a Querydsl query type for Wallet
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWallet extends EntityPathBase<Wallet> {

    private static final long serialVersionUID = 10968704L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWallet wallet = new QWallet("wallet");

    public final b172.challenging.Home.domain.QHome home;

    public final StringPath homeName = createString("homeName");

    public final DateTimePath<java.time.LocalDateTime> homeUpdatedAt = createDateTime("homeUpdatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final b172.challenging.auth.domain.QMember member;

    public final NumberPath<Long> point = createNumber("point", Long.class);

    public final NumberPath<Long> saveAmount = createNumber("saveAmount", Long.class);

    public QWallet(String variable) {
        this(Wallet.class, forVariable(variable), INITS);
    }

    public QWallet(Path<? extends Wallet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWallet(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWallet(PathMetadata metadata, PathInits inits) {
        this(Wallet.class, metadata, inits);
    }

    public QWallet(Class<? extends Wallet> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.home = inits.isInitialized("home") ? new b172.challenging.Home.domain.QHome(forProperty("home")) : null;
        this.member = inits.isInitialized("member") ? new b172.challenging.auth.domain.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

