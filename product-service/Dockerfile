FROM  maven:3.9.3-eclipse-temurin-17-alpine AS builder
WORKDIR build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17.0.4.1_1-jre AS layers
WORKDIR layers
COPY --from=builder build/target/*.jar app.jar
RUN java -Djarmode=tools -jar app.jar extract --layers --launcher

FROM eclipse-temurin:17.0.4.1_1-jre
WORKDIR application
COPY --from=layers layers/app/dependencies/ ./
COPY --from=layers layers/app/spring-boot-loader/ ./
COPY --from=layers layers/app/snapshot-dependencies/ ./
COPY --from=layers layers/app/application/ ./
EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]