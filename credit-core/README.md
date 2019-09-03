# For DevOps

## 1.构建镜像相关

#### 打包命令

```
gradle bootJar
```

#### build后的路径

build/libs/

#### jar包名称

credit-server-0.0.1-SNAPSHOT.jar

启动命令

```
java -jar credit-server-0.0.1-SNAPSHOT.jar
```

## 2. 健康检查

```shell
$ curl -X GET \
  http://pass-api-cc.dev.chinacsci.com/api/health/ping \
  -H 'Postman-Token: a4a450d3-d3b8-4933-9e22-1ed24a6973bc' \
  -H 'cache-control: no-cache'
```



## 3. 日志规范

#### 日志插件

slf4j,logback

#### 日志保存路径

运行目录下的logs/*.log

#### docker console

开启console，1M rotate




# For Developer

## 1.运行

```
cd build/libs/
java -jar admin-console-1.0-SNAPSHOT.jar --spring.profiles.active=local

curl -X GET http://localhost:8010/users 
返回200表示成功 
```

## 2.Docker 

#### 制作docker镜像

```
gradle buildDocker
```

#### 运行

```
docker run harbor.dev.chinacsci.com/credit-cloud/admin-console:1.0-SNAPSHOT
```





# For Client

## 1.环境地址

| 环境     | 地址                                            | 备注               |
| -------- | ----------------------------------------------- | ------------------ |
| 开发环境 | http://pass-api-cc.dev.chinacsci.com | 客户端开发调试使用 |

#gradle多模块打jar，上传本地仓库，并给本地其他项目使用
https://segmentfault.com/a/1190000019222437?utm_source=tag-newest

# CentOS7-Docker 搭建Maven私服
https://www.cnblogs.com/reasonzzy/p/11130117.html

# 为Nexus配置阿里云代理仓库
https://blog.csdn.net/lishuoboy/article/details/99946230

# 使用Docker Registry快速搭建私有镜像仓库
https://blog.csdn.net/qq_32523587/article/details/82879015
https://www.jianshu.com/p/fef890c4d1c2



