# --- Build stage ---
FROM maven:3.9.6-eclipse-temurin-22 AS build

WORKDIR /app

# Кешування залежностей
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Додаємо решту коду
COPY src ./src

# Збірка jar
RUN mvn clean package -DskipTests

# --- Runtime stage ---
FROM eclipse-temurin:22-jre

WORKDIR /app

COPY --from=build /app/target/*.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java --enable-preview $JAVA_OPTS -jar app.jar"]
