package project.goorm.queryserver.business.core.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Objects;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column
    private String title;

    @Column
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

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 news 외부 패키지에서 호출하지 말 것.
     */
    protected News() {
    }

    public Long getNewsId() {
        return newsId;
    }

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
