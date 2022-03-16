NAME cds

FROM gradle:7.4.1-jdk17

RUN mkdir /ss_tech
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
EXPOSE 3030

RUN cd ss_tech && java -jar cds.jar -Dspring.profiles.active=dev -Dserver.port=3030

#ENTRYPOINT ["java","-jar","/ss_tech/"]

# docker run -d --restart unless-stopped -p ®:3000 --name ss-dev-mongo mongo:latest