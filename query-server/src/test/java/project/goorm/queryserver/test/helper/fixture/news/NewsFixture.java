package project.goorm.queryserver.test.helper.fixture.news;

import project.goorm.queryserver.business.core.domain.news.entity.News;

import java.time.Instant;

public class NewsFixture {

    public static News createNews() {
        return new News(
                "Bang",
                "Bang",
                1L,
                "http://file3.instiz.net/data/file3/2018/03/15/0/5/7/0578a74a9c26a6240c540324fb301bd3.jpg",
                "http://file3.instiz.net/data/file3/2018/03/15/0/5/7/0578a74a9c26a6240c540324fb301bd3.jpg",
                Instant.now(),
                Instant.now()
        );
    }
}
