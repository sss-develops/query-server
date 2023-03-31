package project.goorm.queryserver.test.helper.fixture.company;

import project.goorm.queryserver.business.core.domain.common.deleted.Deleted;
import project.goorm.queryserver.business.core.domain.company.entity.Company;

public class CompanyFixture {

    public static Company createCompany() {
        return new Company(
                "Naver",
                5,
                "https://image.rocketpunch.com/company/5466/naver_logo.png",
                Deleted.FALSE
        );
    }
}
