package project.goorm.queryserver.business.web.news.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.goorm.queryserver.business.web.news.application.NewsQuery;
import project.goorm.queryserver.business.web.news.presentation.response.NewsResponse;
import project.goorm.queryserver.common.annotation.helper.PresentationLayer;
import project.goorm.queryserver.common.response.ApiResponse;

@PresentationLayer
@RequestMapping(path = "/api/query/news")
public class NewsQueryAPI {

    private final NewsQuery newsQuery;

    public NewsQueryAPI(NewsQuery newsQuery) {
        this.newsQuery = newsQuery;
    }

    @GetMapping(path = "/{newsId}")
    public ResponseEntity<ApiResponse> findNewsById(@PathVariable Long newsId) {
        NewsResponse data = newsQuery.findNewsById(newsId);
        return ResponseEntity
                .ok(ApiResponse.of(data));
    }
}
