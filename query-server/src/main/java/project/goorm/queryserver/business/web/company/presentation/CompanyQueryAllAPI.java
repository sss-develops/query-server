package project.goorm.queryserver.business.web.company.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.goorm.queryserver.business.core.domain.company.entity.Company;
import project.goorm.queryserver.business.web.company.application.CompanyQueryService;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;
import project.goorm.queryserver.common.annotation.helper.PresentationLayer;
import project.goorm.queryserver.common.response.ApiResponse;

import java.util.List;

@PresentationLayer
@RequestMapping("/api/company/all")
public class CompanyQueryAllAPI {

    private final CompanyQueryService companyQueryService;

    public CompanyQueryAllAPI(CompanyQueryService companyQueryService) {
        this.companyQueryService = companyQueryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findCompanyAll() {
        List<CompanyResponse> data = companyQueryService.findCompanyAll();
        return ResponseEntity.ok()
                .body(ApiResponse.of(data));
    }
}
