package project.goorm.queryserver.business.core.domain.company;

import org.springframework.http.HttpStatus;
import project.goorm.queryserver.common.exception.common.BaseExceptionType;

public enum CompanyTypeException implements BaseExceptionType {

    COMPANY_NOT_FOUND_EXCEPTION(400, "잘못된 회사 정보를 입력했습니다.", HttpStatus.BAD_REQUEST),
    COMPANY_IS_EMPTY_EXCEPTION(404, "회사 목록이 비었습니다.", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus status;

    CompanyTypeException(
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
