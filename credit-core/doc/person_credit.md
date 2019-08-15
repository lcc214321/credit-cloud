# 个人征信API使用文档

## 接口列表

| 接口名称                     | URI                                 | 接口编号 |
| ---------------------------- | ----------------------------------- | -------- |
| [个人反欺诈](#4001_info)     | `/api/v1/mirror/anti-fraud/001`     | 4001     |
| [个人反欺诈评分](#4002_info) | `/api/v1/mirror/anti-fraud-scoring` | 4002     |
| [个人信用评分](#4003_info)   | `/api/v1/mirror/credit-scoring`     | 4003     |



## <span id='4001_info'>4001 个人反欺诈</span>

### 接口简介

能够实现对接入客户欺诈风险的监测，识别接入客户中有哪些客户是黑灰名单客户.数据来自中证自有征信数据、自有黑灰名单数据、第三方征信数据等.根据客户学历,职业，信贷逾期，法院失信等规则来鉴定用户反欺诈信息.

### 请求资源URI

`/api/v1/mirror/anti-fraud/001`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### <span id='request_detail'>请求字段明细</span>

| 字段名称          | 类型   | 是否必填 | 字段说明                                                     |
| ----------------- | ------ | -------- | ------------------------------------------------------------ |
| idNo              | String | 是       | 身份证号                                                     |
| name              | String | 是       | 姓名                                                         |
| mobile            | String | 是       | 手机号码                                                     |
| orgName           | String | 否       | 单位名称                                                     |
| orgPhone          | String | 否       | 单位电话                                                     |
| orgLocGps         | String | 否       | 客户单位GPS位置 格式为：精度,维度                            |
| orgLoc3rdGps      | String | 否       | 外部数据（如滴滴）获取单位地址GPS，格式：精度,纬度如114.06128224887138,22.54775263769751. |
| homeLoc3rdGps     | String | 否       | 外部数据（如滴滴）获取家庭地址GPS，格式：精度,纬度如114.06128224887138,22.54775263769751. |
| workCity          | String | 否       | 工作城市 如 “深圳市”.                                        |
| creditLimit       | String | 否       | 授信额度                                                     |
| eduLevel          | String | 否       | 客户学历:博士/硕士/本科/大专/中专/高中/初中/小学/其他.       |
| marriageStatus    | String | 否       | 婚姻状态：未婚/已婚/离异/其他.                               |
| mobileInstallApp  | Number | 否       | 手机安装app数量.                                             |
| appSearchWords    | String | 否       | 客户登录时打开搜索引擎APP，查询高风险类字词.                 |
| mobileRunApp      | String | 否       | 客户同时打开贷款申请APP数量.                                 |
| mobileLanguage    | String | 否       | 客户手机语言 如中文”zh-CN”.                                  |
| mobileIsRoot      | String | 否       | 客户手机是否root。 是:”Y” 否:”N”.                            |
| mobileIsSimulator | String | 否       | 是否为模拟器登录。是:”Y” 否:”N”.                             |
| mobileIsProxyIp   | String | 否       | 客户是否使用代理IP登录。是:”Y” 否:”N”.                       |
| mobileSimCount    | String | 否       | 客户手机sim卡个数。                                          |
| mobileLine1Num    | String | 否       | 客户手机Line1手机号                                          |
| mobileLocGps      | String | 否       | 客户手机GPS，格式：精度,纬度 如114.06128224887138,22.54775263769751. |
| commonUsedLocGps  | String | 否       | 常用GPS，格式：精度,纬度 如114.06128224887138,22.54775263769751. |
| wifiIp            | String | 否       | 客户手机接入的wifi ip地址。如172.168.10.1.                   |
| wifiGateway       | String | 否       | 客户手机接入的wifi的网关地址。如172.168.10.1.                |
| nearWifis         | String | 否       | 附近wifi的名称。多个用逗号“,”隔开。如“tplink001,tplink002”.  |
| wifiSsid          | String | 否       | 客户连接wifi的ssid.                                          |
| wifiBssid         | String | 否       | 客户连接wifi的bssid.                                         |

*注意*：以上参数不能全部为空，查询时按上表中参数顺序进行优先级匹配

### 返回data字段明细

| 字段名 | 类型   | 字段说明                                       |
| ------ | ------ | ---------------------------------------------- |
| data   | String | pass:通过<br />reject:拒绝<br />attention:关注 |

### 请求报文示例

```json
{
  "idNo": "341225198906274952",
  "name": "马华腾",
  "mobile": "15618579663",
  "org_name": "腾通讯"
}
```



### 返回成功报文示例

```json
{
    "code": "CM10000",
    "errorMessage": null,
    "data": "pass",
    "success": true
}
```



## <span id='4002_info'>4002 个人反欺诈评分</span>

### 接口简介

反欺诈模型主要根据客户社交网络关系、历史贷款行为、设备位置等多维数据，通过建立集成的机器学习算法识别客户较为复杂、非线性、隐蔽的欺诈行为。反欺诈模型作为黑灰名单及欺诈规则等识别方法的补充机制，可用以识别客户在申请小额信用贷款时的欺诈风险。该模型具有区分度高、迭代快等特点。信用评分适用于线上小额信用贷款客户欺诈风险识别,使用客户姓名,身份证号码,电话来查询反欺诈评分.

### 请求资源URI

`/api/v1/mirror/anti-fraud-scoring`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### 请求字段明细

| 字段名称 | 类型   | 是否必填 | 字段说明 |
| -------- | ------ | -------- | -------- |
| idNo     | String | 是       | 身份证号 |
| name     | String | 是       | 姓名     |
| mobile   | String | 是       | 手机号   |



### 返回data字段明细

| 字段名 | 类型   | 字段说明                                                     |
| ------ | ------ | ------------------------------------------------------------ |
| data   | Number | 评分结果.   欺诈评分范围为[0,100],分数越大，客户欺诈风险越大 |



### 请求报文示例

```json
{
    "idNo": "3412251989062749360",
    "name": "马化腾",
    "mobile": "13817529999"
}
```



### 返回成功报文示例

```json
{
    "code": "CM10000",
    "errorMessage": null,
    "data": 8,
    "success": true
}
```



## <span id='4003_info'>4003 个人信用评分</span>

### 接口简介

信用风险模型主要根据客户基本信息、还款能力、历史贷款记录等多维数据，通过运用经典逻辑回归的方法建立信用评分卡，以预测客户在未来一段时间内的可能发生逾期的概率。适用于小额信用贷款贷前审批环节，识别客户信用风险以支持审批决策，同时也可作为设置客户额度及利率等贷款定价环节的参考。该模型具有稳定、区分度高、解释能力强等特点。信用评分适用于线上小额信用贷款客户信用风险识别,使用客户姓名,身份证号码,电话来查询评分.

### 请求资源URI

`/api/v1/mirror/credit-scoring`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### 请求字段明细

| 字段名称 | 类型   | 是否必填 | 字段说明 |
| -------- | ------ | -------- | -------- |
| idNo     | String | 是       | 身份证号 |
| name     | String | 是       | 姓名     |
| mobile   | String | 是       | 手机号   |



### 返回data字段明细

| 字段名 | 类型   | 字段说明                                                     |
| ------ | ------ | ------------------------------------------------------------ |
| data   | Number | 评分结果.  信用评分范围为[300,800]，分数越大，客户信用风险越小 |



### 请求报文示例

```json
{
    "idNo": "3412251989062749360",
    "name": "马化腾",
    "mobile": "13817529999"
}
```



### 返回成功报文示例

```json
{
    "code": "CM10000",
    "errorMessage": null,
    "data": 645,
    "success": true
}
```



