FROM maven:3.8.7-openjdk-18 as builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package  -Pdev -DskipTests

FROM openjdk:18

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

EXPOSE 8080

CMD ["/wait-for-it.sh", "db:3306", "--","java", "-Dspring.profiles.active=dev","-jar", "app.jar"]
