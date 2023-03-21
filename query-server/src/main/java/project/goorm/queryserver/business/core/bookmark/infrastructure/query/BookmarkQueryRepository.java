package project.goorm.queryserver.business.core.bookmark.infrastructure.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BookmarkQueryRepository {

    private final JPAQueryFactory queryFactory;

    public BookmarkQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
