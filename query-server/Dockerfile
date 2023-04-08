FROM openjdk:17.0-slim
WORKDIR /app

COPY build/libs/query-server-0.0.1-SNAPSHOT.jar .

EXPOSE 8071
ENTRYPOINT [ "java", "-jar", "query-server-0.0.1-SNAPSHOT.jar" ] 