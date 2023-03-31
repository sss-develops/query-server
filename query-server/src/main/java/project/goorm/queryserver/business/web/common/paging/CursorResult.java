package project.goorm.queryserver.business.web.common.paging;


import java.util.List;

public class CursorResult<T> {
    private List<T> values;
    private Boolean hasNext;
    private Long lastIndex;

    public CursorResult(List<T> values, Boolean hasNext, Long lastIndex) {
        this.values = values;
        this.hasNext = hasNext == null ? false : hasNext;
        this.lastIndex = lastIndex;
    }

    public List<T> getValues() {
        return values;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public Long getLastIndex() {
        return lastIndex;
    }
}
