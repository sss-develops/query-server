package project.goorm.queryserver.business.web.news.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goorm.queryserver.business.core.domain.news.entity.News;
import project.goorm.queryserver.business.core.domain.news.infrastructure.query.NewsQueryRepository;
import project.goorm.queryserver.business.web.news.presentation.response.NewsResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsQueryByCompanyService {

    private final NewsQueryRepository newsQueryRepository;

    public NewsQueryByCompanyService(NewsQueryRepository newsQueryRepository) {
        this.newsQueryRepository = newsQueryRepository;
    }

    @Transactional(readOnly = true)
    public List<NewsResponse> findNewsByCompanyId(Long companyId) {
        List<News> findNews = newsQueryRepository.findNewsByCompanyId(companyId);
        return findNews.stream()
                .map(NewsResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NewsResponse> findNewsByCompanyName(String companyName) {
        List<News> findNews = newsQueryRepository.findNewsByCompanyName(companyName);
        return findNews.stream()
                .map(NewsResponse::of)
                .collect(Collectors.toList());
    }
}
