package project.goorm.queryserver.business.core.domain.news;

import org.springframework.http.HttpStatus;
import project.goorm.queryserver.common.exception.common.BaseExceptionType;

public enum NewsTypeException implements BaseExceptionType {

    INVALID_NEWS_ID_EXCEPTION(400, "올바르지 않은 뉴스 아이디 입니다.", HttpStatus.BAD_REQUEST),
    NEWS_NOT_FOUND_EXCEPTION(404, "해당 뉴스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NEWS_IS_EMPTY_EXCEPTION(404, "해당 회사의 뉴스 목록이 비었습니다.", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus status;

    NewsTypeException(
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
