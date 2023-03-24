package project.goorm.queryserver.business.web.company.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.goorm.queryserver.business.core.domain.company.entity.Company;
import project.goorm.queryserver.business.web.company.application.CompanyQueryService;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;
import project.goorm.queryserver.common.annotation.helper.PresentationLayer;
import project.goorm.queryserver.common.response.ApiResponse;

@PresentationLayer
@RequestMapping("/api/company/name")
public class CompanyQueryByNameAPI {

    private final CompanyQueryService companyQueryService;

    public CompanyQueryByNameAPI(CompanyQueryService companyQueryService) {
        this.companyQueryService = companyQueryService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse> findCompanyByName(@PathVariable("name") String companyName) {
        CompanyResponse data = companyQueryService.findCompanyByName(companyName);
        return ResponseEntity.ok()
                .body(ApiResponse.of(data));
    }
}
