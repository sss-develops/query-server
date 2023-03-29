package project.goorm.queryserver.business.web.common.paging;

import java.util.Objects;

public class Cursor {

    private static final Long FIRST_PAGE = 0L;
    private static final int MAX_PAGE_SIZE = 10;

    private Long next;
    private int pageSize;

    private Cursor(
            Long next,
            int pageSize
    ) {
        validateCursor(next);
        this.next = getNext(next);
        this.pageSize = getPageSize(pageSize);
    }

    private Long getNext(Long next) {
        return next == null ? FIRST_PAGE : next;
    }

    private int getPageSize(int pageSize) {
        return Math.min(pageSize, MAX_PAGE_SIZE);
    }

    private void validateCursor(Long next) {
        if (next < 0) {
            throw new IllegalArgumentException();
        }
    }

    public static Cursor from(
            Long next,
            int pageSize
    ) {
        return new Cursor(next, pageSize);
    }

    public Long getNext() {
        return next;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cursor cursor)) return false;
        return getPageSize() == cursor.getPageSize() && getNext().equals(cursor.getNext());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNext(), getPageSize());
    }

    @Override
    public String toString() {
        return String.format("Next: %s, Cursor: %s", next, pageSize);
    }
}
