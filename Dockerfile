# using multistage docker build
# ref: https://docs.docker.com/develop/develop-images/multistage-build/

# temp container to build using gradle
FROM gradle:7.4.2 AS TEMP_BUILD_IMAGE
RUN ls
ENV APP_HOME=/usr/app/
RUN ls

WORKDIR $APP_HOME
RUN ls

COPY build.gradle.kts settings.gradle.kts $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build || return 0
COPY . .
RUN gradle clean build

# actual container
FROM openjdk:11
ENV ARTIFACT_NAME=crypto-data-store-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app

WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

EXPOSE 8080
ENTRYPOINT exec java -Dspring.profiles.active=dev -Djvm.password=A14worldpeace -jar ${ARTIFACT_NAME}