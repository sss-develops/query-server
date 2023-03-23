package project.goorm.queryserver.business.core.domain.news.entity;

import project.goorm.queryserver.business.core.domain.common.deleted.Deleted;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(name = "newsTitle")
    private String title;

    @Column(name = "newsDescription")
    private String description;

    @Column
    private Long companyId;

    @Column
    private String originalLink;

    @Column
    private String naverLink;

    @Column
    private Instant publishedAt;

    @Column
    private Instant createdAt;

    @Column
    private Instant lastModifiedAt;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')")
    private Deleted deleted;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 news 외부 패키지에서 호출하지 말 것.
     */
    protected News() {
    }

    public Long getNewsId() {
        return newsId;
    }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public Long getCompanyId() { return companyId; }

    public String getOriginalLink() { return originalLink; }

    public String getNaverLink() { return naverLink; }
    public Instant getPublishedAt() { return publishedAt; }

    public Instant getCreatedAt() { return createdAt; }

    public Instant getLastModifiedAt() { return lastModifiedAt; }

    public Deleted getDeleted() { return deleted; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News news)) return false;
        return getNewsId().equals(news.getNewsId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNewsId());
    }

    @Override
    public String toString() {
        return newsId.toString();
    }
}
