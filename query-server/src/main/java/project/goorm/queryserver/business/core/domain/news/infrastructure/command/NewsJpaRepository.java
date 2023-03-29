package project.goorm.queryserver.business.core.domain.news.infrastructure.command;

import org.springframework.data.jpa.repository.JpaRepository;
import project.goorm.queryserver.business.core.domain.news.entity.News;

public interface NewsJpaRepository extends JpaRepository<News, Long> {
}
