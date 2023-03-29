package project.goorm.queryserver.business.web.company.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.goorm.queryserver.business.web.company.application.CompanySearchQuery;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;
import project.goorm.queryserver.common.annotation.helper.PresentationLayer;
import project.goorm.queryserver.common.response.ApiResponse;

import java.util.List;

@PresentationLayer
@RequestMapping(path = "/api/query/companies")
public class TopSearchedCompaniesAPI {

    private final CompanySearchQuery companySearchQuery;

    public TopSearchedCompaniesAPI(CompanySearchQuery companySearchQuery) {
        this.companySearchQuery = companySearchQuery;
    }

    @GetMapping(path = "/top-searched")
    public ResponseEntity<ApiResponse> getTopSearchedCompanies() {
        List<CompanyResponse> data = companySearchQuery.getTopSearchedCompanies();
        return ResponseEntity.ok()
                .body(ApiResponse.of(data));
    }
}
