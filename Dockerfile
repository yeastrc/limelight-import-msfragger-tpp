FROM amazoncorretto:11-alpine-jdk

COPY build/libs/msFraggerTPP2LimelightXML.jar  /usr/local/bin/msFraggerTPP2LimelightXML.jar

ENTRYPOINT ["java", "-jar", "/usr/local/bin/msFraggerTPP2LimelightXML.jar"]