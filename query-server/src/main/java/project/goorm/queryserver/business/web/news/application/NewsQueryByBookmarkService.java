package project.goorm.queryserver.business.web.news.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goorm.queryserver.business.core.domain.news.entity.News;
import project.goorm.queryserver.business.core.domain.news.infrastructure.query.NewsQueryRepository;
import project.goorm.queryserver.business.web.news.presentation.response.NewsResponse;
import project.goorm.queryserver.common.exception.common.SSSTeamException;

import java.util.List;
import java.util.stream.Collectors;

import static project.goorm.queryserver.business.core.domain.news.NewsTypeException.NEWS_IS_EMPTY_EXCEPTION;

@Service
public class NewsQueryByBookmarkService {

    private final NewsQueryRepository newsQueryRepository;

    public NewsQueryByBookmarkService(NewsQueryRepository newsQueryRepository) {
        this.newsQueryRepository = newsQueryRepository;
    }

    @Transactional(readOnly = true)
    public List<NewsResponse> findNewsByBookmarkWithMemberId(Long memberId) {
        List<News> findNews = newsQueryRepository.findNewsByBookmarkWithMemberId(memberId);
        List<NewsResponse> newsResponseList = findNews.stream()
                .map(n -> new NewsResponse(n))
                .collect(Collectors.toList());
        return newsResponseList;
    }
}
