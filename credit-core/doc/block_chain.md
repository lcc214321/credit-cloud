# 区块链(CredioPlus Chain)API使用文档



## 1.前言

信^+^链(CredioPlus Chain)平台为中证信用旗下区块链基础服务平台，为企业用户特别是金融类企业用户提供基于区块链的技术解决方案。信^+^链具有易于接入、数据私密性高、金融业务支撑性好以及链上数据检索的特性。因此，信^+^链能够为中小微企业及金融服务机构提供一套完整的基于区块链的金融服务基础设施。

## 2.信^+^链节点及用户

信^+^链由区块链节点(Blockchain Node)及用户(Client User)组成, 每个区块链节点和用户都由唯一的数字证书(Digital Certificate)来标识其唯一链上身份。通过数字证书，其他节点和用户不仅可以校验数据及业务请求是否符合业务规则，并且能够在出现纠纷后，能够追溯相应的参与用户及区块链节点。特别的，由于信^+^链平台提供金融业务场景功能，如资产发行及流转等，每一个用户在平台上也拥有用户名和密码的身份，从而实现对金融业务功能的使用。

## 3.信^+^链用户接入

对于用户（Client User)，需遵循如下流程：

1. 调用用户注册接口[注册](#userRegistration)，填写用户注册信息,

2. 使用信^+^链证书生成工具cryptogen (或开源工具如openSSL), 根据信^+^链系统配置，生成相应证书和私钥，具体如下：

   2.1 首先我们了解一下cryptogen需要的配置文件的格式

   ```yaml
   PeerOrgs:
     # ---------------------------------------------------------------------------
     # Org1
     # ---------------------------------------------------------------------------
     - Name: Org1 #用户所在区块链组织的名字，由平台统一分配或线下提交
       Domain: org1.example.com #该组织的域名
       EnableNodeOUs: true
       Specs:
         - Hostname: foo # peer节点的hostname, 同时隐含其域名为 foo.org1.example.com
           CommonName: foo27.org5.example.com # 如果不想使用foo.org1.example.com, 那么可以用这个参数生成指定的域名
         - Hostname: bar # peer节点的hostname, 同时隐含其域名为 bar.org1.example.com
         - Hostname: baz # peer节点的hostname, 同时隐含其域名为 baz.org1.example.com
       Users: #为该组织生成用户证书, 默认会生一个名为Admin的用户的证书
         Count: 2 # 除了上面提到的Admin用户的证书, 还会生成两个新的用户, 默认用户名分别为User1@org1.example.com和User2@org1.example.com
         Names: #额外生成的用户证书，通过这个字段指定相应的用户名
           - Tom # 隐含该用户名为Tome@org1.example.com
           - Jerry # 隐含该用户名为Jerry@org1.example.com
   ```

   2.2 将上述内容保存为文件`crypto-config.yaml`， 执行如下命令

   ```bash
   cryptogen generate --config=./crypto-config.yaml
   ```
   生成初始证书文件夹crypto-config，该文件包含初始用户和节点的身份信息
   2.3 当需要新增用户, 可以直接在`crypto-config.yaml`相应的位置添加用户名, 例如:

   ```yaml
   PeerOrgs:
     - Name: Org1
       Domain: org1.example.com 
       EnableNodeOUs: true
       Specs:
         - Hostname: foo 
           CommonName: foo27.org5.example.com 
         - Hostname: bar 
         - Hostname: baz 
   
       Users: 
         Count: 2 
         Names: 
           - Tom 
           - Jerry 
           - GummyBear # 新增用户, 隐含其用户名为GummyBear@org1.example.com
   ```

   2.4 将初始证书文件夹`crypto-config` 和修改后的`crypto-config.yaml` 放在同一级目录下，执行命令

   ```bash
   cryptogen extend --config=./crypto-config.yam
   ```

   执行完后，新增用户的身份信息会生成在`crypto-config`文件夹下

   下面是该工具生成的证书体系的目录结构.

   ```shell
   peerOrganizations
       └── org1.example.com # org1的所有的证书都在这个目录下面
           ├── ca #msp根证书所在的目录, 下面包含了根证书以及相应的私钥
           ├── msp #msp证书目录
           │   ├── admincerts #Admin用户的证书, 这个证书和users/Admin@org1.example.com/signcerts/下面的证书是一样的
           │   ├── cacerts
           │   └── tlscacerts
           ├── peers #peer节点的证书目录
           │   ├── bar.org1.example.com
           │   ├── baz.org1.example.com
           │   └── foo27.org5.example.com
           ├── tlsca #TLS根证书目录
           └── users #用户证书目录
               ├── Admin@org1.example.com
               |-- User1@org1.example.com # count字段默认生成用户证书
               |-- User2@org1.example.com # count字段默认生成用户证书
               ├── Tom@org1.example.com   # names字段指定生成的用户证书
               └── Jerry@org1.example.com # names字段指定生成的用户证书
               └── GummyBear@org1.example.com #新增用户
   ```

   这样我们就可以在相应的目录找到新增用户(GummyBear)的证书和私钥

对于数字证书及私钥，信^+^链提供托管和非托管模式:

- 非托管模式，用户可以使用中证信^+^链Java SDK完成相应接口的调用。
- 托管模式，完成用户注册后，需遵循如下步骤：
  1. 调用[登陆](#userLogin)，完成用户登陆, 
  2. 调用[上传证书](#userCertUpload), 完成用户数字证书上传,
  3. 调用[上传私钥](#userKeyUpload), 完成用户私钥上传.

完成以上步骤后，即成为信^+^链合法用户。

## 4.信^+^链节点接入

对于信^+^链平台用户的自有节点需求，用户首先要生成节点相关的证书。

在已有配置文件crypto-config.yaml的相应位置添加节点信息：

```yaml
PeerOrgs:
  - Name: Org1
    Domain: org1.example.com 
    EnableNodeOUs: true
    Specs:
      - Hostname: foo 
        CommonName: foo27.org5.example.com 
      - Hostname: bar 
      - Hostname: baz 
      - Hostname: apollo #新增节点, 隐含其域名为apollo@org1.example.com

    Users: 
      Count: 2 
      Names: 
        - Tom 
        - Jerry 
```

如上面的yaml文件, 新增了节点`apollo@org1.example.com`和用户`GummyBear@org1.example.com`. 

将初始证书文件夹`crypto-config` 和修改后的`crypto-config.yaml` 放在同一级目录下，执行如下命令

```bash
cryptogen extend --config=./crypto-config.yaml
```

执行完后，新增节点的身份信息会生成在`crypto-config`文件夹下

```shell
peerOrganizations
    └── org1.example.com # org1的所有的证书都在这个目录下面
        ├── ca #msp为区块链组织根证书所在的目录, 下面包含了根证书以及相应的私钥
        ├── msp #msp证书目录
        │   ├── admincerts #区块链组织的Admin用户的证书, 这个证书和users/Admin@org1.example.com/signcerts/下面的证书是一样的
        │   ├── cacerts
        │   └── tlscacerts
        ├── peers #peer节点的证书目录
        │   ├── bar.org1.example.com
        │   ├── baz.org1.example.com
        │   └── foo27.org5.example.com
        │   └── apollo.org1.example.com #新增节点
        ├── tlsca #TLS根证书目录
        └── users #用户证书目录
            ├── Admin@org1.example.com
            |-- User1@org1.example.com
            |-- User2@org1.example.com
            ├── Tom@org1.example.com
            └── Jerry@org1.example.com
```

对于需要独立区块链节点的用户，信^+^链同样提供托管和非托管模式的区块链节点接入方式。

在托管模式下，用户只需提交如下信息到信^+^链，信^+^链将会分配相应节点给予用户：

1. 节点存储空间大小;
2. 节点和管理员用户的MSP（区块链组织身份）和TLS（加密信道）的证书和私钥, 包括节点和管理员用户的`msp`和`tls`目录下的所有的文件需要通过线下方式发送给信^+^链.
3. 需要支持的功能，目前可选项为[金融资产及交易服务](#assetOnChainService) 以及[链上数据检索服务](#dataSearchService)

对于非托管模式，节点接入到信^+^链平台，需要满足以下条件：

1. 具有有效信^+^平台节点身份, 如使用信^+^链证书生成工具cryptogen (或开源工具如openSSL)生成的包含节点身份信息的`crypto-config`文件夹
2. 部署区块链节点，配置外网域名地址
   区块链节点的部署方法，推荐以下两种方法，可选择其中一种部署方法

   - 二进制部署 

     1. 创建文件夹`/var/hyperledger/config`,将文档附件的配置文件`configtx.yaml  core.yaml`下载到该文件夹
     2. 创建文件夹`/var/hyperledger/production`, 节点运行过程中的账本数据将会放到这个文件夹下面
     3. 假设节点的域名为peer0.org2.example.com,设置如下环境变量

        ```bash 
        FABRIC_CFG_PATH=/var/hyperledger/config 
        PEER_TLS_PATH=./crypto-config/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls #存放该节点tls证书的文件夹
        CORE_PEER_TLS_ROOTCERT_FILE=${PEER_TLS_PATH}/ca.crt
        CORE_PEER_TLS_KEY_FILE=${PEER_TLS_PATH}/server.key
        CORE_PEER_TLS_CERT_FILE=${PEER_TLS_PATH}/server.crt
        CORE_PEER_PROFILE_ENABLED=false
        CORE_PEER_GOSSIP_ORGLEADER=false
        CORE_PEER_LOCALMSPID=Org2MSP #org msp id
        CORE_VM_ENDPOINT=unix:///host/var/run/docker.sock
        CORE_PEER_TLS_ENABLED=true
        CORE_PEER_ID=peer0.org2.example.com
        CORE_LOGGING_LEVEL=INFO
        CORE_PEER_GOSSIP_EXTERNALENDPOINT=peer0.org2.example.com:7051
        CORE_PEER_ADDRESS=peer0.org2.example.com:7051
        CORE_PEER_GOSSIP_USELEADERELECTION=true
        ```
        
     4. 使用命令`peer node start`启动peer

   - docker 容器部署

     1. 构建如下docker-compose yaml file. 下面的例子里面假设节点的域名为`peer0.org2.example.com`

        ```yaml
        version: '2'
        volumes:
          peer0.org2.example.com:
        
        services:
          peer0.org2.example.com:
            container_name: peer0.org2.example.com
            image: hyperledger/fabric-peer:1.1.0
            environment:
              - CORE_VM_ENDPOINT=unix:///host/var/run/docker.sock
              - CORE_LOGGING_LEVEL=INFO
              - CORE_PEER_TLS_ENABLED=true
              - CORE_PEER_GOSSIP_USELEADERELECTION=true
              - CORE_PEER_GOSSIP_ORGLEADER=false
              - CORE_PEER_PROFILE_ENABLED=true
              - CORE_PEER_TLS_CERT_FILE=/etc/hyperledger/fabric/tls/server.crt
              - CORE_PEER_TLS_KEY_FILE=/etc/hyperledger/fabric/tls/server.key
              - CORE_PEER_TLS_ROOTCERT_FILE=/etc/hyperledger/fabric/tls/ca.crt
              - CORE_PEER_ID=peer0.org2.example.com
              - CORE_PEER_ADDRESS=peer0.org2.example.com:7051
              - CORE_PEER_GOSSIP_EXTERNALENDPOINT=peer0.org2.example.com:7051
              - CORE_PEER_LOCALMSPID=Org2MSP #org msp Id
            volumes:
                - /var/run/:/host/var/run/
                - ./crypto-config/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/msp:/etc/hyperledger/fabric/msp
                - ./crypto-config/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls:/etc/hyperledger/fabric/tls
                - ./data/peer0.org2.example.com:/var/hyperledger/production
            ports:
              - 7051:7051
              - 7053:7053
            working_dir: /opt/gopath/src/github.com/hyperledger/fabric/peer
            command: peer node start
        ```

     2. 将上述内容保存为`peer.yaml`并放到证书根目录`crypto-config`的同级目录

     3. 使用命令`docker-compose -f peer.yaml up -d`启动

     4. 节点运行中的账本数据在文件夹`./data/peer0.org2.example.com`里面

   部署完毕后配置节点的外网域名地址，开放端口`7051`，`7053`，外网域名要满足如下格式：

   ```markdown
         节点的HostName.组织DomainName
   ```

   将节点的如下相关信息发送给信^+^链，申请加入信^+^区块链

   ```markdown
       节点所属组织域名DomainName 
       节点HostName
       节点外网域名地址
       节点所属组织msp ca证书
       节点所属组织tls ca证书
       节点所属组织里admin用户的证书
   ```


## 5.用户管理

在信^+^链中，每一个用户有一个链上身份，即区块链组织(BlockchainAffiliation)。其主要目的为识别用户的链上身份，实现用户所属区块链节点和数据的管理。同时，每一个用户有一个企业身份信息（BusinessAffiliation)，其主要为及用户物理世界的身份。在信^+^链中，一个用户一定属于某一个企业（BusinessAffiliation), 同时，由于一个企业一定属于某一个区块链组织(BlockchainAffiliation), 所以，某一个用户一定也属于其所属企业对应的区块链组织。另外，每一个用户在信^+^链中，拥有不同的角色（权限），即普通用户(client)及管理员(admin)，从而实现普通用户无法调用管理类接口。通常来说，每一个企业拥有一个或多个管理员角色用户，实现对其拥有的区块链网络的管理。

信^+^链提供用户管理类接口，主要完成如下功能：

1. [2001 注册](#userRegistration)
2. [2002 用户登录](#userLogIn)
3. [2003 用户登出](#userLogOut)*
4. [2004 修改密码](#changePassword)*
5. [2005 查询用户角色](#queryUserRole)*
6. [2006 上传证书](#userCertUpload)*
7. [2007 上传私钥](#userKeyUpload)*
8. [2008 下载证书](#userCertDownload)*
9. [2009 下载私钥](#userKeyDownload)*
10. [2010 获取活跃用户数](#userCount)*
11. [2011 冻结用户](#revokeUser)*
12. [2012 激活用户](#activateUser)*
13. [2013 获取用户列表](#getUserListByBlockchainAffiliationID)*
14. [2014 用户所属企业详情](#getBusinessDetailsByUser)*
15. [2015 获取用户详情](#getUserDetails)*
16. [2016 获取手机验证码](#getSmsCode)*

*标注接口需要用户登陆信^+^链后操作。

### 2001 用户注册<a id="userRegistration"> </a>
- URL

```
POST /chain/api/user/open/createUser
Content-Type: application/json
```

* 请求报文

| 字段名 | 类型 | 必填 |可选值|
|:---| :---| :---|:---|
|businessAffiliationId|integer|true|所属企业ID|
| password              | string  | true |            |
|username|string|true||
|telephoneNumer|string|true|用户手机号|
|smsCode|String|true|手机验证码|

* 报文示例

```json
{
  "username":"ADMIN",
  "password":"adminpw",
  "businessAffiliationId":1,
  "telephoneNumer":1381752000,
   "smsCode":"88899" 
}
```

* 返回报文

```json
{
  "success": true,
  "errorMessage": null,
  "code": "CM10000",
  "data":
  {
    "id": 9,
    "name": "stringfaf",
    "blockchainAffiliationId": 1,
    "blockchainAffiliation": "sdadas",
    "status": "ACTIVE"
     }
}
```

* 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
| id |long|否| 用户id|
| name |string|否|用户名称|
| blockchainAffiliationId |long|否| Affiliation id|
| blockchainAffiliation |string|否|blockchain name|
| status |string|否|状态|



### 2002 用户登录<a id="userLogin"> </a>
* URL

```json
POST /chain/api/open/login
Content-Type: application/json
```

* 请求报文

| 名称     | 类型   | 必填 | 说明     |
| :------- | :----- | :--- | :------- |
| username | string | 是   | 用户名称 |
| password | String | 是   | 用户密码 |

* 报文示例

```json
{
   "username":"ADMIN",
   "password":"adminpw"
}
```


* 返回报文

```json
{
  "success":true,
  "errorMessage": null,
  "code": "CM10000",
  "data": {
   "accessToken": "string",
		"expiresIn": 0,
		"refreshToken": "string",
		"scope": "string",
		"tokenType": "string"
		}
}
```

* 字段说明

|字段|类型|是否为null|备注|
|:--|:--|:--|:--|
|accessToken|string|否|登录成功以后获取的用于访问信^+^链的访问token|
|expiresIn|integer|否|access_token的实效时间, 以秒为单位|
|scope|string|否|token的有效范围|
|tokenType|string|否|当前的token类型, 如 'Bearer'|
|refreshToken|string|否|accessToken失效后进行刷新请求的token|

成功获得accessToken以后, 即可开始使用信^+^链的大部分API. 每一个API调用的时都需带上获得的accessToken. 

```
GET/POST /chain/api/
X-CP-Token: Bearer {accessToken}
```
即在每次http请求的header中都带上accessToken

### 2003 用户登出 <a id="userLogOut"> </a>

* URL

```
POST /chain/api/client/user/logout
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```


* 请求报文
  
    无
  
* 报文示例
     无 
     
* 返回报文

   见返回报文格式 


### 2004 修改密码  <a id="changePassword"></a>

* URL

```
POST /chain/api/user/client/user/updatePassword
X-CP-Token: Bearer {accessToken}
Content-Type: application/json
```

* 请求报文
  
|名称 | 类型    | 必填 | 说明   |
| :------ | :------ | :--- | :---------------- |
| username |string  | 是   | 用户名称|
| password |string  | 是   | 用户密码|
| newPassword |string  | 是   | 新密码|
| confirmPassword |string  | 是   | 确认密码|

* 报文示例

```json
{
   "confirmPassword": "string",
   "newPassword": "string",
   "password": "string",
   "username": "string"
}
```

* 返回报文

   见返回报文格式 
   
### 2005 查询用户角色 <a id="queryUserRole"> </a>
* 功能描述

   查询当前用户角色，用户可以有多个角色
   
* URL

```
GET /chain/api/user/client/user/queryUserRole
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 请求报文
  
   无
   
* 报文示例

   无

* 返回报文
  
```json
{
  "success": true,
  "errorMessage": null,
  "code": "CM10000",,
  "data{
   	"username":"ADMIN",
   	"roleName":"admin",
   	"userRoleId":1
   	}
}
```

* 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
| roleName |string|否|角色名称|
| username |string|否|用户名称|
| userRoleId |long|否|用户角色id|


### 2006 <a id="userCertUpload"></a>上传证书
* URL 

```
POST /chain/api/user/client/user/cert
X-CP-Token: Bearer {accessToken}
Content-Type: multipart/form-data
```

* 请求参数

```
key="file", data= 用户证书数据
```

* 返回报文

```json
{
"success":true,
"code": "CM10000",
"errorMessage": "",
"data": {
}
}
```

### 2007 <a id="userKeyUpload"></a>上传私钥 
* URL 

```
POST /chain/api/user/client/user/privateKey
X-CP-Token: Bearer {accessToken}
Content-Type: multipart/form-data
```

* 请求参数

```
key="file", data= 用户私钥数据
```

* 返回报文

```json
{
"success":true,
"code": "CM10000",
"errorMessage": "",
"data": {
}
}
```

### 2008 <a id="userCertDownload"></a>下载证书
* URL

```
GET /chain/api/user/client/user/cert
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 返回报文

```
该用户证书数据
```

 **注意** 该接口直接返回用户证书数据

### 2009 <a id="userKeyDownload"> </a>下载私钥
* URL

```
GET /chain/api/user/client/user/privateKey
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 返回报文

```
该用户私钥数据
```

 **注意** 该接口直接返回用户私钥数据

### 2010 <a id="userCount"> </a>获取活跃用户数
* URL

```
GET /chain/api/user/client/user/count
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 返回报文

```json
{
"success":true,
"code": "CM10000",
"errorMessge": "",
"data": 10
}
```

**注意** 该接口的data中直接以integer返回当前的活跃用户数

* 字段说明

|字段|类型|是否为null|备注|
|:--|:--|:--|:--|
|data|integer|否||


### 2011 冻结用户 <a id="revokeUser"></a>
* URL

```
POST /chain/api/user/admin/user/revoke
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 请求报文

|名称 | 类型    | 必填 | 说明   |
| :------ | :------ | :--- | :---------------- |
| userId |long  | 是   | 用户id |
| reason |string  | 是   | 注销原因 |

* 报文示例

    
|参数名称 | 参数值    |
| :------ | :------ |
| userId | 1  |
| reason |test  |

* 返回报文

   见返回报文格式 

### 2012 激活用户 <a id="activateUser"></a>
* URL

```
POST /chain/api/user/admin/user/activate
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 请求报文

|名称 | 类型    | 必填 | 说明   |
| :------ | :------ | :--- | :---------------- |
| userId |long  | 是   | 用户id |

* 报文示例

|参数名称 | 参数值    |
| :------ | :------ |
| userId | 1  |



* 返回报文

   见返回报文格式 

### 2013 获取用户列表<a id="getUserListByBlockchainAffiliationID"></a>

* 功能描述

   根据当前用户的区块链组织ID(blockchainAffiliationID),获取归属该区块链组织的用户列表
   
* URL

```
GET /chain/api/user/admin/user/list
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 请求报文

|名称 | 类型    | 必填 | 说明   |
| :------ | :------ | :--- | :---------------- |
| page |int  | 是   | 页数  |
| pageSize |int  | 是   | 每页条数 |

* 报文示例


|参数名称 | 参数值   |
| :------ | :------ |
| page |1  |
| pageSize |10|



* 返回报文

```json
{
"success": true,
"errorMessage": null,
"code": "CM10000",
"data": {
"page": 1,
"pageSize": 10,
"totalCount": 2,
"items": [
  {
    "id": 1,
    "createTime": null,
    "updateTime": null,
    "createUser": null,
    "updateUser": null,
    "delFlag": false,
    "username": "ADMIN",
    "status": "ACTIVE",
    "blockchainAffiliation": "test",
    "blockchainAffiliationId": 1,
    "businessAffiliationId": 1,
    "mspId": "Org1MSP",
    "cert": null,
    "privateKey": null
  },
  {
    "id": 2,
    "createTime": null,
    "updateTime": null,
    "createUser": null,
    "updateUser": null,
    "delFlag": false,
    "username": "ACTUATOR",
    "status": "ACTIVE",
    "blockchainAffiliation": "test",
    "blockchainAffiliationId": 1,
    "businessAffiliationId": 1,
    "mspId": "Org1MSP",
    "cert": null,
    "privateKey": null
  }
]
}
}   
```

* 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
| id |long|否| 用户id|
| username |string|否|用户名称|
| blockchainAffiliationId |long|否| Affiliation id|
| businessAffiliationId |long|否|企业id|
| mspId |long|否| Affiliation mspId|
| cert |long|否|证书|
| privateKey |long|否|私钥|
| status |string|否|状态|
| description |string|是||

### 2014 用户所属企业详情 <a id="getBusinessDetailsByUser"></a>

* URL

```
GET /chain/api/user/client/business/user/current
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 请求报文
  
    无
  
* 报文示例
     无
     
* 返回报文

```json
{
 "success": true,
 "errorMessage": null,
 "code": "CM10000",
 "data": {
   "id": 1,
   "createTime": "2018-10-26T06:31:49.000+0000",
   "updateTime": "2018-10-30T08:22:10.000+0000",
   "createUser": null,
   "updateUser": null,
   "delFlag": false,
   "name": "test",
   "description": {
     "province": "china",
     "address": "china",
     "authentication": "china"
   },
   "blockchainAffiliationId": 1,
   "userNumber": 2
 }
}   
```

* 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
| id |long|否| 企业id|
| name |string|否|企业名称|
| blockchainAffiliationId |long|否| Affiliation id|
| description |string|是||
| userNumber |long|否| 用户数|

### 2015 获取用户详情 <a id="getUserDetails"></a>

* URL

```
GET /chain/api/user/client/user/current
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 请求报文
  
    无
  
* 报文示例
     无 
     
* 返回报文
  
```json
{
  "success": true,
  "errorMessage": null,
  "code": "CM10000",
  "data": {
    "name": "ADMIN",
    "businessAffiliationName": "test"
  }
}
```

* 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
| businessAffiliationName |string|否|企业名称|
| name |string|否|用户名称|

 

### 2016 获取验证码 <a id="getSmsCode"></a>

- URL

 ```shell
GET /chain/api/uaa/open/getSmsCode
Content-Type application/json
 ```

* 请求报文

| 名称           | 类型   | 必填 | 说明   |
| :------------- | :----- | :--- | :----- |
| telephoneNumer | string | 是   | 手机号 |

* 报文示例

| 参数名称       | 参数值      |
| :------------- | :---------- |
| telephoneNumer | 130XXXXXXXX |

* 返回报文

  

## 6.企业信息管理

在信^+^链中，每一个企业（及用户）有一个链上身份，即区块链组织(BlockchainAffiliation)。其主要目的为识别企业及用户的链上身份，实现企业所属区块链节点和数据的管理。另外，每一个企业（及用户）有一个企业身份信息（BusinessAffiliation)，其主要为企业（及用户）物理世界的身份。在信^+^链中，一个区块链组织(blockchainAffiliation)可能包含一个或者多个企业(BusinessAffiliation)，从而实现多个中小企业可能共享某一些区块链节点的功能。

企业管理类接口主要提供对象为信^+^平台运行管理，实现企业用户信息的管理与注册
主要提供如下接口：

1. [2101 创建区块链组织信息](#createBlockchainInfo)*
2. [2102 更新区块链组织信息](#updateBlockchainAffiliationInfo)*
3. [2103 查询企业列表(根据区块链组织ID)](#queryBusinessAffliationList)*
4. [2104 创建企业信息](#createBusinessAffiliationInfo)*
5. [2105 上传企业标识](#uploadBusinessAffiliationLogo)*
6. [2106 获取企业用户列表](#queryUserListByBusinessAffiliationId)*
7. [2107 获取企业标识](#queryBusinessAffiliationLogo)*
8. [2108 更新企业信息](#updateBusinessAffiliationInfo)*

*标注接口需要用户登陆信^+^链后操作。


###  2101 创建区块链组织信息  <a id="createBlockchainInfo"></a>

* URL

```
POST /chain/api/user/admin/blockchain/createBlockchainAffiliation
X-CP-Token: Bearer {accessToken}
Content-Type: application/json
```

 * 请求报文

|名称 | 类型    | 必填 | 说明   |
| :------ | :------ | :--- | :---------------- |
| name |string | 是   | 区块链机构名称  |
| mspId |string  | 是   | Affiliation 的MSPID |
| description |string  | 否   | 描述 |

 * 报文示例

```json
{
   	"name":“中证”,
   	"mspId":“中证mspId”，
   	"description":"description"
}
```

 * 返回报文

```json
 {
  "success": true,
  "errorMessage": null,
  "code": "CM10000",
  "data": {
    "id": 2,
    "name": "中证信用",
    "description": "test",
    "mspId": "orgid"
  }
}
```
 * 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
| name |string|否|Affiliation名称|
| mspId |string|否|Affiliation 的MSPID|
| description |string|是||
| id |long|否||

### 2102 更新区块链组织信息<a id="updateBlockchainAffiliationInfo"></a>

* URL

```
POST /chain/api/user/admin/blockchain/update
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 请求报文


|名称 | 类型    | 必填 | 说明   |
| :------ | :------ | :--- | :---------------- |
| blockchainAffiliationId  |long  | 是   | blockchainAffiliationId |
| name |string | 是   | 区块链机构名称  |
| description |string  | 是   | 描述 |

* 报文示例


|名称 | 类型    |
| :------ | :------ |
| blockchainAffiliationId  |1  |
| name | newname |
| description |test|

 * 返回报文
见返回报文格式

### 2103 查询企业列表(根据区块链组织ID）<a id="queryBusinessAffliationList"></a>

* URL

```
GET /chain/api/user/admin/blockchain/businessAffiliationList
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

 * 请求报文


|名称 | 类型    | 必填 | 说明   |
| :------ | :------ | :------ | :------ |
| blockchainAffiliationId  |long  | 是   | blockchainAffiliationId |
| page |int  | 否   | 页数  |
| pageSize |int  | 否   | 页大小 |


* 报文示例 


|名称 | 类型    |
| :------ | :------ |
| blockchainAffiliationId  |1  |
| page |1 |
| pageSize |10|



 * 返回报文   
```json
{
"success": true,
"errorMessage": null,
"code": "CM10000",
"data": {
"page": 1,
"pageSize": 10,
"totalCount": 1,
"items": [
  {
    "id": 1,
    "createTime": "2018-10-26T06:31:49.000+0000",
    "updateTime": "2018-10-30T07:40:09.000+0000",
    "createUser": null,
    "updateUser": null,
    "delFlag": false,
    "name": "testBU",
    "description": null,
    "blockchainAffiliationId": 1,
    "creditNo": "123232435"
  }
]
}
}
```

* 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
| name |string|否|企业名称|
| creditNo |string|否|统一信用代码|
| description |string|是||
| blockchainAffiliationId |long|否|orgid|

### 2104 创建企业信息 <a id="createBusinessAffiliationInfo"></a>
 * URL

```
POST /chain/api/user/admin/business/createBU
X-CP-Token: Bearer {accessToken}
Content-Type: application/json
```

* 请求报文

|名称 | 类型    | 必填 | 说明   |
| :------ | :------ | :--- | :---------------- |
| name  | string  | 是   | 企业名称 |
| creditNo | string | 是   | 统一信用代码 |
| description | string  | 否   | 描述信息  |
| blockchainAffiliationId | long | 是 |  |

 * 报文示例 

```json
{
   	"name":"中证增信",
   	"creditNo":"CN123456",
   	"description":{
      "province": "sh",
      "address": "sh",
      "authentication": "sh"
    },
   	"blockchainAffiliationId":1
}
```

 * 返回报文

```json
{
  "success": true,
  "errorMessage": null,
  "code": "CM10000",
  "data": {
    "id": 2,
    "createTime": "2018-10-30T07:51:13.967+0000",
    "updateTime": "2018-10-30T07:51:13.967+0000",
    "createUser": null,
    "updateUser": null,
    "delFlag": false,
    "name": "chinacsci",
    "description": {
      "province": "sh",
      "address": "sh",
      "authentication": "sh"
    },
    "blockchainAffiliationId": 1,
    "creditNo": "123456"
  }
}	
```

 * 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
| name |string|否|business_affiliation 名字|
| blockchainAffiliationId |string|否|Affiliation ID|
| creditNo |string|否|统一信用代码|
| description |string|是||
| id |long|否||

### 2105 上传企业标识 <a id="uploadBusinessAffiliationLogo"></a>

* URL

 ```
   POST /chain/api/user/admin/business/uploadLogoFile
   X-CP-Token: Bearer {accessToken}
   Content-Type: multipart/form-data
 ```

* 请求报文


|名称 | 类型    | 必填 | 说明   |
| :------ | :------ | :--- | :---------------- |
| businessAffiliationId  | long  | 是   | 企业id |
| file | MultipartFile  | 是   | 企业图片,目前只支持PNG格式  |

  * 报文示例  

|参数名称 | 参数值   |
| :------ | :------ |
| businessAffiliationId  | 1|
| file | xxx.png  |


 * 返回报文

见返回报文格式 

### 2106 获取企业用户列表  <a id="queryUserListByBusinessAffiliationId"></a>

* URL

 ```
   GET /chain/api/user/admin/business/userList
   X-CP-Token: Bearer {accessToken}
   Content-Type: application/x-www-form-urlencoded
 ```

* 请求报文


|名称 | 类型    | 必填 | 说明   |
| :------ | :------ | :------ | :------ |
| businessAffiliationId  | long  | 是   | 企业id |
| page |int  | 是   | 页数  |
| pageSize |int  | 是   | 每页条数 |

 * 报文示例


|参数名称 | 参数值   |
| :------ | :------ |
| businessAffiliationId  | 1  |
| page |1  |
| pageSize |10|

 * 返回报文
   
  ```json
   {
	"page": 1,
    "pageSize": 10,
    "totalCount": 1,
    "items": [
      {
        "id": 1,
        "createTime": null,
        "updateTime": null,
        "createUser": null,
        "updateUser": null,
        "delFlag": false,
        "username": "ADMIN",
        "status": "ACTIVE",
        "blockchainAffiliation": null,
        "blockchainAffiliationId": null,
        "businessAffiliationId": null,
        "mspId": null,
        "cert": null,
        "privateKey": null
      }
    ]
   }
  ```

 * 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
| username |string|否|用户名称|
| blockchainAffiliationId |long|否| Affiliation id|
| businessAffiliationId |long|否|企业id|
| mspId |long|否| Affiliation mspId|
| cert |long|否|证书|
| privateKey |long|否|私钥|
| status |string|否|状态|
| description |string|是||

### 2107 获取企业标识  <a id="queryBusinessAffiliationLogo"></a>

* URL

```
GET /chain/api/user/admin/business/getLogo
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 请求报文


|名称 | 类型    | 必填 | 说明   |
| :------ | :------ | :--- | :---------------- |
| businessAffiliationId  | long  | 是   | 企业id |

* 报文示例


|参数名称 | 参数值   |
| :------ | :------ |
| businessAffiliationId  | 1  |

* 返回报文

见返回报文格式 

### 2108 更新企业信息 <a id="updateBusinessAffiliationInfo"></a>

 * URL

```
POST /chain/api/user/admin/business/update
X-CP-Token: Bearer {accessToken}
Content-Type: application/json
```

 * 请求报文

|名称 | 类型    | 必填 | 说明   |
| :------ | :------ | :--- | :---------------- |
| id  | long  | 是   | 企业id |
| name |string  | 是   | 企业名称  |
| creditNo |string  | 是   | 企业证件号 |
| description |string  | 否   | 描述|

  * 报文示例

```json
{
  "creditNo": "41234234",
  "description": {
    "address": "china",
    "authentication": "china",
    "province": "china"
  },
  "id": 1,
  "name": "test"
}   
```

 * 返回报文

见返回报文格式 




## 7.金融资产及交易 <a id="assetOnchainService"></a>


信^+^链目前针对供应链金融及消费金融场景，提供如下接口：

1. [2201 资产发布](#assetIssue)*
2. [2202 资产状态更新](#assetUpdateStatus)*
3. [2203 资产交易发布](#assetTransIssue)*
4. [2204 资产交易状态更新](#assetTransStatusUpdate)*
5. [2205 资产/交易数据授权](#assetAuthorize)*
6. [2206 授权信息查询](#AssetAuthorizationQuery)*
7. [2207 查询上链资产](#queryAssetInfoById)*
8. [2208 查询上链交易信息](#queryTransInfoById)*

信^+^链的Post类接口为异步处理模式，因此，为了满足事务性，信^+^链提供如下特性：对于[资产发布](#assetIssue)、[资产状态更新](#assetUpdateStatus)、[资产交易发布](#assetTransIssue)、[资产/交易数据授权](#assetAuthorize)接口，提供接口幂等性及确定性，其有如下含义：

1. 接口返回状态码为成功时，会保证其完成；
2. 接口返回状态异常后，重试不会造成重复事务.

*标注接口需要用户登陆信^+^链后操作。


### 2201 资产发布 <a id="assetIssue"></a>
* URL  
  
```
POST /chain/api/data/asset/issue
Content-Type: application/json
X-CP-Token: Bearer {accessToken}
```

* 请求报文

| 字段名         | 类型    	|必填  |说明       |
| :------------- | :------ | :------ | :-------------------- |
| assetId | string | 否 | 资产唯一编号 |
| assetMode | string | 是 | 资产属性 |
| externalBizId | string | 是 | 外部业务编号 |
| category1 | string | 是 | 一级类别 |
| category2   | string | 是 | 二级类别 |
| channelName | string | 否 | 渠道名称 |
| channelCode | string | 是 | 渠道编号 |
| assetName   | string | 是 | 资产名称 |
| status      | string | 否 | 资产状态 |
| amount | number | 是 | 资产金额 |
| loanedAmt | number | 否 | 已融资金额 |
| startDate | date | 是 | 起始日期,格式“yyyy-MM-ddTHH:mm:ss” |
| endDate | date | 是 | 到期日期,格式“yyyy-MM-ddTHH:mm:ss” |
| issuer   | string | 是 | 资产发布者名称 |
| issuerId | string | 是 | 资产发布者Id |
| owner    | string | 是 | 资产所有人名称 |
| ownerId  | string | 是 | 资产所有人Id |
| assetMetaData | AssetMetaData | 是 | 资产元信息，根据资产类型有不同的结构，见[附录D1](#defOfAssetMetaData)|
| otherInfo | string | 否 | 其他信息 |
| hash |string | 否 | 资产信息的hash, hash计算规则如下 |
| needEncrypted |boolean | 是 | 是否需要加密, "true"/"false" |
| encryptedFields | string | 否 | 加密字段集 |

<a id="DefOfAssetInfo"></a> 
*其中:*

*a. 资产类型定义见[附录C1](#listOfAssetType), 资产属性定义见[附录C2](#listOfAssetMode),资产状态定义见[附录C3](#listOfAssetStatus)，期限单位定义见[附录C11](#listOfPeriodUnit)*

b. *hash计算规则:*

> md5Hex(Category1+Category2+ChannelCode+AssetName+Amount+Issuer+ExternalBizI+EndDate+hash(AssetMetaData.toString()))

* 返回报文

```json
{
"success":true,
"code": "CM10000",
"errorMessage": null,
"data": {
    	"assetId":"asfddfssf"
  }                 
}
  
```
* 字段说明

| 字段名      | 类型    |说明          |
| :------- | :------ | :----------- |
| assetId | string | 资产唯一编号 |



### 2202 资产状态更新 <a id="assetUpdateStatus"></a>
* URL  

```
PUT /chain/api/data/asset/updateStatus
Content-Type: application/json
X-CP-Token: Bearer {accessToken}
```

* 请求报文

| 字段名         | 类型    	|必填  |说明      |
| :------------- | :------ | :------ | :------------------------|
| assetId        | string  | 是     	| 资产唯一编号 |
| status		  | string  | 是   		|状态编码，见附录[C3](#listOfAssetStatus)|

* 报文示例

```json
{
  "assetId":"AR00001",
   "status":"05"
}
```

* 返回报文	

见返回报文格式

### 2203 资产交易发布 <a id="assetTransIssue"></a>

* URL  
  
```
POST /chain/api/data/asset/uploadTrans
Content-Type: application/json
X-CP-Token: Bearer {accessToken}
```

* 请求报文

| 字段名         | 类型    	|必填  |说明                              |
| :------------- | :------| :------| :----------------------|
| transId        | string  | 是     	| 交易ID                      |
| parentTransId  | string | 否        | 父交易ID                     |
| transType	| string | 是 |交易类型, 定义见附录[C1](#listOfTransTypeAndStatus)   |
| assetId    | string | 是		| 资产编号                              |
| borrowAmt    | string | 是		| 金额                                  |
| status    | string | 是		| 交易后，资产状态，接入业务方确定    |
| initiator    | string | 否	| 交易发起人                                  |
| initiatorId    | string | 是		| 交易发起人Id      |
| counterparty    | string |否	| 交易对手                                  |
| counterpartyId    | string | 是		| 交易对手Id        |
| metaData    | object | 是		| 交易元信息，TransMetaData对象定义见[附录D2](#defOfTransMetaData) |
| encryptedFields    | string | 否		| 加密字段集，使用竖线分割|
| transTime    | string | 是		| 交易时间，格式：yyyy-MM-ddTHH:mm:ss|
| opInfo    | object | 是		| 资产交易中间步骤，OpInfo对象定义见[附录D3](#defOfOpInfo) |
|needEncrypted|boolean|是| 是否需要加密|

 * 报文示例

  ```json
  {
     "transId":"123213", 
     "transType":"01", 
     "assetId":"AR0001",
     "borrowAmt":"1000", 
     "status":"VALID", 
     "initiator":"test1",
     "initiatorId":"123",
     "counterparty":"test2",
     "counterpartyId":"124", 
     "metaData":{
            ...
        }, 
     "encryptedFields":"name|idNo|cellphone",
     "needEncrypted":true,
     "transTime":"2018-08-18T12:12:01",
     "opInfo":{  
        "opId":"123213",
        "opType":"01", 
        "operator":"user2", 
        "opTime":"2018-08-18T12:12:01" 
     }
  }
  ```

* 返回报文	

```json
{
"success":true,
"code": "CM10000",
"errorMessage": null,
"data": {
    	"opId":"asfddfssf"
  }                 
}
```
* 字段说明


| 字段名      | 类型    |说明          |
| :------- | :------ | :----------- |
| opId | string | 操作唯一编号 |

### 2204 资产交易状态更新 <a id="AssetTranStatusUpdate"></a>

* URL  

```
PUT /chain/api/data/asset/updateTransStatus
Content-Type: application/json
X-CP-Token: Bearer {accessToken}
```

* 请求报文

| 字段名 | 类型   | 必填 | 说明                                                  |
| :----- | :----- | :--- | :------------------------ |
| opId   | string | 是 | 操作ID                                                |
| status | string | 否 | 交易状态，枚举值见[附录C4](#listOfTransTypeAndStatus)|

* 报文示例

```json
{
 "opId":"123213", 
 "status":"01"
}
```

* 返回报文	

见返回报文格式

### 2205 数据授权 <a id="assetAuthorize"></a>

* URL

```
POST /chain/api/data/asset/auth
Content-Type: application/json
X-CP-Token: Bearer {accessToken}
```

* 请求报文

| 字段名       | 类型   | 必填 | 说明                |
| ------------ | ------ | ---- | ------------------- |
| authorizedId | Long   | 是   | 被授权人Id          |
| authType     | String | 是   | assetInfo/transInfo |
| bizId        | String | 是   | assetId 或 opId     |

* 报文示例

```json
{
"authorizedId": "1",
"authType": "AssetInfo",
"bizId": "234234"
}
```

* 返回报文

```json
{
"success":true,
"code": "CM10000",
"errorMessage": null,
"data": "授权成功"                
}
```

### 2206 授权信息查询 <a id="assetAuthorizationQuery"></a>

* URL

```
GET /chain/api/data/asset/getAuthorizedData
X-CP-Token: Bearer {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 请求报文

| 字段名   | 类型   | 必填 | 说明                 |
| -------- | ------ | ---- | -------------------- |
| bizId     | String | 是   | assetId/opId         |
| authType | String | 是   | assetInfo, transInfo |

* 返回报文

| 字段名          | 类型   | 说明              |
| --------------- | ------ | ----------------- |
| rawData         | String | json字符串        |
| encryptedFields | String | 加密字段集 |

* 报文示例

```json
{
    "success": true,
    "errorMessage": null,
    "code": "CM10000",
    "data": {
    "rawData": "{\"assetMode\":\"asset\",\"assetId\":\"zichan24\",\"externalBizId\":\"extId1224\",\"category1\":\"category1\",\"category2\":\"category2\",\"channelName\":\"cn1\",\"channelCode\":\"cc1\",\"assetName\":\"assetName11\",\"status\":\"LOANED\",\"amount\":10000,\"loanedAmt\":9000,\"startDate\":\"2018-08-19T04:15:03\",\"endDate\":\"2019-08-19T04:15:03\",\"issuer\":\"issuer12\",\"issuerId\":\"issuerId12\",\"owner\":\"owner12\",\"ownerId\":\"ownerId12\",\"assetMetaData\":{\"assetType\":\"INVOICE\",\"basicAssetInfo\":[{\"invoiceNo\":\"InvoiceNo1\",\"invoiceAmt\":4000.0,\"status\":\"ISSUED\",\"endDate\":\"2019-08-19T12:13:123\",\"remark\":\"\"},{\"invoiceNo\":\"InvoiceNo2\",\"invoiceAmt\":6000.0,\"status\":\"ISSUED\",\"endDate\":\"2019-08-19T12:13:123\",\"remark\":\"\"}],\"maxFundRatio\":0.8,\"repayMethod\":\"repayMethod\",\"termsCount\":12,\"duration\":12,\"durationUnit\":\"M\"},\"otherInfo\":\"assetInfo1233\",\"hash\":\"aaedb55ce0ceec435362c6edd845c0ba\",\"encryptedFields\":\"assetName|assetMetaData.basicAssetInfo.invoiceNo\",\"issueTime\":\"2018-11-06T08:36:16\",\"dataType\":\"issue\"}",
    "encryptedFields": "assetName|assetMetaData.basicAssetInfo.invoiceNo"
    }
}
```

### 2207 查询上链资产 <a id="queryAssetInfoById"></a>
* URL  

```
GET /chain/api/data/asset/queryAssetInfoById/{assetId}
X-CP-Token: Bearer {accessToken}
```

* 请求报文

| 字段名  | 类型   | 必填 | 说明         |
| :------ | :----- | :--- | :----------- |
| assetId | string | 是   | 资产唯一编号 |

* 返回报文

返回字段含义同[7.1资产发布的请求报文](#assetIssue)

* 报文示例

```json
{
  "success":true,
  "code":"CM10000",//错误代码,int
  "errorMessage": null,//错误信息,string
  "data": {//返回内容,json object
    "assetId": "INV0001",
    "assetType": "01",
    "upperAssetId": "123",
    "category1": "01",
    "category2": "02",
    "channelCode": "sijib",
    "assetName": "fapiao",
    "status": "02",
    "totalAmt": "10000",
    "startDate": "2018-10-12T12:00:00",
    "endDate": "2018-12-12T12:00:00",
    "issuer": "",
    "issuerId": "101",
    "owner": "",
    "ownerId": "102",
    "period": 2,
    "assetMetaData": {...},
    "hash": "asdfsafdasfsafas",
    "transType": "issue",
    "encryptedFields": "",
    "issueTime": "2018-10-12T12:00:00"
  }
}
```

### 2208 查询上链交易信息  <a id="queryTransInfoById"></a>
* URL  

```
GET /chain/api/data/asset/queryTransInfoById/{opId}
X-CP-Token: Bearer {accessToken}
```

* 请求报文  

| 字段名         | 类型 | 必填    | 说明                     |
| :------------- | :------ | :------ | :-------------------------- |
| opId           | string | 是  | 唯一交易编号                      |

* 返回报文

返回字段含义同[7.3资产交易发布](#assetTransIssue)的请求报文

* 报文示例

```json
{
  "success":true,
  "code":"CM10000",//错误代码,string
  "errorMessage": "",//错误信息,string
  "data": {//返回内容,json object
   "transId":"123213", 
   "transType":"01", 
   "assetId":"AR0001",
   "borrowAmt":"1000", 
   "status":"VALID", 
   "initiator":"test1",
   "initiatorId":"123",
   "counterparty":"test2",
   "counterpartyId":"124", 
   "metaData":{...}, 
   "encryptedFields":"name|idNo|cellphone",
   "transTime":"2018-08-18T12:12:01",
   "opInfo":{  
        "opId":"123213",
    	"opType":"01", 
    	"operator":"user2", 
    	"opTime":"2018-08-18T12:12:01" 
   }
}
}
```

## 8.资产/交易数据检索 <a id="dataSearchService"> </a>

信^+^链提供区块链资产及交易数据的检索及追索，主要包含如下接口：

1. [2301 交易状态变更记录查询](#transStatusHistoryQuery)*
2. [2302 资产流转记录查询](#transTraceQuery)*
3. [2303 资产查询](#assetQuery)*
4. [2304 交易最新状态查询](#transLatestStatusQuery)*

*标注接口需要用户登陆信^+^链后操作。

### 2301 交易状态变更历史查询  <a id="transStatusHistoryQuery"></a>
* URL

```
GET /chain/api/es-query/client/assetOnChain/findTransDetailsByTransId?transId={transId}&productName={productName}
X-CP-Token: Bearer {accessToken}
```

* 请求参数

| 字段名      | 类型   | 必填 | 备注                                         |
| ----------- | ------ | ---- | -------------------------------------------- |
| transId     | string | 是   | 上链交易的唯一标识                           |
| productName | string | 是   | 由具体场景确定， 目前为"ASSET_2_BLOCKCHAIN'" |

* 返回报文

```json
{
"success":true,
"code":"CM10000",
"errorMessage": "",
"data": {
    "type": "transactionDetail",
    "assetTransactionDetails": {
        "transId": "", 
        "transType": "", 
        "assetId": "", 
        "amount": number, 
        "initiator": "", 
        "initiatorId": "", 
        "counterparty": "", 
        "counterpartyId": "",
        "issuer": "" 
    },
    "status_list": [
        {
            "status": "status_1",
            "transTime": "", 
            "blockchainTxDetail": {
                "timestamp": number, 
                "txHash": "", 
                "txIndex": number, 
                "txCount": number, 
                "blockHash": "", 
                "prevBlockHash": "", 
                "nextBlockHash": "", 
                "blockHeight": number, 
                "totalBlocks": number, 
            }
        }
    ]
}
}
```

* 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
|type|string|否|当前数据类型, 这里必须为'transactionDetail'|
|assetTransactionDetails.transId|string|否|交易Id|
|assetTransactionDetails.transType|string|否|交易类型, 具体数据由上传决定|
|assetTransactionDetails.assetId|string|否|交易中资产的Id|
|assetTransactionDetails.initiator|string|是|交易的发起方|
|assetTransactionDetails.initiatorId|string|否|交易的发起方的Id|
|assetTransactionDetails.counterparty|string|是|交易的对手方|
|assetTransactionDetails.counterpartyId|strig|否|交易对手方的Id|
|assetTransactionDetails.issuer|string|是|资产发行方|
|status_list.status|string|否|交易状态, 具体数据由上传决定|
|status_list.transTime|string|否|交易时间, 格式为'yyyy-MM-ddTHH:mm:ss'|
|status_list.blockchainTxDetail.timestamp|string|否|交易提交到区块链的时间, 格式为yyyy-MM-ddTHH:mm:ss|
|status_list.blockchainTxDetail.txHash|string|否|区块链transactionId|
|status_list.blockchainTxDetail.txIndex|integer|否|当前tx在block中的index|
|status_list.blockchainTxDetail.txCount|integer|否|当前block中含有transaction的个数|
|status_list.blockchainTxDetail.blockHash|string|否|当前区块的hash|
|status_list.blockchainTxDetail.prevBlockHash|string|否|前一个区块的hash|
|status_list.blockchainTxDetail.nextBlockHash|string|是|后一个区块的hash|
|status_list.blockchainTxDetail.blockHeight|integer|否|当前区块的block number|
|status_list.blockchainTxDetail.totalBlocks|integer|否|当前区块的block总数|

### 2302 资产流转记录查询（由交易往前追溯） <a id="transTraceQuery"></a>
* URL

```
GET /chain/api/es-query/client/assetOnChain/findTransFlowByTransId?transId={transId}&productName={productName}
X-CP-Token: Bearer {accessToken}
```

* 请求参数

| 字段名      | 类型   | 必填 | 备注                                        |
| ----------- | ------ | ---- | ------------------------------------------- |
| transId     | string | 是   | 上链交易的唯一标识                          |
| productName | string | 是   | 由具体场景确定, 目前为"ASSET_2_BLOCKCHAIN'" |

* 返回报文

```json
{
"success":true,
"code":"CM10000",
"errorMessage": "",
"data": {
    "type": "transactionFlowDetail",
    "transactionFlow": [
        {
            "timestamp": "", 
            "txHash": "", 
            "transType": "", 
            "amount": "", 
            "banlance": number, 
            "assetId": "", 
            "initiator": "",
            "initiatorId": "", 
            "counterparty": "",
            "counterpartyId": ""
        }
      ]
    }
}
}
```

* 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
|type|string|否|当前数据类型, 这里必须为'transactionFlowDetail'|
|transactionFlow.timestamp|string|否|交易或者资产发行的时间, 格式为'yyyy-MM-ddTHH:mm:ss'|
|transactionFlow.txHash|string|否|该业务交易所在区块链transaction的id|
|transactionFlow.transType|string|否|交易类型, 具体数据由上传决定|
|transactionFlow.amount|string|否|交易金额|
|transactionFlow.banlance|integer|是|交易后余额, 可能为空|
|transactionFlow.assetId|string|否|资产Id|
|transactionFlow.initiator|string|是|交易发起方|
|transactionFlow.initiatorId|string|否|交易发起方Id|
|transactionFlow.counterparty|string|是|交易对手方|
|transactionFlow.counterpartyId|string|否|交易对手方Id|


### 2303 资产查询 <a id="assetQuery"></a>
* URL

```
GET /chain/api/es-query/client/assetOnChain/findLatestStatusAssetDetailByAssetId?assetId={assetId}&productName={productName}
X-CP-Token: Bearer {access_token}
```

* 请求参数

| 字段名      | 类型   | 必填 | 备注           |
| ----------- | ------ | ---- | -------------- |
| assetId     | string | 是   | 资产唯一编号   |
| productName | string | 是   | 由具体场景确定, 目前默认为'ASSET_2_BLOCKCHAIN' |

* 返回报文

```json
{
"success":true,
"code":"CM10000",
"errorMessage": "",
"data": {
    "payload": {
        "assetId": "",
        "assetType": "",
        "upperAssetId": "",
        "category1": "",
        "category2": "",
        "channelCode": "",
        "assetName": "",
        "status": "",
        "totalAmt": "",
        "startDate": "",
        "endDate": "",
        "issuer": "",
        "issuerId": "",
        "owner": "",
        "ownerId": "",
        "period": "",
        "metaData": "",
        "hash": "",
        "encryptedFields": "",
        "issueTime": ""
    },
    "authorized": [
        {
        "authorizer": "user1",
        "authorizerId": "000001",
        "authorizedUser": "user2",
        "authorizedUserId": "000002",
        "authorizeType": "01",
        "bizId": "", 
        "hash": "hash(rawData)", 
        "expiredDate": ""
        },
    ]
}
}
```

* 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
|payload.assetId|string|否|资产id|
|payload.assetType|string|否|资产类别|
|payload.upperAssetId|string|是|上层资产Id|
|payload.category1|string|是|一级类别|
|payload.category2|string|是|二级类别|
|payload.channelCode|string|是|渠道编号|
|payload.assetName|string|是|资产名称|
|payload.status|string|是|资产状态|
|payload.totalAmt|string|是|金额|
|payload.startDate|string|是|起始日期, 格式'yyyy-MM-ddTHH:mm:ss'|
|payload.endDate|string|是|结束日期, 格式'yyyy-MM-ddTHH:mm:ss'|
|payload.issuer|string|是|资产发行者|
|payload.issuerId|string|否|资产发行者Id|
|payload.owner|string|是|资产持有人|
|payload.ownerId|string|否|资产持有人Id|
|payload.period|string|是|期限|
|payload.metaData|string|是|资产元数据|
|payload.hash|string|否|资产元数据hash|
|payload.encryptedFields|string|是|加密字段列表, 以'|'分割|
|payload.issueTime|string|是|发行时间, 格式'yyyy-MM-ddTHH:mm:ss'|
|authorized.authorizer|string|是|授权人|
|authorized.authorizerId|string|否|授权人Id|
|authorized.authorizedUser|string|是|被授权人|
|authorized.authorizedUserId|string|否|被授权人Id|
|authorized.authorizeType|string|否|授权类型, 01-资产, 02-交易|
|authorized.bizId|string|否|唯一编号, 授权类型为01的时候为资产Id, 授权类型为02的时候为交易Id|
|authorized.hash|string|否|授权数据hash|
|authorized.expiredDate|string|是|授权过期时间, 格式'yyyy-MM-ddTHH:mm:ss'|


### 2304 交易最新状态查询 <a id="transLatestStatusQuery"></a>
* URL

```
GET /chain/api/es-query/client/assetOnChain/findLatestStatusTransDetailByTransId?transId={transId}&productName={productName}
X-CP-Token: Bearer {access_token}
```

* 请求参数

| 字段名      | 类型   | 必填 | 备注                                       |
| ----------- | ------ | ---- | ------------------------------------------ |
| transId     | string | 是   | 交易唯一标识                               |
| productName | string | 是   | 由具体场景确定，目前为"ASSET_2_BLOCKCHAIN" |

* 返回报文

```json
{
"success": true,
"code": "CM10000",
"errorMessage": "",
"data": {
    "payload": {
         "transId": "",
         "transType": "",
         "assetId": "AR0001",
         "amount": "",
         "status": "",
         "initiator": "",
         "initiatorId": "",
         "counterparty": "",
         "counterpartyId": "",
         "metaData": "",
         "encryptedFields": "name|idNo|cellphone",
         "transTime": "2018-08-18T12:12:01",
         "opInfo": {
            "opId": "asdf",
          	"opType": "",
          	"operator": "user2",
          	"opTime": "2018-08-18T12:12:01"
        }
    },
    "authorized": [
        {
        "authorizer": "user1",
        "authorizerId": "000001",
        "authorizedUser": "user2",
        "authorizedUserId": "000002",
        "authorizeType": "01",
        "bizId": "",
        "hash": "hash(rawData)",
        "expiredDate": ""
        }
        ...
    ]
}
}
```

* 字段说明

|字段|类型|可否为null|备注|
|:--|:--|:--|:--|
|payload.transId|string|否|业务交易Id|
|payload.transType|string|否|业务交易类型|
|payload.assetId|string|否|业务交易的资产id|
|payload.amount|string|否|业务交易金额|
|payload.status|string|是|业务交易状态|
|payload.initiator|string|是|交易发起者|
|payload.initiatorId|string|否|交易发起者Id|
|payload.counterparty|string|是|交易对手方|
|payload.counterpartyId|string|否|交易对手方Id|
|payload.metaData|string|是|交易元数据|
|payload.transTime|string|是|交易时间, 格式'yyyy-MM-ddTHH:mm:ss'|
|payload.opInfo.opId|string|是|交易操作Id|
|payload.opInfo.opType|string|是|交易操作类型|
|payload.opInfo.operator|string|是|交易操作者|
|payload.opInfo.opTime|string|是|交易操作时间, 格式'yyyy-MM-ddTHH:mm:ss'|
|authorized.authorizer|string|是|授权人|
|authorized.authorizerId|string|否|授权人Id|
|authorized.authorizedUser|string|是|被授权人|
|authorized.authorizedUserId|string|否|被授权人Id|
|authorized.authorizeType|string|否|授权类型, 01-资产, 02-交易|
|authorized.bizId|string|否|唯一编号, 授权类型为01的时候为资产Id, 授权类型为02的时候为交易Id|
|authorized.hash|string|否|授权数据hash|
|authorized.expiredDate|string|是|授权过期时间, 格式'yyyy-MM-ddTHH:mm:ss'|

## 9. 区块链节点交互

针对区块链信息，信^+^链同时支持与区块链节点的直接交互，主要包括区块链智能合约的调用、智能合约的查询、区块及交易信息的查询，具体如下：

1. [2401 合约调用](#userInvokeChaincode)*
2. [2402 合约查询](#userQueryChaincode)*
3. [2403 查询区块数据](#userQueryBlock)*
4. [2404 查询区块详细信息](#queryBlockDetails)*
5. [2405 查询最新区块信息](#userQueryLatestBlock)*
6. [2406 查询指定交易信息](#userQueryTransaction)*

*标注接口需要用户登陆信^+^链后操作。

### 2401 合约调用 <a id="userInvokeChaincode"> </a>
* URL

```
POST /chain/api/chain/client/chaincode/invoke
X-CP-Token: Bearer {accessToken}
Content-Type: application/json
```

* 请求报文

| 名称     | 类型   | 必填 | 说明     |
| :------- | :----- | :--- | :------- |
| productId | Long | 是 | chaincode绑定的业务产品ID |
| methodName | String | 是  | 需要调用的合约方法名 |
| parameters | Map<String, Object> | 是 | chaincode方法的输入参数 |
| transientMap | Map<String, byte[]> | 否 | 其他不需要上链的参数 |

* 返回报文

| 字段名   | 类型   | 说明              |
| -------- | ------ | ----------------- |
| response | Object | chaincode返回信息 |
| txId     | String | 区块链交易ID      |


### 2402 合约查询 <a id="userQueryChaincode"> </a>

* 请求URL

```
POST /chain/api/chain/client/chaincode/query
X-CP-Token: Bear {accessToken}
Content-Type: application/json
```

* 请求报文

| 名称     | 类型   | 必填 | 说明     |
| :------- | :----- | :--- | :------- |
| productId | Long | 是  | chaincode绑定的业务产品ID |
| methodName | String | 是  | 需要调用的合约方法名 |
| parameters | Map<String, Object> | 是 | chaincode方法的输入参数 |
| transientMap | Map<String, byte[]> | 否 | 其他不需要上链的参数 |

* 返回报文
  
| 字段名       | 类型   | 说明                     |
| :----------- | :----- | :----------------------- |
| data  | Map |                |

* 报文示例

```json
{
"success":true,
"code": "CM10000",
"errorMessage": null,
"data": {
	"peer0.org1.com": {...}
 }                 
}
```



### 2403 查询指定区块信息 <a id="userQueryBlock"> </a>

* 请求URL

```
GET /chain/api/chain/client/block/queryBlockByNumber
X-CP-Token: Bear {accessToken}
Content-Type: application/x-www-form-urlencoded
```

* 请求报文

| 名称     | 类型   | 必填 | 说明     |
| :------- | :----- | :--- | :------- |
| blockNumber | Long | 是   | 区块号 |
| channelId | Long | 是  | 通道ID(中台编号) |
| userId | Long | 是  | 接口调用者ID |

 * 报文示例

```json
{
"success":true,
"code": "CM10000",
"errorMessage": null,
"data": {
	  "height": 100,
    "blockHash": "asfasdfa",
    "previousBlockHash": "qweqwrqrq",
    "transactionCount": 30,
    "transactionInfoList": [
        {
            "transactionId":"adfasfa",
            "blockIndex":10
        }
    ]  
 }                 
}
```
* 字段说明<a id="returnBody21"></a>
  

| 字段名       | 类型   | 说明                     |
| :----------- | :----- | :----------------------- |
| height  | Long | 区块高度                |
| blockHash | string | 区块hash值                 |
| previousBlockHash | string | 前一个区块的hash                |
| transactionCount | int | 区块中打包的交易数                 |
| transactionInfoList | List | 区块中打包的交易信息列表，交易信息结构见[附录E1](#defOfTransactionInfo) |

### 2405 查询最新区块信息  <a id="userQueryLatestBlock"> </a>

* 请求URL
```
GET /chain/api/chain/client/block/queryLatestBlock
Content-Type: application/x-www-form-urlencoded
X-CP-Token: Bear {accessToken}
```
* 请求报文

| 名称     | 类型   | 必填 | 说明     |
| :------- | :----- | :--- | :------- |
| channelId | Long | 是  | 通道ID(中台编号) |
| userId | Long | 是  | 接口调用者ID |

* 返回报文

返回报文格式

### 2406 查询指定交易信息 <a id="userQueryTransaction"> </a>

* 请求URL

```
GET /chain/api/chain/client/transaction/getTransactionById
Content-Type: application/x-www-form-urlencoded
X-CP-Token: Bear {accessToken}
```

* 请求报文

| 名称     | 类型   | 必填 | 说明     |
| :------- | :----- | :--- | :------- |
| txId | String | Y   | 区块链上的交易ID |

* 报文示例

```json
{
"success":true,
"code": "CM10000",
"errorMessage": null,
"data": {
      "transId": "C99D1227FA89", 
      "parentTransId": "FED41FCE8345", 
      "transType": "FUND", 
      "assetId": "31DD13922F10", 
      "amount": 20000, 
      "status": "ISSUEDDDD", 
      "initiator": "initiator1", 
      "initiatorId": "104", 
      "counterparty": "", 
      "counterpartyId": "", 
      "metaData": {
          "assetInfo": null, 
          "investInfo": {
              "investStruct": [
                  {
                      "id": "104", 
                      "name": "test", 
                      "ratio": 1, 
                      "prioLevel": "0", 
                      "remark": ""
                  }
              ], 
              "duration": 370, 
              "durationUnit": "d", 
              "expRevRate": 0.07, 
              "interest": 1419.18
          }, 
          "fundInfo": null, 
          "creditInfo": null, 
          "repaymentPlan": null, 
          "repayInfo": null
      }, 
      "encryptedFields": "initiator|metaData.investInfo.investStruct.name", 
      "transTime": "2018-02-12T00:00:00", 
      "opInfo": {
          "opId": "763A7CD4C0EA9", 
          "opType": "F_AUDIT", 
          "operator": "operator1", 
          "opTime": "2018-11-01T12:51:38"
      }, 
      "dataType": "trade", 
      "remark": "测试数据"
  }              
} 
```

* 字段说明

| 字段名 | 类型 | 说明               |
| :----- | :--- | :----------------- |
| data   | Map  | chaincode 返回信息 |


## 10. 区块链事件订阅
事件订阅服务目前对订阅者不作完全区分，所有订阅者的订阅都会发布在消息总线的同一个队列内。同时，为了防止不同订阅者对同一个队列的消费，不通的订阅者需要位于不同的消费者群（consumer group), 从而实现消息队列对每一个订阅者都会分发所有的消息。下一版本将会对订阅者的订阅行为作更细粒度的区分。主要接口如下：

1. [2501 申请订阅](#addEventHubListening)*
2. [2502 取消订阅](#deleteEventHubListening)*
3. [2503 查询已订阅信道及节点](#querySubscription)*
4. [2504 查询未订阅信道及节点](#queryUnSubscription)*

*标注接口需要用户登陆信^+^链后操作。

### 2501 申请订阅<a id="addEventHubListening"> </a> 
- URL

```json
POST /chain/api/eventhub/manager/add
Content-Type: application/json
X-CP-Token: Bear accessToken
```

- 请求报文

| 字段名 | 类型 | 必填 |可选值|字段说明|
|:---| :---| :---|:---|----|
|channelId|integer|true||需要订阅的信道ID|
|peerName|string|true||需要订阅的节点名|

- 报文示例

```json
{
  "channelId": 0, 
  "PeerName": "testPeer"
}
```

- 返回报文

```json
{
  "success":true,
  "code": "CM10000",
  "errorMessage": "",
  "data": "Add eventhub Successfully"
  }
}
```
### 2502 取消订阅 <a id="deleteEventHubListening"></a> 
* URL

```json
POST /chain/api/eventhub/manager/delete
Content-Type: application/json
X-CP-Token: Bear {accessToken}
```

* 请求报文

| 字段名 | 类型 | 必填 |可选值|字段说明|
|:---| :---| :---|:---|----|
|channelId|integer|true||需要取消订阅的信道ID|
|peerName|string|true||需要取消订阅的节点名|

* 报文示例

```json
{
  "channelId": 0, 
  "PeerName": "testPeer"
}
```

* 返回报文

```json
{
  "success":true,
  "code": "CM10000",
  "errorMessage": "",
  "data": "delete eventhub Successfully"
  }
}
```

### 2503<a id="querySubscription"> </a> 查询已订阅信道及节点
* URL

```json
GET /chain/api/eventhub/manager/query
X-CP-Token: Bear {accessToken}
```

* 返回报文

```json
{
  "success":true,
  "code": "CM10000",
  "errorMessage": "",
  "data": {
    "key": {
    		"testChannel": ["peer0", "peer1"],
    		"testChannel2": ["peer2", "peer3"]
    	}
     }
}
```
* 字段说明


| 字段名       | 类型   | 说明                     |
| :----------- | :----- | :----------------------- |
| key  | Map |                |

### 2504<a id="queryUnSubscription"> </a> 查询未订阅信道及节点

* URL

```json
GET /chain/api/eventhub/manager/queryUnListenChannels
X-CP-Token: Bear {accessToken}
```

* 返回报文

```json
{
  "success":true,
  "code": "CM10000",
  "errorMessage": "",
  "data": {
    "key": {
    		"testChannel": ["peer0", "peer1"],
    		"testChannel2": ["peer2", "peer3"]
    	}
     }
}
```
* 字段说明


| 字段名       | 类型   | 说明                     |
| :----------- | :----- | :----------------------- |
| key  | Map |                |



## 附录


### B1 类别码表（一级资产类别） <a id="Category1"></a>
| 代码         | 说明                                             |
| :-----------| :---------------------------------------------- |
| 10000       | 供应链金融					                        |
| 20000       | 消费金融				                               |
| 30000       | 中小微金融				                            |
### B2 类别码表（二级资产类别） <a id="Category2"></a>
| 代码         | 说明                                             |
| :-----------| :---------------------------------------------- |
| 30001       | 电商贷					                        |
| 30002       | 运单贷				                               |
| 30003       | 商超贷				                            |
### C 资产相关码表
#### C1 资产类型码表 <a id="listOfAssetType"></a>
| 代码         | 说明                                         |
| :-----------| :--------------------------------------------|
| INVOICE | 发票 |
| LOAN    | 借款	|

#### C2 资产类别码表 <a id="listOfAssetMode"></a>
| 代码         | 说明                                         |
| :-----------| :--------------------------------------------|
| ASSET | 已形成债权 |
| LOAN    | 未形成债权	|

#### C3 资产状态码表 <a id="listOfAssetStatus"></a>
| 代码         | 说明                                         |
| :-----------| :--------------------------------------------|
| SUBMITTED   | 已提交			                               |
| LOANED      | 还款中				                            |
| OVER_DUE    | 已逾期				                            |
| SETTLED     | 已结清                                        |
| INVALID     | 已失效                                        |

#### C4 交易类型及状态定义 <a id="listOfTransTypeAndStatus"></a>

| 类型编码         |  类型名称   | 状态定义      |
| :------------- | :--------- | :------------------------ |
| ISSUE | 发布 | CONFIRMING-待对手方确认, SUCCESS-发布成功, REJECTED-对手方拒绝 |
| TRANSFER | 转让 | CONFIRMING-待对手方确认, SUCCESS-发布成功, REJECTED-对手方拒绝 |
| FUND | 融资 | APPLY\_SUBMITTED:申请已提交, CRD\_AUDITTING:待征信, CRD\_APPROVED:征信通过, CRD\_REJECTED:征信拒绝, CRD\_ENHANCING:待增信, CRD\_NO\_ENHANCE:不增信, CRD\_ENHANCED:已增信, PREAUDITTING:待初筛, PREAUDIT\_APPROVED:初筛通过, PREAUDIT\_REJECTED:初筛拒绝, AUDITING: 待审核, AUDIT\_APPROVED:审核通过, AUDIT\_REJECTED:审核拒绝, LOANED:放款成功|
| REFUND | 再融资 | 同融资 |
| REPAY | 还款 | SUCCESS:还款成功, FAIL:还款失败 |
| UPLOAD_PLAN| 上传还款计划 | SUCCESS/FAIL |

#### C5 操作类型定义（opType）<a id="listOfOpType"></a>
| 编码         |  说明|
| :------------- | :------------------------ |
| ISSUE | 资产发布|
| FUNDING | 申请融资 |
| CRD_AUDIT | 征信审核 |
| P_AUTID | 平台审核 |
| CRD_ENHANCE | 增信 |
| F_AUDIT | 资金方审核 |
| ASSET_TRANSFER | 申请资产转让 |
| TRANSFER_COMFIRM | 转让确认 |
| REPAY | 发起还款 |

#### C6 投资优先级定义(PrioLevel) <a id="listOfPrioLevel"></a>
| 编码         |  说明|
| :------------- | :------------------------ |
| 0 | 优先 |
| 1 | 次级 |
| 2 | 劣后 |

#### C7 增信抵质押类型(Type) <a id="listOfCreditEnhanceInfoType"></a>
| 编码         |  说明|
| :------------- | :------------------------ |
| MORTGAGE | 抵押 |
| PLEDGE | 质押 |
| CREDIT_ENHANCE | 增信 |
| GUARANTEE | 担保 |

#### C8 借款人类型定义 <a id="listOfBorrowerType"></a>
| 编码         |  说明|
| :------------- | :------------------------ |
| BUSINESS | 公司 |
| PERSONAL | 个人 |

#### C9 借款状态定义 <a id="listOfLoanStatus"></a>
| 编码         |  说明|
| :------------- | :------------------------ |
| ISSUED | 待还款 |
| OVERDUE | 已逾期 |
| PAID | 已还款 |

#### C10 性别定义 <a id="listOfGender"></a>
| 编码         |  说明|
| :------------- | :------------------------ |
| MALE | 男 |
| FEMALE | 女 |

#### C11 借款期限定义 <a id="listOfPeriodUnit"></a>
| 编码         |  说明|
| :------------- | :------------------------ |
| D | 天    |
| W | 周    |
| M | 月 |
| Y | 年 |

#### C12 证件类型定义 <a id="listOfIdCardType"></a>
| 编码         |  说明|
| :------------- | :------------------------ |
| ID | 身份证    |
| PASSPORT | 护照  |


#### C13 婚姻状况定义 <a id="listOfMaritalStatus"></a>
| 编码         |  说明|
| :------------- | :------------------------ |
| UNMARRIED | 未婚    |
| MARRIED | 已婚   |
| DEVORCED | 离婚 |
| UNKNOW | 未知 |
### D 数据结构定义
#### D1 AssetMetaData java 定义如下：<a id="defOfAssetMetaData"></a>

  ```java
  public class AssetMetaData {

	  /**
	   * 资产类别
	   *
	   * @see AssetTypeEnums
	   */
	  private String assetType;
	
	  /**
	   * 底层资产信息，包括借款（BorrowInfo）和发票(Invoice)
	   */
	  private List<Object> basicAssetInfo;
	
	  /**
	   * 最高融资比例
	   */
	  private BigDecimal maxFundRatio;
	
	  /**
	   * 投资结构
	   */
	  private List<InvestStruct> investStructInfo;
	
	  /**
	   * 还款方式
	   */
	  private String repayMethod;
	
	  /**
	   * 期数
	   */
	  private Integer termsCount;
	
	  /**
	   * 期限
	   */
	  private Integer duration;
	
	  /**
	  private String durationUnit;
	}
	
	
	public class BorrowInfo{

	  /**
	   * 借款编号
	   */
	  private String borrowNo;

	  /**
	   * 借款人类型
	   *
	   * @see BorrowerTypeEnums
	   */
	  private String borrowerType;
	
	  /**
	   * 借款人信息
	   */
	  private Borrower borrowerInfo;
	
	  /**
	   * 借款金额
	   */
	  private BigDecimal borrowAmt;
	
	  /**
	   * 期限
	   */
	  private Integer duration;
	
	  /**
	   * 期限单位
	   *
	   * @see PeriodUnitEnums
	   */
	  private String durationUnit;
	
	  /**
	   * 还款方式
	   */
	  private String repayMethod;
	
	  /**
	   * 借款协议
	   */
	  private String contractNo;
	
	  /**
	   * 附件信息
	   */
	  private List<String> attachments;
	
	  /**
	   * 借款目的
	   */
	  private String purpose;
	
	  /**
	   * 备注
	   */
	  private String remark;
	
	  /**
	   * 借款状态
	   *
	   * @see LoanStatusEnums
	   */
	  private String loanStatus;
	
	  /**
	   * 担保、抵质押信息
	   */
	  private List<CreditEnhanceInfo> creditEnhanceInfos;
	}
	
	
	public class Invoice {

	  /**
	   * 发票编号
	   */
	  private String invoiceNo;
	
	  /**
	   * 发票金额
	   */
	  private BigDecimal invoiceAmt;
	
	  /**
	   * 发票状态
	   *
	   * @see LoanStatusEnums
	   */
	  private String status;
	
	  /**
	   * 到期⽇期
	   */
	  private String endDate;
	
	  /**
	   * 备注
	   */
	  private String remark;
	}
	
  ```

#### D2 TransMetaData java类定义<a id="defOfTransMetaData"></a>

```java
public class TransMetaData {
  
  private Asset assetInfo;

  private InvestMetaData investInfo;

  private FundMetaData fundInfo;

  private CreditEnhanceInfo creditInfo;

  private List<RepaymentPlanItem> repaymentPlan;

  private RepaymentRecord repayInfo;
}

/**
 * 投资结构信息
 */
public class InvestMetaData {

  /**
   * 投资结构
   */
  private InvestStruct[] investStruct;

  /**
   * 投资期限
   */
  private Integer duration;

  /**
   * 期限单位
   *
   * @see PeriodUnitEnums
   */
  private String durationUnit;

  /**
   * 预期收益率
   */
  private BigDecimal expRevRate;

  /**
   * 应收利息
   */
  private BigDecimal interest;
}

/**
 * 融资信息
 */
public class FundMetaData {

  /**
   * 融资期限
   */
  private Integer duration;

  /**
   * 期限单位
   *
   * @see PeriodUnitEnums
   */
  private String durationUnit;

  /**
   * 融资利率
   */
  private BigDecimal intRate;

  /**
   * 预计到账金额
   */
  private BigDecimal estiAmt;

  /**
   * 还款方式
   */
  private String repayMethod;

  /**
   * 总期数
   */
  private Integer termsCount;

  /**
   * 融资目的
   */
  private String fundPurpose;

  private String otherInfo;
}


/** 
 * 增信信息
 */
public class CreditEnhanceInfo {

  /**
   * @see CreditEnhanceInfoTypeEnums
   */
  private String type;

  /**
   * 抵质押物品名称
   */
  private String name;

  /**
   * 抵质押物品信息，内容由上传方定义
   */
  private String articleInfo;

  /**
   * 抵质押物编号/增信函编号
   */
  private String serialNum;

  /**
   * 担保增信或抵质押文件，文件保存路径
   */
  private List<String> creditFiles;

  /**
   * 增信方
   */
  private String creditSupplier;

  /**
   * 增信方Id
   */
  private String creditSupplierId;
}


/**
 * 单条还款计划
 */
public class RepaymentPlanItem {

  /**
   * 融资ID
   */
  private String transId;

  /**
   * 期数
   */
  private Integer termIndex;

  /**
   * 应还本金
   */
  private BigDecimal principal;

  /**
   * 应还利息
   */
  private BigDecimal interest;

  /**
   * 应还罚息
   */
  private BigDecimal penalty;

  /**
   * 其他费用
   */
  private BigDecimal fee;

  /**
   * 应还总额
   */
  private BigDecimal dueAmt;

  /**
   * 已还金额
   */
  private BigDecimal paidAmt;

  /**
   * 到期日
   */
  private String dueDate;

  /**
   * 逾期天数
   */
  private Integer overDueDays;
}

/**
 * 还款记录信息
 */
public class RepaymentRecord {

  /**
   * 融资ID
   */
  private String transId;

  private String OpId;

  /**
   * 资产ID
   */
  private String assetId;

  /**
   * 金额
   */
  private BigDecimal repayAmt;

  /**
   * 还款日期
   */
  private String repayDate;

  /**
   * 还款人
   */
  private String initiator;

  /**
   * 还款人Id
   */
  private String initiatorId;
}

```

#### D3 OpInfo java类定义<a id="defOfOpInfo"></a>

```java
public class OpInfo {
    /**
     * 操作ID
     */
    private String opId;

    /**
     * 操作类型
     */
    private String opType;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private String opTime;
}
```

*opType定义见[附录C5](#listOfOpType)*

### E 数据结构定义

#### E1 TransactionInfo类定义<a id="defOfTransactionInfo"></a>

```java
public class TransactionInfo {
    private String transactionId;
    private int blockIndex;
}
```

#### E2 BlockchainTxDetail类定义<a id="defOfBlockchainTxDetail"></a>

```java
public class BlockchainTxDetail {
    private Long channelId;
    private String channelName;
    private String txId;
    private long epoch;
    private Date timeStamp;
    private boolean valid;
    private int transactionActionInfoCount;
    private List<TransactionActionInfo> transactionActionInfoList;
    private String previousHash;
    private String blockHash;
    private long height;
    private long transactionCount;
    private long transactionIndex;
}
```
