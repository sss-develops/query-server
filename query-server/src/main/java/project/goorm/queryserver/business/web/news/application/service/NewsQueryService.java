package project.goorm.queryserver.business.web.news.application.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goorm.queryserver.business.core.domain.news.entity.News;
import project.goorm.queryserver.business.core.domain.news.infrastructure.query.NewsQueryRepository;
import project.goorm.queryserver.business.web.news.application.NewsQuery;
import project.goorm.queryserver.business.web.news.presentation.response.NewsResponse;
import project.goorm.queryserver.common.exception.common.SSSTeamException;

import static project.goorm.queryserver.business.core.domain.news.NewsTypeException.NEWS_NOT_FOUND_EXCEPTION;
import static project.goorm.queryserver.common.configuration.redis.RedisKey.NEWS_CACHE_CONDITION;
import static project.goorm.queryserver.common.configuration.redis.RedisKey.NEWS_CACHE_KEY;
import static project.goorm.queryserver.common.configuration.redis.RedisKey.NEWS_CACHE_VALUE;

@Service
public class NewsQueryService implements NewsQuery {

    private final NewsQueryRepository newsQueryRepository;

    public NewsQueryService(NewsQueryRepository newsQueryRepository) {
        this.newsQueryRepository = newsQueryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            key = NEWS_CACHE_KEY,
            value = NEWS_CACHE_VALUE,
            condition = NEWS_CACHE_CONDITION
    )
    public NewsResponse findNewsById(Long newsId) {
        News news = newsQueryRepository.findNewsById(newsId)
                .orElseThrow(() -> SSSTeamException.of(NEWS_NOT_FOUND_EXCEPTION));
        return NewsResponse.of(news);
    }
}
