FROM openjdk:7-jdk
ENV MYSQL_HOST=localhost
ENV MYSQL_PORT=3306
ENV MYSQL_CHF_DB=thirdapp
ENV MYSQL_USER=szapata
ENV MYSQL_PASS=pass
ENV LOG_LEVEL=DEBUG
RUN addgroup --system spring && adduser --system spring -ingroup spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]