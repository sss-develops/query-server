package project.goorm.queryserver.test.integration.news.paging;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.goorm.queryserver.business.core.domain.news.entity.News;
import project.goorm.queryserver.business.web.common.paging.Cursor;
import project.goorm.queryserver.business.web.common.paging.CursorResult;
import project.goorm.queryserver.business.web.news.application.service.paging.NewsCursorSearchService;
import project.goorm.queryserver.common.configuration.rdb.AbstractContainerTestBase;
import project.goorm.queryserver.test.helper.fixture.news.NewsFixture;
import project.goorm.queryserver.test.helper.helper.PersistenceHelper;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("News커서 기반 조회 테스트")
public class NewsCursorPagingServiceTest extends AbstractContainerTestBase {

    @Autowired
    private PersistenceHelper persistenceHelper;

    @Autowired
    private NewsCursorSearchService newsCursorPagingService;


    @Test
    @DisplayName("2개의 뉴스가 저장되 있을 떄 커서 기반 조회를 하면 2개의 결과가 나온다.")
    public void given_news_when_find_by_cursor_then_return_news_list() {
        // given - precondition or setup
        persistenceHelper.persist(NewsFixture.createNews());
        persistenceHelper.persist(NewsFixture.createNews());

        // when - action or the behaviour that we are going test
        CursorResult<News> byCursorPaging = newsCursorPagingService.findByCursorPaging(Cursor.from(5));

        // then - verify the output
        assertThat(byCursorPaging.getValues().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("클라이언트가 사용할 다음 인덱스를 제대로 반환하는지 테스트한다.")
    public void given_new_list_when_find_by_cursor_then_return_valid_next_index() {
        // given - precondition or setup
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newsList.add(NewsFixture.createNews());
        }
        persistenceHelper.persist(newsList);

        // when - action or the behaviour that we are going test
        CursorResult<News> byCursorPaging =
                newsCursorPagingService.findByCursorPaging(Cursor.from(5));

        CursorResult<News> byCursorPaging2 =
                newsCursorPagingService.findByCursorPaging(Cursor.from(byCursorPaging.getLastIndex(), 5));


        // then - verify the output
        assertThat(byCursorPaging2.getLastIndex()).isEqualTo(1);
    }

    @Test
    @DisplayName("다음 페이지가 없는 경우를 테스트한다.")
    public void given_new_list_when_find_by_cursor_then_return_has_next_false() {
        // given - precondition or setup
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newsList.add(NewsFixture.createNews());
        }

        // when - action or the behaviour that we are going test
        CursorResult<News> byCursorPaging = newsCursorPagingService.findByCursorPaging(Cursor.from(1L, 5));

        // then - verify the output
        assertThat(byCursorPaging.getHasNext()).isEqualTo(false);
    }

    @Test
    @DisplayName("다음 Id값이 minus가 들어오면 IllegalArgumentException예외를 던진다.")
    public void when_find_by_minus_id_then_throw_exception() {
        // then - verify the output
        assertThatThrownBy(
                () -> newsCursorPagingService.findByCursorPaging(Cursor.from(-1L, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
