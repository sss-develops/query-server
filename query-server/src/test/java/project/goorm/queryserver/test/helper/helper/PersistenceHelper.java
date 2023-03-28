package project.goorm.queryserver.test.helper.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.goorm.queryserver.business.core.domain.news.entity.News;
import project.goorm.queryserver.business.core.domain.news.infrastructure.command.NewsJpaRepository;

@Component
public class PersistenceHelper {

    @Autowired
    private NewsJpaRepository newsJpaRepository;

    public News persist(News news) {
        return newsJpaRepository.save(news);
    }

    public News findNewsById(Long newsId) {
        return newsJpaRepository.findById(newsId).orElseThrow();
    }
}
