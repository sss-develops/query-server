package project.goorm.queryserver.test.integration.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import project.goorm.queryserver.business.core.domain.company.entity.Company;
import project.goorm.queryserver.business.web.common.paging.Cursor;
import project.goorm.queryserver.business.web.company.application.CompanySearchQuery;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;
import project.goorm.queryserver.common.configuration.rdb.DatabaseTestBase;
import project.goorm.queryserver.test.helper.helper.PersistenceHelper;
import project.goorm.queryserver.test.integration.IntegrationTestBase;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static project.goorm.queryserver.test.helper.fixture.company.CompanyListFixture.createCompanyList;

@DisplayName("검색어로 시작하는 회사 이름 조회 통합 테스트")
public class CompanySearchByNameIntegrationTest extends DatabaseTestBase {

    @Autowired
    private CompanySearchQuery companySearchQuery;

    @Autowired
    private PersistenceHelper persistenceHelper;

    @Test
    @DisplayName("검색어로 시작하는 회사가 존재하면 해당하는 회사들의 리스트를 출력해준다.")
    void when_company_corresponding_to_the_search_keyword_exist_response_should_be_not_null_and_accurate() {
        //given
        List<Company> companyList = createCompanyList();
        for (Company company: companyList
             ) {
            persistenceHelper.persist(company);
        }
        String companyNameTest = "ac";
        Cursor cursor = Cursor.from(0L, 10);
        //when
        List<CompanyResponse> companyResponseList = companySearchQuery.searchCompaniesByName(cursor, companyNameTest);
        List<String> ignoreCaseList = companyResponseList.stream().map(l -> l.getCompanyName()).collect(Collectors.toList());
        //then
        assertThat(ignoreCaseList)
                .extracting(String::toLowerCase)
                .allMatch(s -> s.startsWith(companyNameTest));


    }

    @Test
    @DisplayName("검색어로 시작하는 회사가 존재하지 않으면 빈 리스트를 출력해준다.")
    void when_company_corresponding_to_the_search_keyword_not_exist_response_should_be_null() {
        //given
        List<Company> companyList = createCompanyList();
        for (Company company: companyList
        ) {
            persistenceHelper.persist(company);
        }
        String companyNameTest = "asd123qwe4321";
        Cursor cursor = Cursor.from(0L, 10);
        //when
        List<CompanyResponse> companyResponseList = companySearchQuery.searchCompaniesByName(cursor, companyNameTest);
        //then
        assertEquals(0, companyResponseList.size());
    }

    @Test
    @DisplayName("검색어로 시작하는 회사가 존재하고 커서가 특정 되었을 때  해당하는 회사들의 리스트를 출력해준다.")
    void when_company_corresponding_to_the_search_keyword_exist_and_cursor_be_specified_response_should_be_not_null_and_accurate() {
        //given
        List<Company> companyList = createCompanyList();
        for (Company company: companyList
        ) {
            persistenceHelper.persist(company);
        }
        String companyNameTest = "ac";
        Long cursorNext = 3L;
        int cursorPageSize = 5;
        //when
        Cursor cursor = Cursor.from(cursorNext, cursorPageSize);
        List<CompanyResponse> companyResponseList = companySearchQuery.searchCompaniesByName(cursor, companyNameTest);
        //then
        for (int i = 0; i < cursorPageSize; i++) {
            assertThat(companyResponseList.get(i).getCompanyName())
                    .isEqualTo(companyList
                            .get(cursorNext.intValue() + i).getCompanyName());
        }
    }
}
