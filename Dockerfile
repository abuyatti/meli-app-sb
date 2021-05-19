FROM openjdk:8-jdk-alpine

ENV WORKDIR /deployment/
ENV APP_JAR app.jar
ENV JAVA_OPTS ""

WORKDIR $WORKDIR
COPY target/*.jar $APP_JAR

RUN apk add curl

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar $APP_JAR"]
