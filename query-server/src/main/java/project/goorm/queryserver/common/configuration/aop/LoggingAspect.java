package project.goorm.queryserver.common.configuration.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import project.goorm.queryserver.common.response.ApiResponse;
import project.goorm.queryserver.common.response.CommonInformationResponse;
import project.goorm.queryserver.common.response.log.ESLog;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Aspect
@Component
@Slf4j
public class LoggingAspect<T> {

    private final KafkaTemplate<String, ESLog> kafkaTemplate;

    public LoggingAspect(KafkaTemplate<String, ESLog> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static String TOPIC_NAME = "query-server-log";
    private static String TRACING_ID = "trace.id";

    @Around("within(project.goorm.queryserver.business.web.news.presentation.*)")
    public Object writeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object[] arguments = joinPoint.getArgs();
            String methodName = joinPoint.getSignature().getName();
            HttpServletRequest httpServletRequest = extractHttpServletRequest();
            MDC.put(TRACING_ID, UUID.randomUUID().toString());

            if (arguments != null) {
                log.info("ID: [{}], Request: [{}]", MDC.get(TRACING_ID), arguments);
            }

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Object result = joinPoint.proceed();
            stopWatch.stop();
            long totalTimeMillis = stopWatch.getTotalTimeMillis();
            ApiResponse<T> apiResponse = cast(result);
            if (apiResponse != null) {
                registerCommonHeader(apiResponse, httpServletRequest);
                log.info("ID: [{}], Response: [{}]", MDC.get(TRACING_ID), apiResponse);
                kafkaTemplate.send(TOPIC_NAME, new ESLog(apiResponse, MDC.get(TRACING_ID), methodName, totalTimeMillis));
            }
            return result;
        } finally {
            MDC.clear();
        }
    }

    private HttpServletRequest extractHttpServletRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    private void registerCommonHeader(
            ApiResponse<T> apiResponse,
            HttpServletRequest httpServletRequest
    ) {
        apiResponse.setCommonResponse(createCommonInformation(httpServletRequest));
    }

    private CommonInformationResponse createCommonInformation(HttpServletRequest httpServletRequest) {
        return new CommonInformationResponse(httpServletRequest);
    }

    private ApiResponse<T> cast(Object response) {
        if (response instanceof ResponseEntity<?>) {
            @SuppressWarnings(value = "unchecked")
            ResponseEntity<ApiResponse> castedResposne = (ResponseEntity<ApiResponse>) response;
            return castedResposne.getBody();
        }
        throw new IllegalArgumentException("잘못된 응답 데이터 형식입니다.");
    }

}
