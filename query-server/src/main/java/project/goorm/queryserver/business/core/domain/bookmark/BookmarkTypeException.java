package project.goorm.queryserver.business.core.domain.bookmark;

import org.springframework.http.HttpStatus;
import project.goorm.queryserver.common.exception.common.BaseExceptionType;

public enum BookmarkTypeException implements BaseExceptionType {

    BOOKMARK_IS_EMPTY_EXCEPTION(404, "북마크 목록이 비었습니다.", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus status;

    BookmarkTypeException(
            int code,
            String message,
            HttpStatus status
    ) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
