# Build stage
FROM maven:3.9.8-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY .mvn .mvn
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn clean install -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine AS runner
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser
WORKDIR /app
COPY --from=builder /app/target/RecrutaiBackEnd-*.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
