package project.goorm.queryserver.business.web.news.presentation.response;

import project.goorm.queryserver.business.core.domain.news.entity.News;

import java.io.Serializable;
import java.time.Instant;

public class NewsResponse implements Serializable {

    private String title;
    private String description;
    private String originalLink;
    private String naverLink;
    private Instant publishedAt;

    private NewsResponse(News news) {
        this.title = news.getTitle();
        this.description = news.getDescription();
        this.originalLink = news.getOriginalLink();
        this.naverLink = news.getNaverLink();
        this.publishedAt = news.getPublishedAt();
    }

    public static NewsResponse of(News news) {
        return new NewsResponse(news);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public String getNaverLink() {
        return naverLink;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, PubDate: %s, OriginalLink: %s", title, publishedAt, originalLink);
    }
}
