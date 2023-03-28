package project.goorm.queryserver.business.web.news.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.goorm.queryserver.business.web.news.application.service.NewsQueryByCompanyService;
import project.goorm.queryserver.business.web.news.presentation.response.NewsResponse;
import project.goorm.queryserver.common.annotation.helper.PresentationLayer;
import project.goorm.queryserver.common.response.ApiResponse;

import java.util.List;

@PresentationLayer
@RequestMapping(path = "/api/news/company/name")
public class NewsQueryByCompanyNameAPI {

    private final NewsQueryByCompanyService newsQueryByCompanyService;

    public NewsQueryByCompanyNameAPI(NewsQueryByCompanyService newsQueryByCompanyService) {
        this.newsQueryByCompanyService = newsQueryByCompanyService;
    }

    @GetMapping(path = "/{companyName}")
    public ResponseEntity<ApiResponse> findNewsByCompanyName(@PathVariable("companyName") String companyName) {
        List<NewsResponse> data = newsQueryByCompanyService.findNewsByCompanyName(companyName);
        return ResponseEntity.ok()
                .body(ApiResponse.of(data));
    }
}
