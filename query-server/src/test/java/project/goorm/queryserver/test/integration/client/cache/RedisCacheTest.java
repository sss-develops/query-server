package project.goorm.queryserver.test.integration.client.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.redis.core.RedisTemplate;
import project.goorm.queryserver.business.core.domain.news.entity.News;
import project.goorm.queryserver.business.core.domain.news.infrastructure.query.NewsQueryRepository;
import project.goorm.queryserver.business.web.news.application.NewsQuery;
import project.goorm.queryserver.business.web.news.application.service.NewsQueryService;
import project.goorm.queryserver.business.web.news.presentation.response.NewsResponse;
import project.goorm.queryserver.common.configuration.rdb.DatabaseTestBase;
import project.goorm.queryserver.test.helper.helper.PersistenceHelper;
import project.goorm.queryserver.test.integration.IntegrationTestBase;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static project.goorm.queryserver.test.helper.fixture.news.NewsFixture.createNews;

@DisplayName("레디스 캐시 테스트")
class RedisCacheTest extends DatabaseTestBase {

    private NewsQuery newsQuery;

    @SpyBean
    private NewsQueryRepository newsQueryRepository;

    @Autowired
    private PersistenceHelper persistenceHelper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @BeforeEach
    void setUP() {
        newsQuery = new NewsQueryService(newsQueryRepository);
    }

    @Test
    @DisplayName("레디스 캐시가 존재하지 않을 때 RDB를 호출한다.")
    void when_redis_cache_not_exist_then_rdb_should_be_called() {
        redisTemplate.delete("news::cache::1");
        News news = persistenceHelper.persist(createNews());
        Optional<News> expectedResult = Optional.ofNullable(news);

        doReturn(expectedResult)
                .when(newsQueryRepository).findNewsById(news.getNewsId());

        newsQuery.findNewsById(news.getNewsId());

        verify(newsQueryRepository, times(1)).findNewsById(news.getNewsId());
    }

    @Test
    @DisplayName("레디스 캐시가 존재할 때 RDB를 호출하지 않는다.")
    void when_redis_cache_exist_then_rdb_should_not_be_called() throws JsonProcessingException {
        News news = persistenceHelper.persist(createNews());
        NewsResponse cache = NewsResponse.of(news);
        objectMapper.registerModule(new JavaTimeModule());
        redisTemplate.opsForValue().set("news::cache::1", objectMapper.writeValueAsString(cache));

        Optional<News> expectedResult = Optional.ofNullable(news);

        doReturn(expectedResult)
                .when(newsQueryRepository).findNewsById(news.getNewsId());

        verify(newsQueryRepository, times(0)).findNewsById(news.getNewsId());
    }
}
