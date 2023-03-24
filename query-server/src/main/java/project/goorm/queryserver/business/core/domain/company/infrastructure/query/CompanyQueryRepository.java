package project.goorm.queryserver.business.core.domain.company.infrastructure.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import project.goorm.queryserver.business.core.domain.company.entity.Company;

import java.util.List;
import java.util.Optional;

import static project.goorm.queryserver.business.core.domain.company.entity.QCompany.company;

@Repository
public class CompanyQueryRepository {

    private final JPAQueryFactory queryFactory;

    public CompanyQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Optional<Company> findCompanyById(Long companyId) {
        return Optional.ofNullable(queryFactory.selectFrom(company)
                .where(company.companyId.eq(companyId)).fetchOne());
    }

    public Optional<Company> findCompanyByName(String companyName) {
        return Optional.ofNullable(queryFactory.selectFrom(company)
                .where(company.companyName.eq(companyName)).fetchOne());
    }

    public List<Company> findCompanyAll() {
        return queryFactory.selectFrom(company)
                .fetch();
    }
}
