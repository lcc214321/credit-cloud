# For DevOps

## 1.构建镜像相关

#### 打包命令

```
gradle bootRepackage
```

#### build后的路径

build/libs/

#### jar包名称

admin-console-1.0.jar

启动命令

```
java -jar admin-console-1.0.jar 
```

## 2. 健康检查

Http GET {{service}}/health/check

## 3. 日志规范

#### 日志插件

slf4j,logback

#### 日志保存路径

运行目录下的logs/*.log

#### docker console

开启console，1M rotate

## 4. MySQL初始配置

schema名称credit_cloud，编码utf8mb4

/src/main/resources/db/migration/V1__init_tables.sql


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

## 4.Flyway

#### 清空本地数据库

```
gradle flywayClean
```

#### 导入数据库

```
gradle flywayMigrate
```

## 5.Swagger

```
http://localhost:8010/swagger-ui.html
```

Develop环境地址

```shell
http://console-api-cc.dev.chinacsci.com/swagger-ui.html
```



# For Client

## 1.环境地址

| 环境     | 地址                                            | 备注               |
| -------- | ----------------------------------------------- | ------------------ |
| 开发环境 | http://http://console-api-cc.dev.chinacsci.com/ | 客户端开发调试使用 |

## 2.接口文档地址

```shell
http://console-api-cc.dev.chinacsci.com/swagger-ui.html#/
```



## 3.接口访问权限请求流程



![jwt](http://o6wkmqikd.bkt.clouddn.com/jwt1.png)



用户登陆成功后会返回`accessToken`和`refreshToken`以及token过期时间等字段， `accessToken`是有过期时间的,客户端需要在token过期前使用`refreshToken`定时刷新`accessToken`以保证接口访问的安全性.若token过期客户端需要重新登录.
把accessToken值放入Header的`Authorization`中既可访问其他接口


## 4.公用账号和密码

```html
username:benkris1
password:a12345678
```



## 5.租户体系结构



![tenant](http://o6wkmqikd.bkt.clouddn.com/tenant.png)



Tenant:租户信息,最顶级结构代表我们的一个目标客户.

App:应用信息 Tenant下可以有多个App,目前阶段租户下只有一个App.

AppKey:App下的开发者信息,包含`appKey`,`app_secret` 开发者在调用信用云接口的时候需要提供此信息.

Group:用户组信息，下面会有多个账号.暂时客户端不需要开发

User:账号信息，登录信用云管理后台使用。