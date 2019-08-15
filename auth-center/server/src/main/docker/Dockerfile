FROM cemmersb/centos-jdk8
MAINTAINER pqin<qinpeng@chinacsci.com>

#----------------------------Copy 项目目录到容器里--------------------------------------------

ADD auth-center-1.0.0-SNAPSHOT.jar /opt/

WORKDIR /opt


ENTRYPOINT ["java", "-Xmx2048m", "-XX:+PrintGCDetails", "-Dcom.sun.management.jmxremote.port=9999",\
 "-Dcom.sun.management.jmxremote.ssl=false","-Xloggc:./log/gc.log",\
  "-Dcom.sun.management.jmxremote.authenticate=false", "-XX:+PrintGCTimeStamps", \
  "-Xdebug", "-Xrunjdwp:transport=dt_socket,address=1301,server=y,suspend=n",\
   "-Duser.timezone=GMT+08", "-jar", "/opt/cloud-auth/auth-center-1.0.0-SNAPSHOT.jar", \
   "--spring.profiles.active=dev"]

