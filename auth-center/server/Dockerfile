FROM ${DOCKER_REGISTRY}/loansys/base/jdk8-gradle4.6:0.1
MAINTAINER Ben.Ma <macl@chinacsci.com>

ARG SPRING_PROFILES_ACTIVE
RUN mkdir -p /opt/workspace
RUN mkdir -p /opt/cloud-auth

COPY . /opt/workspace/
WORKDIR /opt


RUN gradle clean -p workspace/ && \
         gradle bootJar -p workspace/server && \
         cp workspace/server/build/libs/auth-center-1.0.0.jar cloud-auth/ && \
         rm -rf workspace/



ENV SPRING_PROFILES_ACTIVE ${SPRING_PROFILES_ACTIVE}


ENTRYPOINT ["java",\
 "-Xmx2048m",\
 "-XX:+UseG1GC",\
 "-XX:+PrintGCDetails",\
 "-Dcom.sun.management.jmxremote.ssl=false",\
 "-Xloggc:./log/gc.log",\
 "-XX:+PrintGCTimeStamps",\
 "-Duser.timezone=GMT+08",\
 "-jar", "/opt/cloud-auth/auth-center-1.0.0.jar",\
 "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"\
 ]

