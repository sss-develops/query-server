package project.goorm.queryserver.test.helper.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.goorm.queryserver.business.core.domain.company.entity.Company;
import project.goorm.queryserver.business.core.domain.company.infrastructure.command.CompanyJpaRepository;
import project.goorm.queryserver.business.core.domain.news.entity.News;
import project.goorm.queryserver.business.core.domain.news.infrastructure.command.NewsJpaRepository;

import java.util.List;

@Component
public class PersistenceHelper {

    @Autowired
    private NewsJpaRepository newsJpaRepository;

    @Autowired
    private CompanyJpaRepository companyJpaRepository;

    public News persist(News news) {
        return newsJpaRepository.save(news);
    }

    public Company persist(Company company) {
        return companyJpaRepository.save(company);
    }

    public News findNewsById(Long newsId) {
        return newsJpaRepository.findById(newsId).orElseThrow();
    }

    public List<News> persist(List<News> newsList) {
        return newsJpaRepository.saveAll(newsList);
    }
}
