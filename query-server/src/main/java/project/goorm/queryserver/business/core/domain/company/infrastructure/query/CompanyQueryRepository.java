package project.goorm.queryserver.business.core.domain.company.infrastructure.query;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import project.goorm.queryserver.business.core.domain.common.deleted.Deleted;
import project.goorm.queryserver.business.core.domain.company.entity.Company;
import project.goorm.queryserver.business.core.domain.company.entity.TopSearchedCompany;
import project.goorm.queryserver.business.web.common.paging.Cursor;

import java.util.List;
import java.util.Optional;

import static project.goorm.queryserver.business.core.domain.company.entity.QCompany.company;
import static project.goorm.queryserver.business.core.domain.company.entity.QTopSearchedCompany.topSearchedCompany;

@Repository
public class CompanyQueryRepository {

    private final JPAQueryFactory queryFactory;

    public CompanyQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Optional<Company> searchCompanyId(Long companyId) {
        return Optional.ofNullable(
                queryFactory.selectFrom(company)
                        .where(
                                company.companyId.eq(companyId).and(
                                        company.deleted.eq(Deleted.FALSE)
                                )
                        )
                        .fetchOne()
        );
    }

    public Optional<Company> findCompanyByName(String companyName) {
        return Optional.ofNullable(queryFactory.selectFrom(company)
                .where(company.companyName.eq(companyName)).fetchOne());
    }

    public List<Company> searchCompaniesName(
            Cursor cursor,
            String companyName
    ) {
        return queryFactory.selectFrom(company)
                .where(
                        company.companyName.like(companyName + "%")
                                .and(company.deleted.eq(Deleted.FALSE))
                                .and(company.companyId.gt(cursor.getNext()))
                )
                .orderBy(company.companyId.asc())
                .limit(cursor.getPageSize())
                .fetch();
    }

    public List<Company> findCompanyAll() {
        return queryFactory.selectFrom(company)
                .fetch();
    }

    public List<TopSearchedCompany> findTopSearchedCompanies() {
        return queryFactory.selectFrom(topSearchedCompany)
                .fetch();
    }
}
