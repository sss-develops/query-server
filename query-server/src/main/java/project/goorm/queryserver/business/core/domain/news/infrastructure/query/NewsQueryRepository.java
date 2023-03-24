package project.goorm.queryserver.business.core.domain.news.infrastructure.query;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import project.goorm.queryserver.business.core.domain.common.deleted.Deleted;
import project.goorm.queryserver.business.core.domain.news.entity.News;

import java.util.List;
import java.util.Optional;

import static project.goorm.queryserver.business.core.domain.bookmark.entity.QBookmark.bookmark;
import static project.goorm.queryserver.business.core.domain.company.entity.QCompany.company;
import static project.goorm.queryserver.business.core.domain.news.entity.QNews.news;

@Repository
public class NewsQueryRepository {

    private final JPAQueryFactory queryFactory;

    public NewsQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<News> findNewsByCompanyId(Long companyId) {
        return queryFactory.selectFrom(news)
                .where(news.companyId.eq(companyId)
                .and(news.deleted.eq(Deleted.FALSE)))
                .fetch();
    }

    public List<News> findNewsByCompanyName(String companyName) {
        return queryFactory.selectFrom(news)
                .join(company)
                .on(news.companyId.eq(company.companyId))
                .where(company.companyName.eq(companyName).and(news.deleted.eq(Deleted.FALSE)))
                .fetch();
    }

    public List<News> findNewsByBookmarkWithMemberId(Long memberId) {
        return queryFactory.selectFrom(news)
                .where(news.newsId.in(JPAExpressions.select(bookmark.newsId)
                                .from(bookmark)
                                .where(bookmark.memberId.eq(memberId)))
                        .and(news.deleted.eq(Deleted.FALSE)))
                .fetch();
    }
}
