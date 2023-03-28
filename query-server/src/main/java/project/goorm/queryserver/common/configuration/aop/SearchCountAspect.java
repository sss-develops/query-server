package project.goorm.queryserver.common.configuration.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import project.goorm.queryserver.business.web.client.redis.RedisCountCommand;
import project.goorm.queryserver.common.exception.common.SSSTeamException;

import javax.servlet.http.HttpServletRequest;

import static project.goorm.queryserver.business.core.domain.news.NewsTypeException.INVALID_NEWS_ID_EXCEPTION;

@Slf4j
@Aspect
@Component
public class SearchCountAspect {

    private final RedisCountCommand redisCountCommand;

    public SearchCountAspect(RedisCountCommand redisCountCommand) {
        this.redisCountCommand = redisCountCommand;
    }

    @Pointcut("execution(* project.goorm.queryserver.business.web.news.presentation.NewsQueryAPI.findNewsById(..))")
    private void countCompanyId() {
    }

    @Before("countCompanyId()")
    public void countStatistic(JoinPoint joinPoint) {
        Long companyId = extractPathVariable(extractHttpServletRequest());
        redisCountCommand.increaseCompanyIdCount(companyId);
    }

    private HttpServletRequest extractHttpServletRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    private Long extractPathVariable(HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] temp;
        try {
            temp = requestURI.split("/");
            return Long.parseLong(temp[temp.length - 1]);
        } catch (NumberFormatException e) {
            throw SSSTeamException.of(INVALID_NEWS_ID_EXCEPTION);
        }
    }
}
