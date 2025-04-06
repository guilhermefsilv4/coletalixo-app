FROM amazoncorretto:21

VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE=target/coletalixo-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]