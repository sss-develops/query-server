package project.goorm.queryserver.business.core.domain.bookmark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;

    @Column
    private Long newsId;

    @Column
    private Long memberId;

    @Column
    private Instant createdAt;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 bookmark 외부 패키지에서 호출하지 말 것.
     */
    protected Bookmark() {
    }

    public Long getBookmarkId() {
        return bookmarkId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bookmark bookmark)) return false;
        return getBookmarkId().equals(bookmark.getBookmarkId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookmarkId());
    }

    @Override
    public String toString() {
        return bookmarkId.toString();
    }
}
