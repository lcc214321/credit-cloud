FROM ${DOCKER_REGISTRY}/base/jdk:8u92
MAINTAINER Ben.Ma <macl@chinacsci.com>

COPY credit-server/build/libs/credit-server-1.0.0.jar /opt/
WORKDIR /opt/
RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar credit-server-1.0.0.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]

