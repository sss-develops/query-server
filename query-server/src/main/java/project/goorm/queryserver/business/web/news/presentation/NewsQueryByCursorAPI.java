package project.goorm.queryserver.business.web.news.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.goorm.queryserver.business.core.domain.news.entity.News;
import project.goorm.queryserver.business.web.common.paging.Cursor;
import project.goorm.queryserver.business.web.common.paging.CursorResult;
import project.goorm.queryserver.business.web.news.application.service.paging.NewsCursorSearchService;
import project.goorm.queryserver.common.annotation.helper.PresentationLayer;
import project.goorm.queryserver.common.response.ApiResponse;

@PresentationLayer
@RequestMapping(path = "/api/query/news")
public class NewsQueryByCursorAPI {

    private final NewsCursorSearchService newsCursorSearchService;

    public NewsQueryByCursorAPI(NewsCursorSearchService newsCursorSearchService) {
        this.newsCursorSearchService = newsCursorSearchService;
    }

    @GetMapping("/cursor")
    public ResponseEntity<ApiResponse> findByCursor(@RequestParam Long next, @RequestParam Integer size) {
        CursorResult<News> newsCursorResult = newsCursorSearchService.findByCursorPaging(Cursor.from(next, size));
        return ResponseEntity.ok(ApiResponse.of(newsCursorResult));
    }
}
