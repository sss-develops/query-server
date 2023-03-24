package project.goorm.queryserver.business.web.company.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.goorm.queryserver.business.web.company.application.CompanyQueryService;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;
import project.goorm.queryserver.common.annotation.helper.PresentationLayer;
import project.goorm.queryserver.common.response.ApiResponse;

import javax.websocket.server.PathParam;

@PresentationLayer
@RequestMapping("/api/company/id")
public class CompanyQueryByIdAPI {

    private final CompanyQueryService companyQueryService;

    public CompanyQueryByIdAPI(CompanyQueryService companyQueryService) {
        this.companyQueryService = companyQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findCompanyById(@PathVariable("id") Long companyId) {
        CompanyResponse data = companyQueryService.findCompanyById(companyId);
        return ResponseEntity.ok()
                .body(ApiResponse.of(data));
    }
}
