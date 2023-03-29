package project.goorm.queryserver.business.web.company.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.goorm.queryserver.business.web.company.application.CompanySearchQuery;
import project.goorm.queryserver.business.web.company.application.service.CompanyQueryService;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;
import project.goorm.queryserver.common.annotation.helper.PresentationLayer;
import project.goorm.queryserver.common.response.ApiResponse;

@PresentationLayer
@RequestMapping(path = "/api/query/companies")
public class CompanyDetailSearchAPI {

    private final CompanySearchQuery companySearchQuery;

    public CompanyDetailSearchAPI(CompanySearchQuery companySearchQuery) {
        this.companySearchQuery = companySearchQuery;
    }

    @GetMapping(path = "/{companyId}")
    public ResponseEntity<ApiResponse> searchCompanyId(@PathVariable Long companyId) {
        CompanyResponse data = companySearchQuery.searchCompanyId(companyId);
        return ResponseEntity.ok()
                .body(ApiResponse.of(data));
    }
}
