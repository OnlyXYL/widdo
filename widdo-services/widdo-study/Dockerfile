#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ADD widdo-study-302.1.0.0.jar app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
FROM openjdk:8-jdk-alpine
LABEL "author"="only_xyl@163.com"
VOLUME /widdo
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]