package project.goorm.queryserver.common.response.log;

import project.goorm.queryserver.common.response.ApiResponse;

public class ESLog<T> {

    private ApiResponse<T> response;

    private String UUID;

    private String methodName;

    private Long latency;

    public ESLog(
            ApiResponse<T> response,
            String UUID,
            String methodName,
            Long latency
    ) {
        this.response = response;
        this.UUID = UUID;
        this.methodName = methodName;
        this.latency = latency;
    }

    public ApiResponse<T> getResponse() {
        return response;
    }

    public String getUUID() {
        return UUID;
    }

    public String getMethodName() {
        return methodName;
    }

    public Long getLatency() {
        return latency;
    }

    @Override
    public String toString() {
        return String.format(
                """
                        UUID: %s,
                        Response: %s ,
                        methodName: %s,
                        latency: %s
                        """,
                UUID, response, methodName, latency
        );
    }
}
