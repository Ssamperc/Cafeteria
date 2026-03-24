FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar","--spring.datasource.url=jdbc:postgresql://db:5432/cafeteria_bd","--spring.datasource.username=postgres","--spring.datasource.password=postgres","--spring.jpa.hibernate.ddl-auto=update"]