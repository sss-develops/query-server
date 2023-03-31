package project.goorm.queryserver.business.web.news.application.service.paging;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goorm.queryserver.business.core.domain.news.entity.News;
import project.goorm.queryserver.business.core.domain.news.infrastructure.query.NewsQueryRepository;
import project.goorm.queryserver.business.web.common.paging.Cursor;
import project.goorm.queryserver.business.web.common.paging.CursorResult;

import java.util.List;

@Service
public class NewsCursorPagingService {

    private static final Long DEFAULT_PAGE_SIZE = 10L;

    private NewsQueryRepository newsQueryRepository;

    public NewsCursorPagingService(NewsQueryRepository newsQueryRepository) {
        this.newsQueryRepository = newsQueryRepository;
    }

    @Transactional(readOnly = true)
    public CursorResult<News> findByCursorPaging(Cursor cursor) {
        List<News> newsList = getNewsList(cursor.getNext(), (long) cursor.getPageSize());
        Long lastOfList = newsList.isEmpty() ?
                0 :
                newsList.get(newsList.size() - 1).getNewsId();

        Long nextSearchIndex = lastOfList - 1;

        Boolean hasNext = hasNext(lastOfList);

        return new CursorResult<>(newsList, hasNext, nextSearchIndex);
    }

    private List<News> getNewsList(
            Long cursorId,
            Long size
    ) {
        size = size == null ? DEFAULT_PAGE_SIZE : size;

        return cursorId == null ?
                newsQueryRepository.findAllByOrder(size) :
                newsQueryRepository.findAllByIdLessThanOrderByIdDesc(cursorId, size);
    }

    private Boolean hasNext(Long id) {
        if (id == null) return false;
        return newsQueryRepository.existByIdLessThan(id);
    }
}
