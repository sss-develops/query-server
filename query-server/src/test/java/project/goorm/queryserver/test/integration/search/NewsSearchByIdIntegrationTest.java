package project.goorm.queryserver.test.integration.search;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.goorm.queryserver.business.core.domain.news.entity.News;
import project.goorm.queryserver.business.web.news.application.NewsQuery;
import project.goorm.queryserver.business.web.news.presentation.response.NewsResponse;
import project.goorm.queryserver.common.exception.common.SSSTeamException;
import project.goorm.queryserver.test.helper.helper.PersistenceHelper;
import project.goorm.queryserver.test.integration.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static project.goorm.queryserver.business.core.domain.news.NewsTypeException.NEWS_NOT_FOUND_EXCEPTION;
import static project.goorm.queryserver.test.helper.fixture.news.NewsFixture.createNews;

@DisplayName("뉴스 상세조회 통합 테스트")
class NewsSearchByIdIntegrationTest extends IntegrationTestBase {

    @Autowired
    private NewsQuery newsQuery;

    @Autowired
    private PersistenceHelper persistenceHelper;

    @Test
    @DisplayName("뉴스가 존재하면 응답이 Null이 아니다.")
    void when_news_exist_response_should_not_be_null() {
        News news = persistenceHelper.persist(createNews());

        NewsResponse response = newsQuery.findNewsById(news.getNewsId());

        assertNotNull(response.getTitle());
    }

    @Test
    @DisplayName("뉴스가 존재하지 않으면 SSSTeamException이 발생한다.")
    void when_news_not_exist_response_should_be_null() {
        Long invalidNewsId = Long.MAX_VALUE;

        assertThatThrownBy(() -> newsQuery.findNewsById(invalidNewsId))
                .isInstanceOf(SSSTeamException.class)
                .hasMessage(NEWS_NOT_FOUND_EXCEPTION.getMessage());
    }
}
