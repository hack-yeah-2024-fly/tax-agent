FROM maven:3.8.3-openjdk-17 AS java-app

COPY ./pom.xml ./pom.xml
COPY ./src ./src
COPY ./lib/ojdbc6.jar ./ojdbc6.jar

RUN mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar
RUN mvn clean install

ENTRYPOINT ["java", "-jar", "./target/springboot-tax-application-0.0.1-SNAPSHOT.jar"]