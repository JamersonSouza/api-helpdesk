FROM maven:3.8.6-amazoncorretto-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -X -DskipTests

FROM openjdk:11-jdk-slim-sid
WORKDIR /app
ENV JAVA_TOOL_OPTIONS="-Xmx400m"
COPY --from=build ./app/target/*.jar ./helpdesk.jar
ENTRYPOINT ["java", "-jar", "helpdesk.jar"]
