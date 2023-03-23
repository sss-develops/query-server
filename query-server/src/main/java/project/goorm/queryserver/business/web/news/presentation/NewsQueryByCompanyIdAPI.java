package project.goorm.queryserver.business.web.news.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.goorm.queryserver.business.web.news.application.NewsQueryByCompanyService;
import project.goorm.queryserver.business.web.news.presentation.response.NewsResponse;
import project.goorm.queryserver.common.annotation.helper.PresentationLayer;
import project.goorm.queryserver.common.response.ApiResponse;

import java.util.List;

@PresentationLayer
@RequestMapping("/api/news/company/id")
public class NewsQueryByCompanyIdAPI {

    private final NewsQueryByCompanyService newsQueryByCompanyService;

    public NewsQueryByCompanyIdAPI(NewsQueryByCompanyService newsQueryByCompanyService) {
        this.newsQueryByCompanyService = newsQueryByCompanyService;
    }

    @GetMapping("/{companyid}")
    public ResponseEntity<ApiResponse> findNewsByCompanyId(@PathVariable("companyid") Long companyId) {
        List<NewsResponse> data = newsQueryByCompanyService.findNewsByCompanyId(companyId);
        return ResponseEntity.ok()
                .body(ApiResponse.of(data));
    }
}
