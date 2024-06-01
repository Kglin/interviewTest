FROM adoptopenjdk:8-jdk-hotspot

WORKDIR /app

COPY pom.xml /app/pom.xml
COPY src /app/src

RUN mvn clean package

FROM adoptopenjdk:8-jre-hotspot

WORKDIR /app

COPY --from=0 /app/target/main-0.0.1-SNAPSHOT.jar /app/main-0.0.1-SNAPSHOT.jar

ENV JAVA_OPTS="-Xmx512m -Xms512m"

ENV JAVA_OPTS="$JAVA_OPTS -XX:+UseG1GC -XX:MaxGCPauseMillis=200"

RUN java -Xshare:dump -XX:SharedArchiveFile=app-cds.jsa -jar main-0.0.1-SNAPSHOT.jar

ENV JAVA_OPTS="$JAVA_OPTS -Xshare:on -XX:SharedArchiveFile=app-cds.jsa"

EXPOSE 8080

CMD ["java", "-jar", "main-0.0.1-SNAPSHOT.jar"]