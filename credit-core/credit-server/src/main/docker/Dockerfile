FROM openjdk:8-jre-alpine
MAINTAINER wanghong <wanghong@chinacsci.com>

#----------------------------Copy 项目目录到容器里--------------------------------------------


ENV CREDIT_SERVER_HOME "/opt/credit-server"


WORKDIR $CREDIT_SERVER_HOME

ENTRYPOINT [\
"java",\
"-XX:+UseG1GC",\
"-Djava.net.preferIPv4Stack=true",\
"-Duser.timezone=GMT+08",\
"-jar",\
"credit-server-0.0.1-SNAPSHOT.jar",\
"--spring.profiles.active=dev"\
]

ADD ./ ./