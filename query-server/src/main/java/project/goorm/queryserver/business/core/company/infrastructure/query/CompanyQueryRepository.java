package project.goorm.queryserver.business.core.company.infrastructure.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyQueryRepository {

    private final JPAQueryFactory queryFactory;

    public CompanyQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
