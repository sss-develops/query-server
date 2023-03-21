package project.goorm.queryserver.common.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.goorm.queryserver.common.exception.common.ErrorResponse;
import project.goorm.queryserver.common.exception.common.SSSTeamException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse catchIllegalArgumentException(IllegalArgumentException exception) {
        return ErrorResponse.of(BAD_REQUEST, exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SSSTeamException.class)
    public ErrorResponse catchSSSTeamException(SSSTeamException exception) {
        return ErrorResponse.of(exception);
    }
}
