FROM gradle:jre14  AS builder
LABEL maintainer="Oleh Kozlovskyi <xeenhl@gmail.com>"

WORKDIR /bot/src
COPY . .

RUN ls -la
RUN ./gradlew assemble


FROM openjdk:17-jdk-alpine

COPY --from=builder /bot/src/build/libs/*.jar /app.jar
COPY --from=builder /bot/src/build/resources /resources

ENTRYPOINT ["java","-jar","/app.jar"]