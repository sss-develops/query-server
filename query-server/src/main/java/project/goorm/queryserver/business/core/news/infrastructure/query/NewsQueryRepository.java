package project.goorm.queryserver.business.core.news.infrastructure.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class NewsQueryRepository {

    private final JPAQueryFactory queryFactory;

    public NewsQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
