# For Developer

## 1.Server

授权中心的服务端

#### 启动
AuthServerBootstrap

#### 端口配置
8030


## 2.Client

授权中心的Java客户端

#### 启动Server端
AuthServerBootstrap

#### 执行Client测试用例
AuthClientTest

# For DevOps

## 1.编译
gradle clean build -x test

#### 打包命令

```
gradle bootRepackage
```

#### build后的路径

build/libs/

#### jar包名称

auth-center-1.0.0-SNAPSHOT.jar

启动命令

```
java -jar auth-center-1.0.0-SNAPSHOT.jar 
```

## 2. 健康检查

Http GET `{{service}}/api/ping`



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

## 3.重新生成 JOOQ代码

```
 gradle generateSampleJooqSchemaSource
```

## idea中使用lombok启动main方法时候报错
https://www.jianshu.com/p/1a83f692fe9c

