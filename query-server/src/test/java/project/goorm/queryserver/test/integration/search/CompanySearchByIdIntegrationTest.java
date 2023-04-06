package project.goorm.queryserver.test.integration.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.goorm.queryserver.business.core.domain.company.entity.Company;
import project.goorm.queryserver.business.web.company.application.CompanySearchQuery;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;
import project.goorm.queryserver.common.configuration.rdb.DatabaseTestBase;
import project.goorm.queryserver.common.exception.common.SSSTeamException;
import project.goorm.queryserver.test.helper.fixture.company.CompanyFixture;
import project.goorm.queryserver.test.helper.helper.PersistenceHelper;
import project.goorm.queryserver.test.integration.IntegrationTestBase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static project.goorm.queryserver.business.core.domain.company.CompanyTypeException.COMPANY_NOT_FOUND_EXCEPTION;

@DisplayName("회사 상세조회 통합 테스트")
public class CompanySearchByIdIntegrationTest extends DatabaseTestBase {

    @Autowired
    private CompanySearchQuery companySearchQuery;

    @Autowired
    private PersistenceHelper persistenceHelper;

    @Test
    @DisplayName("회사가 존재하면 응답이 Null이 아니다.")
    void when_company_exist_response_should_not_be_null() {
        Company company = persistenceHelper.persist(CompanyFixture.createCompany());

        CompanyResponse companyResponse = companySearchQuery.searchCompanyId(company.getCompanyId());

        assertNotNull(companyResponse);
    }

    @Test
    @DisplayName("회사가 존재하지 않으면 SSSTeamException이 발생한다.")
    void when_company_not_exist_response_should_be_null() {
        Long invalidCompanyId = Long.MAX_VALUE;

        assertThatThrownBy(() -> companySearchQuery.searchCompanyId(invalidCompanyId))
                .isInstanceOf(SSSTeamException.class)
                .hasMessage(COMPANY_NOT_FOUND_EXCEPTION.getMessage());
    }
}
