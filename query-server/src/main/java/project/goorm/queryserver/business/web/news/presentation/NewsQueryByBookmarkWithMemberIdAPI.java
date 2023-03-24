package project.goorm.queryserver.business.web.news.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.goorm.queryserver.business.web.news.application.NewsQueryByBookmarkService;
import project.goorm.queryserver.business.web.news.presentation.response.NewsResponse;
import project.goorm.queryserver.common.annotation.helper.PresentationLayer;
import project.goorm.queryserver.common.response.ApiResponse;

import java.util.List;

@PresentationLayer
@RequestMapping("/api/news/bookmark/id")
public class NewsQueryByBookmarkWithMemberIdAPI {

    private final NewsQueryByBookmarkService newsQueryByBookmarkService;

    public NewsQueryByBookmarkWithMemberIdAPI(NewsQueryByBookmarkService newsQueryByBookmarkService) {
        this.newsQueryByBookmarkService = newsQueryByBookmarkService;
    }

    @GetMapping("/{member_id}")
    public ResponseEntity<ApiResponse> findNewsByBookmarkWithMemberId(@PathVariable("member_id") Long memberId) {
        List<NewsResponse> data = newsQueryByBookmarkService.findNewsByBookmarkWithMemberId(memberId);
        return ResponseEntity.ok()
                .body(ApiResponse.of(data));
    }
}
