package project.goorm.queryserver.business.web.company.application;

import project.goorm.queryserver.business.web.common.paging.Cursor;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;
import project.goorm.queryserver.common.annotation.helper.ApplicationLayer;

import java.util.List;

@ApplicationLayer
public interface CompanySearchQuery {

    CompanyResponse searchCompanyId(Long companyId);

    List<CompanyResponse> searchCompaniesByName(Cursor cursor, String companyName);

    List<CompanyResponse> getTopSearchedCompanies();

}
