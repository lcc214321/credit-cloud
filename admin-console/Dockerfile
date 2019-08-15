FROM ${DOCKER_REGISTRY}/loansys/base/jdk8-gradle4.6:0.1
MAINTAINER Ben.Ma <macl@chinacsci.com>

ARG SPRING_PROFILES_ACTIVE

RUN mkdir -p /opt/workspace
RUN mkdir -p /opt/admin-console

COPY . /opt/workspace/
WORKDIR /opt


RUN gradle clean -p workspace/ && \
         gradle bootJar -p workspace/ && \
         cp workspace/build/libs/admin-console-1.0.jar admin-console/ && \
         rm -rf workspace/

ENV SPRING_PROFILES_ACTIVE ${SPRING_PROFILES_ACTIVE}
ENV LANG en_US.utf8

ENTRYPOINT ["java",\
 "-Xmx2048m",\
 "-XX:+UseG1GC",\
 "-XX:+PrintGCDetails",\
 "-Dcom.sun.management.jmxremote.ssl=false",\
 "-Xloggc:./log/gc.log",\
 "-verbose:gc",\
 "-XX:+PrintGCTimeStamps",\
 "-Duser.timezone=GMT+08",\
 "-jar", "/opt/admin-console/admin-console-1.0.jar",\
 "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"\
 ]






