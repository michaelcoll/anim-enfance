FROM openjdk:8-jre-alpine
MAINTAINER Michael COLL <mick.coll@gmail.com>

ARG JAR_FILE
ARG SPRING_PROFILE

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT []

CMD java -Xmx32m -Xss256k -Dspring.profiles.active=${SPRING_PROFILE} -jar app.jar
