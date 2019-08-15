# 企业征信API使用文档 

## 接口列表

| 接口名称                       | URI                                               | 接口编号 |
| ------------------------------ | ------------------------------------------------- | -------- |
| [企业行政处罚](#3001_info)     | `/api/v1/enterprise/basic/admin-penalty`          | 3001     |
| [企业债券违约](#3002_info)     | `/api/v1/enterprise/basic/bond-defaults`          | 3002     |
| [企业股权冻结](#3003_info)     | `/api/v1/enterprise/basic/frozen-share`           | 3003     |
| [企业主体评级](#3004_info)     | `/api/v1/enterprise/basic/credit-rating`          | 3004     |
| [企业工商信息](#3005_info)     | `/api/v1/enterprise/basic/base-info`              | 3005     |
| [企业高管信息](#3006_info)     | `/api/v1/enterprise/basic/manage-info`            | 3006     |
| [企业工商信息变更](#3007_info) | `/api/v1/enterprise/basic/business-change-record` | 3007     |
| [企业股权出质](#3008_info)     | `/api/v1/enterprise/basic/equity-pledge`          | 3008     |
| [企业动产抵押](#3009_info)     | `/api/v1/enterprise/basic/chattel-mortgage`       | 3009     |
| [企业严重违法](#3010_info)     | `/api/v1/enterprise/basic/serious-offence`        | 3010     |



## <span id='3001_info'>3001 企业行政处罚</span>

### 接口简介

决定日期、决定文书号、处罚类型、行政处罚内容、决定机关、公示日期、行政处罚决定书文号、案由、案件结果、主要违法事实、处罚金额(万元)、处罚依据、处罚执行情况

### 请求资源URI

`/api/v1/enterprise/basic/admin-penalty`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### <span id='request_detail'>请求字段明细</span>

| 字段名称    | 类型   | 是否必填 | 字段说明         |
| ----------- | ------ | -------- | ---------------- |
| companyId   | String | 否       | 中证企业ID       |
| creditCode  | String | 否       | 统一社会信用代码 |
| regNo       | String | 否       | 注册号           |
| orgCode     | String | 否       | 组织机构代码     |
| companyName | String | 否       | 公司名称         |

*注意*：以上参数不能全部为空，查询时按上表中参数顺序进行优先级匹配

### 返回data字段明细

| 字段名 | 类型      | 字段说明                      |
| ------ | --------- | ----------------------------- |
| data   | JsonArray | [处罚信息列表](#penalty_info) |



#### <span id='penalty_info'>处罚信息</span>

| 字段名称     | 类型   | 字段说明                        |
| ------------ | ------ | ------------------------------- |
| adminPenalty | String | 行政处罚内容                    |
| decisionNum  | String | 行政处罚决定文书号              |
| decisionDt   | String | 决定日期.日期格式为`yyyy-MM-dd` |
| decisionGov  | String | 决定机关                        |
| illegalType  | String | 处罚类型                        |
| noticeDt     | String | 公示日期                        |
| updtDt       | String | 更新日期                        |



### 请求报文示例

```json
{
    "companyName":"上海申能星火热电有限责任公司"
}
```



### 返回成功报文示例

```json
{
  "code": "CM10000",
  "errorMessage": null,
  "data": [
    {
      "adminPenalty": "1、罚款肆万玖仟元整；2、责令改正。",
      "decisionNum": "(奉)市监案字(2016)181201530021号",
      "decisionDt": "2016-02-19",
      "decisionGov": "上海市奉贤区市场监督管理局",
      "illegalType": "质量类",
      "noticeDt": "2016-02-19",
      "recordId": "25155008",
      "updtDt": "2019-01-23 23:22:25"
    },
    {
      "adminPenalty": "责令停止违法行为，并处罚款",
      "decisionNum": "",
      "decisionDt": "2017-03-13",
      "decisionGov": "上海市环保局（厅）",
      "illegalType": "对违反大气污染防治管理制度的处罚",
      "noticeDt": "2017-03-13",
      "recordId": "27908606",
      "updtDt": "2019-01-23 23:22:25"
    },
    {
      "adminPenalty": "没收78.14元",
      "decisionNum": "",
      "decisionDt": "2017-11-08",
      "decisionGov": "上海市物价局",
      "illegalType": "价格行政处罚信息",
      "noticeDt": "2017-11-08",
      "recordId": "27907158",
      "updtDt": "2019-01-23 23:22:25"
    },
    {
      "adminPenalty": "没收26.84元",
      "decisionNum": "",
      "decisionDt": "2016-11-10",
      "decisionGov": "上海市物价局",
      "illegalType": "价格行政处罚信息",
      "noticeDt": "2016-11-10",
      "recordId": "27757053",
      "updtDt": "2019-01-23 23:22:25"
    }
  ],
  "success": true
}
```



## <span id='3002_info'>3002 企业债券违约</span>

### 接口简介

发生时间、债券简称、债券规模、还本方式、票面利率、到期日、债券名称、债券代码、债券类型、发行时间、余额、债券规模、发行期限、剩余期限、债券评级、主体评级、上市地点、债券违约列表、发生时间、公告标题、公告ur

### 请求资源URI

`/api/v1/enterprise/basic/bond-defaults`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### <span id='request_detail'>请求字段明细</span>

请参考[企业行政处罚](#request_detail)

### 返回data字段明细

| 字段名 | 类型      | 字段说明                       |
| ------ | --------- | ------------------------------ |
| data   | JsonArray | [债券违约列表](#defaults_info) |



#### <span id='defaults_info'>债券违约</span>

| 字段名称          | 类型      | 字段说明                         |
| ----------------- | --------- | -------------------------------- |
| remainingVol      | Number    | 余额                             |
| period            | String    | 发行期限                         |
| bondSNm           | String    | 债券简称                         |
| mrtyDt            | String    | 到期日.日期格式为`yyyy-MM-dd`    |
| bondNm            | String    | 债券名称                         |
| bondRemainder     | String    | 剩余期限                         |
| bondCd            | String    | 债券代码                         |
| paymentType       | String    | 还本方式                         |
| occurDt           | String    | 发生日期.日期格式为`yyyy-MM-dd`  |
| couponRate        | String    | 票面利率                         |
| bondRating        | String    | 债券评级                         |
| bondViolationList | JsonArray | [公告列表](#bond_violation_info) |
| bondtype          | String    | 债券类型                         |
| compyRating       | String    | 主体评级                         |
| issuePlace        | String    | 上市地点                         |
| issueDt           | String    | 发行时间.日期格式为`yyyy-MM-dd`  |
| issueVol          | String    | 债券规模                         |



#### <span id ='bond_violation_info'>公告信息</span>

| 字段名称    | 类型   | 字段说明                        |
| ----------- | ------ | ------------------------------- |
| noticeTitle | String | 公告                            |
| infoCd      | String | 公告URL                         |
| noticeDt    | String | 公告日期.日期格式为`yyyy-MM-dd` |



### 请求报文示例

```json
{
    "companyName":"萧县建设投资有限责任公司"
}
```



### 返回成功报文示例

```json
{
  "code": "CM10000",
  "errorMessage": null,
  "data": [
    {
      "remainingVol": 90000,
      "period": "7",
      "bondSNm": "16萧县债",
      "mrtyDt": "2023-06-22",
      "bondNm": "16萧县债",
      "bondRemainder": "4.682191780821918",
      "bondCd": "139136",
      "paymentType": "分期还本",
      "occurDt": "2017-04-26",
      "couponRate": "4.85",
      "bondRating": "AAA",
      "bondViolationList": [
        {
          "noticeTitle": "萧县建设投资有限责任公司关于不能按期披露2016年年度报告的风险提示公告",
          "infoCd": "http://www.sse.com.cn/disclosure/bond/announcement/corporate/c/2905414005079194.pdf",
          "noticeDt": "2017-04-26"
        }
      ],
      "bondType": "企业债",
      "compyRating": "AA",
      "issuePlace": "上海证券交易所",
      "issueDt": "2016-06-22",
      "issueVol": "9"
    },
    {
      "remainingVol": 90000,
      "period": "7",
      "bondSNm": "16萧县建投债",
      "mrtyDt": "2023-06-22",
      "bondNm": "16萧县建投债",
      "bondRemainder": "4.682191780821918",
      "bondCd": "1680261",
      "paymentType": "分期还本",
      "occurDt": "2017-04-26",
      "couponRate": "4.85",
      "bondRating": "AAA",
      "bondViolationList": [
        {
          "noticeTitle": "萧县建设投资有限责任公司关于不能按期披露2016年年度报告的风险提示公告",
          "infoCd": "http://www.sse.com.cn/disclosure/bond/announcement/corporate/c/2905414005079194.pdf",
          "noticeDt": "2017-04-26"
        }
      ],
      "bondType": "企业债",
      "compyRating": "AA",
      "issuePlace": "中国银行间市场",
      "issueDt": "2016-06-22",
      "issueVol": "9"
    }
  ],
  "success": true
}
```



## <span id='3003_info'>3003 企业股权冻结</span>

### 接口简介

冻结起始日、解冻日期、执行冻结机构、解冻日期、提前解冻日期、解冻日期、冻结事由、冻结股数、股东名称、执行通知书文号、执行裁定书文号

### 请求资源URI

`/api/v1/enterprise/basic/frozen-share`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### <span id='request_detail'>请求字段明细</span>

请参考[企业行政处罚](#request_detail)

### 返回data字段明细

| 字段名 | 类型      | 字段说明                     |
| ------ | --------- | ---------------------------- |
| data   | JsonArray | [股权冻结列表](#frozen_info) |



#### <span id='frozen_info'>股权冻结列表</span>

| 字段名称      | 类型   | 字段说明                            |
| ------------- | ------ | ----------------------------------- |
| companyId     | String | 企业ID                              |
| frozenInst    | String | 执行冻结机构                        |
| frozenDt      | String | 冻结起始日.日期格式为`yyyy-MM-dd`   |
| preUnfrozenDt | String | 提前解冻日期.日期格式为`yyyy-MM-dd` |
| unfrozenDt    | String | 解冻日期.日期格式为`yyyy-MM-dd`     |
| noticeDt      | String | 公告时间.日期格式为`yyyy-MM-dd`     |
| sharehdNm     | String | 股东名称                            |
| frozenReason  | String | 冻结事由                            |
| frozenNum     | String | 冻结股数                            |



### 请求报文示例

```json
{
    "companyName":"华能国际电力股份有限公司"
}
```



### 返回成功报文示例

```json
{
  "code": "CM10000",
  "errorMessage": null,
  "data": [
    {
      "companyId": null,
      "frozenInst": null,
      "frozenDt": "2004-06-03",
      "preUnfrozenDt": null,
      "unfrozenDt": "2005-06-02",
      "noticeDt": "2004-06-30",
      "sharehdNm": "广东省汕头市电力开发公司",
      "frozenReason": "冻结",
      "frozenNum": "3300"
    }
  ],
  "success": true
}
```



## <span id='3004_info'>3004 企业主体评级</span>

### 接口简介

最新评级、最新评级时间、上次评级、上次评级时间、评级机构、评级类型、评级展望

### 请求资源URI

`/api/v1/enterprise/basic/credit-rating`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### <span id='request_detail'>请求字段明细</span>

请参考[企业行政处罚](#request_detail)

### 返回data字段明细

| 字段名 | 类型      | 字段说明                            |
| ------ | --------- | ----------------------------------- |
| data   | JsonArray | [评级信息列表](#credit_rating_info) |



#### <span id='credit_rating_info'>评级信息列表</span>

| 字段名称             | 类型   | 字段说明                        |
| -------------------- | ------ | ------------------------------- |
| ratingDt             | String | 评级日期.日期格式为`yyyy-MM-dd` |
| rateType             | String | 评级类型.示例:长期债务评级      |
| rating               | String | 信用评级.示例:AA-               |
| rateFwd              | String | 评级展望.示例:稳定              |
| dataSrcType          | String | 评级机构来源类别                |
| compyCreditratingSid | String | 流水号                          |
| noticeDt             | String | 公告日期                        |
| itypeCd              | String | 机构当事人属性                  |
| itype                | String | 机构当事人                      |
| rateTypeId           | String | 信用评级类型                    |
| rateFwdCd            | String | 评级展望类型                    |
| creditOrgId          | String | 评级机构标识符                  |
| dataSrcTypeVal       | String | 评级公司                        |
| dataSrc              | String | 资料来源                        |
| remark               | String | 备注                            |
| updtTime             | String | 更新时间                        |



### 请求报文示例

```json
{
    "companyName":"萧县建设投资有限责任公司"
}
```



### 返回成功报文示例

```json
{
  "code": "CM10000",
  "errorMessage": null,
  "data": [
    {
      "compyCreditratingSid": "4118978",
      "noticeDt": "2016-06-14",
      "itypeCd": "1",
      "itype": "发行人",
      "ratingDt": "2016-06-12",
      "rateTypeId": "2706",
      "rateType": "长期债务评级",
      "rating": "AA-",
      "rateFwd": "稳定",
      "rateFwdCd": "2",
      "creditOrgId": "321038",
      "dataSrcType": "1",
      "dataSrcTypeVal": "评级公司",
      "dataSrc": "2016年萧县建设投资有限责任公司公司债券信用评级报告",
      "remark": null,
      "updtTime": "2016-08-09 12:12:36"
    },
    {
      "compyCreditratingSid": "4172135",
      "noticeDt": "2017-07-26",
      "itypeCd": "1",
      "itype": "发行人",
      "ratingDt": "2017-07-25",
      "rateTypeId": "2706",
      "rateType": "长期债务评级",
      "rating": "AA-",
      "rateFwd": "稳定",
      "rateFwdCd": "2",
      "creditOrgId": "321038",
      "dataSrcType": "1",
      "dataSrcTypeVal": "评级公司",
      "dataSrc": "萧县建设投资有限责任公司主体及“16萧县建投债”2017年度跟踪评级报告",
      "remark": null,
      "updtTime": "2017-07-26 19:47:56"
    },
    {
      "compyCreditratingSid": "4179566",
      "noticeDt": "2018-06-26",
      "itypeCd": "1",
      "itype": "发行人",
      "ratingDt": "2018-06-25",
      "rateTypeId": "2706",
      "rateType": "长期债务评级",
      "rating": "AA",
      "rateFwd": "稳定",
      "rateFwdCd": "2",
      "creditOrgId": "321038",
      "dataSrcType": "1",
      "dataSrcTypeVal": "评级公司",
      "dataSrc": "萧县建设投资有限责任公司主体及“16萧县建投债16萧县债”2018年度跟踪评级报告",
      "remark": null,
      "updtTime": "2018-06-30 21:29:00"
    }
  ],
  "success": true
}
```



## <span id='3005_info'>3005 企业工商基本信息</span>

### 接口简介

企业全称、企业简称、英文全称、曾用名、企业性质、注册资金、实缴资本、法定代表人、成立日期、经营状态、营业期限、注册地址、经营范围、核准日期、登记机关、联系电话、公司网址、是否上市企业、是否发债、是否新三板、是否私募企业、证券代码、证券简称、员工总数、所属行业、实际控制人、统一社会信用代码、组织机构代码、纳税人识别号、工商注册号、公司简介、币种

### 请求资源URI

`/api/v1/enterprise/basic/base-info`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### <span id='request_detail'>请求字段明细</span>

请参考[企业行政处罚](#request_detail)

### 返回data字段明细

| 字段名 | 类型       | 字段说明                    |
| ------ | ---------- | --------------------------- |
| data   | JsonObject | [企业信息](#base_node_info) |



#### <span id='base_node_info'>企业信息</span>

| 字段名称         | 类型   | 字段说明                        |
| ---------------- | ------ | ------------------------------- |
| companyId        | Number | 企业ID                          |
| compyName        | String | 企业全称                        |
| compySnm         | String | 企业简称                        |
| fenNm            | String | 企业英文全称                    |
| otherNm          | String | 曾用名                          |
| legRepresent     | String | 法定代表人                      |
| orgForm          | String | 组织形式                        |
| foundDt          | String | 成立日期                        |
| regCapital       | String | 注册资本                        |
| regAddr          | String | 注册地址                        |
| companyPh        | String | 联系电话                        |
| companyWeb       | String | 公司网址                        |
| businScope       | String | 经营范围                        |
| employNum        | String | 员工总数                        |
| blnumb           | String | 营业执照号码                    |
| ntrnum           | String | 国税登记号码                    |
| orgnum           | String | 组织机构代码                    |
| industry         | String | 所属行业                        |
| actualCapital    | String | 实收注册资本                    |
| regGov           | String | 登记机关                        |
| managementSt     | String | 经营状态                        |
| companyProfile   | String | 公司简介                        |
| managementPeriod | String | 营业期限.日期格式为`yyyy-MM-dd` |
| approvedTime     | String | 核准日期                        |
| creditCd         | String | 统一社会信用代码                |


### 报文示例

```json
{
    "companyName":"中证信用增进股份有限公司"
}
```



### 返回成功报文示例

```json
{
  "code": "CM10000",
  "errorMessage": null,
  "data": {
    "companyId": "239589",
    "companyName": "中证信用增进股份有限公司",
    "companySnm": "",
    "fenNm": "",
    "otherNm": "",
    "legRepresent": "牛冠兴",
    "orgForm": "其他内地企业",
    "foundDt": "2015-05-27 12:00:00",
    "regCapital": "458598.0000",
    "regAddr": "深圳市前海深港合作区前湾一路1号A栋201室入驻深圳市前海商务秘书有限公司",
    "companyPh": "",
    "companyWeb": "",
    "businScope": "各类信用主体及债项产品信用增进;征信业务和信用评级;股权、债券及金融衍生品投资;增信产品的创设与交易;增信基金设立与运营管理;信用受托管理及咨询;其他与信用增进相关的私募投资业务等。",
    "employNum": "",
    "blnumb": "91440300342642396Y",
    "ntrnum": "91440300342642396Y",
    "orgnum": "91440300342642396Y",
    "industry": "非银金融-多元金融-",
    "actualCapital": "",
    "regGov": "",
    "managementPeriod": "",
    "companyProfile": "",
    "managementSt": "成立",
    "approvedTime": "",
    "creditCd": "91440300342642396Y"
  },
  "success": true
}
```



## <span id='3006_info'>3006 企业高管信息</span>

### 接口简介

任职企业名称、姓名、性别、年龄、学历、担任职务、国籍

### 请求资源URI

`/api/v1/enterprise/basic/manage-info`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### <span id='request_detail'>请求字段明细</span>

请参考[企业行政处罚](#request_detail)

### 返回data字段明细

| 字段名 | 类型      | 字段说明                      |
| ------ | --------- | ----------------------------- |
| data   | JsonArray | [高管信息列表](#manager_info) |



#### <span id='manager_info'>高管信息列表</span>

| 字段名称  | 类型   | 字段说明 |
| --------- | ------ | -------- |
| companyId | String | 企业ID   |
| companyNm | String | 企业名称 |
| personId  | String | 自然人Id |
| sex       | String | 性别     |
| birthYear | String | 出生年份 |
| position  | String | 职务     |
| personNm  | String | 姓名     |
| education | String | 学历     |
| updtDt    | String | 更新时间 |
| country   | String | 国籍     |

### 请求报文示例

```json
{
    "companyName":"中国平安"
}
```



### 返回成功报文示例

```json
{
  "code": "CM10000",
  "errorMessage": null,
  "data": [
    {
      "company_id": "363565",
      "company_nm": "中国平安保险(集团)股份有限公司",
      "person_id": "37081105",
      "sex": "",
      "birth_year": "",
      "position": "监事",
      "person_nm": "张王进",
      "education": "",
      "country": "CN",
      "updt_dt": "2019-01-23 23:39:02"
    },
    {
      "company_id": "363565",
      "company_nm": "中国平安保险(集团)股份有限公司",
      "person_id": "37081105",
      "sex": "",
      "birth_year": "",
      "position": "监事",
      "person_nm": "张王进",
      "education": "",
      "country": "CN",
      "updt_dt": "2019-01-23 23:39:02"
    }
   ],
   "success": true
}
```





## <span id ='3007_info'>3007 企业工商信息变更</span>

### 接口简介

变更事项、变更前、变更后、变更日期

### 请求资源URI

`/api/v1/enterprise/basic/business-change-record`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### <span id='request_detail'>请求字段明细</span>

请参考[企业行政处罚](#request_detail)

### 返回data字段明细

| 字段名 | 类型      | 字段说明                              |
| ------ | --------- | ------------------------------------- |
| data   | JsonArray | [工商信息变更记录](#busi_record_info) |



#### <span id='busi_record_info'>工商信息变更记录</span>

| 字段名称      | 类型   | 字段说明                        |
| ------------- | ------ | ------------------------------- |
| companyId     | String | 企业ID                          |
| changeItem    | String | 变更事项                        |
| changeDt      | String | 变更日期 日期格式为`yyyy-MM-dd` |
| contentBefore | String | 变更前                          |
| contentAfter  | String | 变更后                          |
| updtDt        | String | 更新时间                        |

### 请求报文示例

```json
{
    "companyName":"中证信用增进股份有限公司"
}
```



### 返回成功报文示例

```json
{
  "code": "CM10000",
  "errorMessage": null,
  "data": [
    {
      "companyId": "239589",
      "changeItem": "注册资本变更",
      "changeDt": "2015-07-21",
      "contentBefore": "330000",
      "contentAfter": "410000",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "注册资本变更",
      "changeDt": "2015-07-21",
      "contentBefore": "人民币330000.0000 万元",
      "contentAfter": "人民币410000.0000 万元",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "章程变更",
      "changeDt": "2015-07-21",
      "contentBefore": "",
      "contentAfter": "",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "章程变更",
      "changeDt": "2015-12-22",
      "contentBefore": "",
      "contentAfter": "",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2015-12-22",
      "contentBefore": "",
      "contentAfter": "",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2015-12-22",
      "contentBefore": "代永波(监事) 谢勇(董事) 沈国甫(董事) 牛冠兴(总经理) 张建辉(监事) 王晓荷(监事) 李强(董事) 马震亚(监事) 俞仕新(董事) 薛峰(董事) 黄奕林(监事) 张威(董事) 牛冠兴(董事长) 刘珂滨(董事) 李小毅(监事) 牛冠兴(董事) 罗东原(监事) 王颢(董事)",
      "contentAfter": "代永波(监事) 刘珂滨(董事) 王晓荷(监事) 李强(董事) 王颢(董事) 黄奕林(监事) 张建辉(监事) 盛今(监事) 牛冠兴(董事长) 洪磊(监事) 沈国甫(董事) 牛冠兴(总经理) 应丹(职工监事) 丁志雄(职工监事) 马震亚(监事) 彭政纲(监事) 李进安(董事) 张威(董事) 刘龙(董事) 李小毅(监事) 谢勇(董事) 冯辞(董事) 薛峰(董事) 郭贤达(职工监事) 俞仕新(董事) 李国柱(职工监事) 姚煜(职工监事) 罗东原(监事)",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2015-12-22",
      "contentBefore": "李小毅（监事）马震亚（监事）王晓荷（监事）代永波（监事）罗东原（监事）黄奕林（监事）张建辉（监事）",
      "contentAfter": "李国柱（监事会主席）盛今（监事）洪磊（监事）马震亚（监事）彭政纲（监事）王晓荷（监事）黄奕林（监事）应丹（监事会主席）张建辉（监事）丁志雄（监事会主席）李小毅（监事）代永波（监事）郭贤达（监事会主席）罗东原（监事）姚煜（监事会主席）",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2015-12-22",
      "contentBefore": "李小毅（监事）马震亚（监事）王晓荷（监事）张建辉（监事）代永波（监事）黄奕林（监事）罗东原（监事）",
      "contentAfter": "李国柱（监事会主席）盛今（监事）洪磊（监事）马震亚（监事）代永波（监事）彭政纲（监事）王晓荷（监事）郭贤达（监事会主席）黄奕林（监事）罗东原（监事）姚煜（监事会主席）应丹（监事会主席）张建辉（监事）李小毅（监事）丁志雄（监事会主席）",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2015-12-22",
      "contentBefore": "牛冠兴（董事长）",
      "contentAfter": "牛冠兴（董事长）",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "其他事项变更",
      "changeDt": "2016-09-28",
      "contentBefore": "",
      "contentAfter": "*****Y",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "其他变更事项",
      "changeDt": "2016-09-28",
      "contentBefore": "",
      "contentAfter": "",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "注册资本变更",
      "changeDt": "2017-08-28",
      "contentBefore": "410000",
      "contentAfter": "414098",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "注册资本变更",
      "changeDt": "2017-08-28",
      "contentBefore": "410000,156",
      "contentAfter": "414098,156",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "注册资本变更",
      "changeDt": "2017-08-28",
      "contentBefore": "410000人民币",
      "contentAfter": "414098人民币",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "章程变更",
      "changeDt": "2017-08-28",
      "contentBefore": "2015-09-15",
      "contentAfter": "2016-12-15",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2017-08-28",
      "contentBefore": "",
      "contentAfter": "",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2017-08-28",
      "contentBefore": "姚煜（监事会主席）张建辉（监事）王晓荷（监事）马震亚（监事）洪磊（监事）李国柱（监事会主席）应丹（监事会主席）罗东原（监事）李小毅（监事）盛今（监事）黄奕林（监事）代永波（监事）丁志雄（监事会主席）郭贤达（监事会主席）彭政纲（监事）",
      "contentAfter": "杨俊（监事）李国柱（职工监事）代永波（监事）李小毅（监事）丁志雄（职工监事）洪磊（监事）罗东原（监事）王晓荷（职工监事）郭贤达（职工监事）盛今（监事）姚煜（职工监事）应丹（职工监事）彭政纲（监事）张建辉（监事）马震亚（监事）黄奕林（监事）",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2017-08-28",
      "contentBefore": "姚煜（监事会主席）张建辉（监事）王晓荷（监事）马震亚（监事）洪磊（监事）李国柱（监事会主席）应丹（监事会主席）罗东原（监事）盛今（监事）黄奕林（监事）代永波（监事）李小毅（监事）丁志雄（监事会主席）郭贤达（监事会主席）彭政纲（监事）",
      "contentAfter": "杨俊（监事）李国柱（职工监事）代永波（监事）李小毅（监事）丁志雄（职工监事）洪磊（监事）罗东原（监事）王晓荷（职工监事）郭贤达（职工监事）盛今（监事）姚煜（职工监事）应丹（职工监事）彭政纲（监事）张建辉（监事）马震亚（监事）黄奕林（监事）",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2017-08-28",
      "contentBefore": "洪磊（监事）李小毅（监事）黄奕林（监事）姚煜（职工监事）郭贤达（职工监事）应丹（职工监事）李国柱（职工监事）马震亚（监事）罗东原（监事）盛今（监事）代永波（监事）王晓荷（职工监事）张建辉（监事）彭政纲（监事）丁志雄（职工监事）杨俊（监事）",
      "contentAfter": "郭贤达（职工监事）姚煜（职工监事）洪磊（监事）李小毅（监事）黄奕林（监事）杨俊（监事）李国柱（职工监事）代永波（监事）罗东原（监事）应丹（职工监事）马震亚（监事）张建辉（监事）丁志雄（职工监事）彭政纲（监事）王晓荷（职工监事）",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2017-08-28",
      "contentBefore": "洪磊（监事）李小毅（监事）黄奕林（监事）姚煜（职工监事）郭贤达（职工监事）应丹（职工监事）李国柱（职工监事）马震亚（监事）罗东原（监事）盛今（监事）代永波（监事）王晓荷（职工监事）张建辉（监事）杨俊（监事）彭政纲（监事）丁志雄（职工监事）",
      "contentAfter": "郭贤达（职工监事）姚煜（职工监事）洪磊（监事）李小毅（监事）黄奕林（监事）杨俊（监事）李国柱（职工监事）代永波（监事）罗东原（监事）应丹（职工监事）马震亚（监事）张建辉（监事）丁志雄（职工监事）彭政纲（监事）王晓荷（职工监事）",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "注册资本变更",
      "changeDt": "2017-10-31",
      "contentBefore": "414098",
      "contentAfter": "458598",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "注册资本变更",
      "changeDt": "2017-10-31",
      "contentBefore": "414098,156",
      "contentAfter": "458598,156",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "注册资本变更",
      "changeDt": "2017-10-31",
      "contentBefore": "414098人民币",
      "contentAfter": "458598人民币",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "章程变更",
      "changeDt": "2017-10-31",
      "contentBefore": "2016-12-15",
      "contentAfter": "2017-10-28",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "其他变更事项",
      "changeDt": "2018-06-29",
      "contentBefore": "沈国甫（董事）薛峰（董事）李进安（董事）张威（董事）冯辞（董事）谢勇（董事）刘龙（董事）王颢（董事）李强（董事）俞仕新（董事）刘珂滨（董事）",
      "contentAfter": "刘龙（董事）刘珂滨（董事）张威（董事）沈国甫（董事）周健男（董事）张剑文（董事）李强（董事）谢勇（董事）王颢（董事）俞仕新（董事）冯辞（董事）",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "章程变更",
      "changeDt": "2018-06-29",
      "contentBefore": "2017-10-28",
      "contentAfter": "2018-04-16",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2018-06-29",
      "contentBefore": "",
      "contentAfter": "",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2018-06-29",
      "contentBefore": "王晓荷（职工监事）郭贤达（职工监事）代永波（监事）杨俊（监事）洪磊（监事）李国柱（职工监事）应丹（职工监事）李小毅（监事）黄奕林（监事）姚煜（职工监事）马震亚（监事）彭政纲（监事）张建辉（监事）罗东原（监事）丁志雄（职工监事）",
      "contentAfter": "代永波（监事）王晓荷（职工监事）罗东原（监事）张建辉（监事）郭贤达（职工监事）彭政纲（监事）李小毅（监事）杨俊（监事）马震亚（监事）丁志雄（职工监事）胡平生（监事）洪磊（监事）应丹（职工监事）李国柱（职工监事）宋梅（职工监事）",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "其他变更事项",
      "changeDt": "2018-10-26",
      "contentBefore": "沈国甫（董事）张剑文（董事）周健男（董事）俞仕新（董事）冯辞（董事）李强（董事）王颢（董事）刘龙（董事）刘珂滨（董事）张威（董事）谢勇（董事）",
      "contentAfter": "刘珂滨（董事）陈东杰（董事）王颢（董事）张威（董事）周健男（董事）谢勇（董事）魏纯（董事）刘龙（董事）冯辞（董事）",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "章程变更",
      "changeDt": "2018-10-26",
      "contentBefore": "2018-04-16",
      "contentAfter": "2018-09-20",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2018-10-26",
      "contentBefore": "",
      "contentAfter": "",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2018-10-26",
      "contentBefore": "牛冠兴（总经理）",
      "contentAfter": "冯辞（总经理）",
      "updtDt": "2019-01-23 22:21:09"
    },
    {
      "companyId": "239589",
      "changeItem": "高级管理人员变更",
      "changeDt": "2018-10-26",
      "contentBefore": "郭贤达（职工监事）应丹（职工监事）李小毅（监事）代永波（监事）罗东原（监事）宋梅（职工监事）丁志雄（职工监事）胡平生（监事）洪磊（监事）李国柱（职工监事）王晓荷（职工监事）彭政纲（监事）马震亚（监事）杨俊（监事）张建辉（监事）",
      "contentAfter": "李国柱（职工监事）胡平生（监事）王晓荷（职工监事）代永波（监事）李军（监事）沈国甫（监事）张建辉（监事）丁志雄（职工监事）宋梅（职工监事）周晓慧（监事）罗东原（监事）何毅华（监事）王洪阳（监事）应丹（职工监事）洪磊（监事）郭贤达（职工监事）李小毅（监事）彭政纲（监事）",
      "updtDt": "2019-01-23 22:21:09"
    }
  ],
  "success": true
}
```



## <span id ='3008_info'>3008 企业股权出质</span>

### 接口简介

质权人姓名、质权人类别、出质金额(万元)、出质审批部门、出质备案日期、出质批准日期、出质截至日期

### 请求资源URI

`/api/v1/enterprise/basic/equity-pledge`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### <span id='request_detail'>请求字段明细</span>

请参考[企业行政处罚](#request_detail)

### 返回data字段明细

| 字段名 | 类型      | 字段说明                  |
| ------ | --------- | ------------------------- |
| data   | JsonArray | [ 质押列表](#equity_info) |



#### <span id='equity_info'>质押信息</span>

| 字段名称      | 类型   | 字段说明             |
| ------------- | ------ | -------------------- |
| companyId     | String | 企业ID               |
| pledgenum     | String | 出质股权数额（万元） |
| mortgagee     | String | 质权人               |
| mortgageeType | String | 质权人类别           |
| pledgenum     | String | 出质股权数额(万元)   |
| pledgeDt      | String | 股权出质截止日期     |
| regNm         | String | 登记编号             |
| pledgor       | String | 出质人               |
| updtDt        | String | 更新时间             |
| status        | String | 状态                 |

### 请求报文示例

```json
{
    "companyName":"保亭黎族苗族自治县农村信用合作联社"
}
```



### 返回成功报文示例

```json
{
  "code": "CM10000",
  "errorMessage": null,
  "data": [
    {
      "companyId": "41883",
      "mortgagee": "五指山市农村信用合作联社",
      "mortgageeType": "",
      "pledgenum": "11.1384万元",
      "pledgeDt": "2015-06-04",
      "pledgor": "谢海峰",
      "regNm": "A1500337361",
      "status": "无效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "海口农村商业银行股份有限公司",
      "mortgageeType": "",
      "pledgenum": "820.9001万元",
      "pledgeDt": "2015-07-22",
      "pledgor": "海南龙泰农业开发有限公司",
      "regNm": "A1500412774",
      "status": "无效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "临高县农村信用合作联社",
      "mortgageeType": "",
      "pledgenum": "45万元",
      "pledgeDt": "2014-11-14",
      "pledgor": "中青旅海江投资发展有限公司",
      "regNm": "A1400625040",
      "status": "无效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "海口农村商业银行股份有限公司",
      "mortgageeType": "",
      "pledgenum": "44.8万元",
      "pledgeDt": "2014-11-14",
      "pledgor": "广西南宁大坤矿业有限公司",
      "regNm": "A1400625123",
      "status": "无效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "海口农村商业银行股份有限公司",
      "mortgageeType": "",
      "pledgenum": "737万元",
      "pledgeDt": "2014-11-14",
      "pledgor": "广西南宁大坤矿业有限公司",
      "regNm": "A1400625165",
      "status": "无效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "海口农村商业银行股份有限公司",
      "mortgageeType": "",
      "pledgenum": "737万元",
      "pledgeDt": "2014-11-14",
      "pledgor": "海南龙健农业开发有限公司",
      "regNm": "A1400625220",
      "status": "无效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "三亚农村商业银行股份有限公司",
      "mortgageeType": "",
      "pledgenum": "39.965万元",
      "pledgeDt": "2015-10-15",
      "pledgor": "中青旅海江投资发展有限公司",
      "regNm": "Ahn15101500920",
      "status": "有效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "海口农村商业银行股份有限公司",
      "mortgageeType": "",
      "pledgenum": "820.9001万元",
      "pledgeDt": "2015-08-05",
      "pledgor": "海南龙健农业开发有限公司",
      "regNm": "A1500431445",
      "status": "有效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "琼海市农村信用合作联社",
      "mortgageeType": "",
      "pledgenum": "7.771万元",
      "pledgeDt": "2015-10-15",
      "pledgor": "中青旅海江投资发展有限公司",
      "regNm": "Ahn15101501027",
      "status": "有效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "海口农村商业银行股份有限公司",
      "mortgageeType": "",
      "pledgenum": "820.9001万元",
      "pledgeDt": "2016-04-12",
      "pledgor": "广西南宁大坤矿业有限公司",
      "regNm": "Ahn16041201910",
      "status": "有效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "琼海市农村信用合作联社",
      "mortgageeType": "",
      "pledgenum": "390万元",
      "pledgeDt": "2017-01-24",
      "pledgor": "海南龙泰农业开发有限公司",
      "regNm": "469035201700000013",
      "status": "有效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "海南文昌农村商业银行股份有限公司",
      "mortgageeType": "",
      "pledgenum": "430.9001万元",
      "pledgeDt": "2017-01-24",
      "pledgor": "海南龙泰农业开发有限公司",
      "regNm": "469035201700000014",
      "status": "有效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "海口农村商业银行股份有限公司",
      "mortgageeType": "",
      "pledgenum": "82.09万元",
      "pledgeDt": "2017-02-09",
      "pledgor": "广西南宁大坤矿业有限公司",
      "regNm": "469035201700000015",
      "status": "有效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "琼中黎族苗族自治县农村信用合作联社股份有限公司",
      "mortgageeType": "",
      "pledgenum": "82.09万元",
      "pledgeDt": "2017-07-11",
      "pledgor": "海南龙健农业开发有限公司",
      "regNm": "469035201700000019",
      "status": "有效",
      "updtDt": "2019-01-31 09:57:40"
    },
    {
      "companyId": "41883",
      "mortgagee": "五指山市农村信用合作联社",
      "mortgageeType": "",
      "pledgenum": "14.6531万股",
      "pledgeDt": "2017-11-22",
      "pledgor": "庄奇学",
      "regNm": "469035201700000027",
      "status": "有效",
      "updtDt": "2019-01-31 09:57:40"
    }
  ],
  "success": true
}
```



## <span id='3009_info'>3009 企业动产抵押</span>

## 接口简介

抵押人、状态、债务人履行债务期限、债务人履行债务期限、登记日期、证件／证照号码、被担保债权数额、被担保主债权种类、抵押权人名称、登记机关、登记编号、抵押权人证照／证件类型、担保范围、抵押物信息列表、抵押物名称、所有权归属、数量、质量、状况、所在地等情况、备注

#### 请求资源URI

`/api/v1/enterprise/basic/chattel-mortgage`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### <span id='request_detail'>请求字段明细</span>

请参考[企业行政处罚](#request_detail)

### 返回data字段明细

| 字段名 | 类型      | 字段说明                   |
| ------ | --------- | -------------------------- |
| data   | JsonArray | [ 质押列表](#chattel_info) |



#### <span id='chattel_info'>质押信息</span>

| 字段名称         | 类型      | 字段说明                         |
| ---------------- | --------- | -------------------------------- |
| companyId        | String    | 企业ID                           |
| debtAmt          | String    | 被担保债权数额                   |
| debtType         | String    | 被担保主债权种类                 |
| regDt            | String    | 核准日期. 日期格式为`yyyy-MM-dd` |
| pawnInfoList     | JsonArray | [抵押物信息列表](#pawn_info).    |
| mortgagorCertNum | String    | 证件/证照号码                    |
| mortgagee        | String    | 抵押人                           |
| mortgagor        | String    | 抵押权人                         |
| regNm            | String    | 登记编号                         |
| mortgageeType    | String    | 抵押权人证照／证件类型"          |
| regNum           | String    | 登记编号                         |
| scope            | String    | 担保范围                         |
| term             | String    | 债务人履行债务期限               |
| state            | String    | 状态                             |
| regGov           | String    | 登记机关                         |
| updtDt           | String    | 更新时间                         |



#### <span id='pawn_info'>抵押物信息</span>

| 字段名称  | 字段类型 | 字段说明       |
| --------- | -------- | -------------- |
| remark    | String   | 抵押物信息备注 |
| detail    | String   | 抵押物详情     |
| ownership | String   | 抵押物所有人   |
| pawnNm    | String   | 抵押物名称     |



### 请求报文示例

```json
{
    "companyName":"柳州东风容泰化工股份有限公司"
}
```



### 返回成功报文示例

```json
{
  "code": "CM10000",
  "errorMessage": null,
  "data": [
    {
      "companyId": "70285433",
      "debtAmt": "3000万元",
      "debtType": "",
      "regDt": "2013-12-18",
      "pawnInfoList": [],
      "mortgagorCertNum": "非公示项",
      "mortgagee": "广西柳州中小企业信用担保有限公司",
      "mortgagor": "广西柳州中小企业信用担保有限公司",
      "regNm": "柳工商抵登字（2013）13563号",
      "scope": "本金利息费用",
      "term": "",
      "state": "无效",
      "regGov": "柳州市工商行政管理局",
      "mortgageeType": "",
      "updtDt": "2019-01-23 23:30:43"
    }
  ],
  "success": true
}
```





## <span id ='3010_info'>3010 企业严重违法</span>

### 接口简介

列入严重违法失信企业名单原因、列入日期、作出决定机关、移出严重违法名单原因、移出日期、移出作出决定机关、类别、违法事实

### 请求资源URI

`/api/v1/enterprise/basic/serious-offence`

### 请求方法

`POST`

### Content-Type

`application/json;charset=utf-8`

### <span id='request_detail'>请求字段明细</span>

请参考[企业行政处罚](#request_detail)

### 返回data字段明细

| 字段名 | 类型      | 字段说明                   |
| ------ | --------- | -------------------------- |
| data   | JsonArray | [ 违法列表](#offence_info) |



#### <span id='offence_info'>违法信息</span>

| 字段名称       | 类型   | 字段描述                     |
| -------------- | ------ | ---------------------------- |
| seriviolatFact | String | 违法事实                     |
| companyId      | String | 企业Id                       |
| listonGov      | String | 作出决定机关                 |
| listoutGov     | String | 移出作出决定机关             |
| listoutReason  | String | 移出严重违法名单原因         |
| listoutDt      | String | 移出日期                     |
| listonDt       | String | 列入日期                     |
| listonReason   | String | 列入严重违法失信企业名单原因 |
| seriviolatType | String | 类别                         |
| updtDt         | String | 更新时间                     |

### 请求报文示例

```json
{
    "companyName":"倬可儿服饰(深圳)有限公司"
}
```



### 返回成功报文示例

```json
{
  "code": "CM10000",
  "errorMessage": null,
  "data": [
    {
      "companyId": "9700067",
      "seriviolatFact": null,
      "listonGov": "深圳市市场和质量监督管理委员会宝安局",
      "listoutReason": "",
      "listoutDt": null,
      "listonDt": "2018-09-18",
      "listonReason": "被列入经营异常名录届满3年仍未履行相关义务的",
      "seriviolatType": "严重违法失信企业名单",
      "updtDt": "2019-01-23 23:22:25"
    }
  ],
  "success": true
}
```

