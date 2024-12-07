FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Création de l'image finale
FROM amazoncorretto:17
ARG APP_VERSION=1.0.0
WORKDIR /app
COPY  --from=build /build/target/Loisir-*.jar /app


# Configuration de l'application
EXPOSE 8094
ENV DB_URL=jdbc:mysql://mysqldb:3306/ihm

ENV JAR_VERSION=${APP_VERSION}
CMD java -jar -Dspring.datasource.url=${DB_URL} Loisir-${JAR_VERSION}.jar




