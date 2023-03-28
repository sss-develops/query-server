package project.goorm.queryserver.business.web.news.application;

import project.goorm.queryserver.business.web.news.presentation.response.NewsResponse;
import project.goorm.queryserver.common.annotation.helper.ApplicationLayer;

@ApplicationLayer
public interface NewsQuery {

    NewsResponse findNewsById(Long newsId);

}
