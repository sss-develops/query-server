package project.goorm.queryserver.test.integration.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.goorm.queryserver.business.web.common.paging.Cursor;
import project.goorm.queryserver.business.web.company.application.CompanySearchQuery;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;
import project.goorm.queryserver.test.helper.helper.PersistenceHelper;
import project.goorm.queryserver.test.integration.IntegrationTestBase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CompanySearchByNameIntegrationTest extends IntegrationTestBase {

    @Autowired
    private CompanySearchQuery companySearchQuery;

    @Autowired
    private PersistenceHelper persistenceHelper;

    @Test
    @DisplayName("검색어로 시작하는 회사가 존재하면 회사들의 리스트를 출력해준다.")
    void t1() {
        String companyNameTest = "aa";
        Cursor cursor = Cursor.from(0L, 10);

        List<CompanyResponse> companyResponseList = companySearchQuery.searchCompaniesName(cursor, companyNameTest);

        assertThat(companyResponseList)
                .extracting("companyName")
                .contains(companyNameTest);
    }

    @Test
    @DisplayName("검색어로 시작하는 회사가 존재하지 않으면 빈 리스트를 출력해준다.")
    void t2() {
        String companyNameTest = "aaaaaaaaaaa";
        Cursor cursor = Cursor.from(0L, 10);

        List<CompanyResponse> companyResponseList = companySearchQuery.searchCompaniesName(cursor, companyNameTest);

        assertEquals(0, companyResponseList.size());
    }
}
