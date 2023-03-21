package project.goorm.queryserver.business.core.company;

import org.springframework.http.HttpStatus;
import project.goorm.queryserver.common.exception.common.BaseExceptionType;

public enum CompanyTypeException implements BaseExceptionType {

    COMPANY_NOT_FOUND_EXCEPTION(404, "회사를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

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
