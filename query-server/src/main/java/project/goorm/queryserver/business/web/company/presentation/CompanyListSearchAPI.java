package project.goorm.queryserver.business.web.company.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.goorm.queryserver.business.web.common.paging.Cursor;
import project.goorm.queryserver.business.web.common.paging.CursorPageable;
import project.goorm.queryserver.business.web.company.application.CompanySearchQuery;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;
import project.goorm.queryserver.common.annotation.helper.PresentationLayer;
import project.goorm.queryserver.common.response.ApiResponse;

import java.util.List;

@PresentationLayer
@RequestMapping(path = "/api/query/companies/list")
public class CompanyListSearchAPI {

    private final CompanySearchQuery companySearchQuery;

    public CompanyListSearchAPI(CompanySearchQuery companySearchQuery) {
        this.companySearchQuery = companySearchQuery;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> searchCompaniesByName(
            @CursorPageable Cursor cursor,
            @RequestParam("companyName") String companyName
    ) {
        List<CompanyResponse> data = companySearchQuery.searchCompaniesByName(
                cursor,
                companyName
        );
        return ResponseEntity.ok()
                .body(ApiResponse.of(data));
    }
}
