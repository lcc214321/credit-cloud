package com.csci.cloud.core.server.utils;

import com.csci.cloud.core.common.enums.ServiceTypeEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.AntPathMatcher;

@Getter
@ToString
public enum ApiDefinesEnum {

  /**
   * 区块链 接口编号 2000-2999.
   */
  //用户管理
  CREDIO_REGISTER("/chain/api/user/open/createUser", "2001", "区块链用户注册", ServiceTypeEnum.BLOCK_CHAIN,
      true),
  CREDIO_LOGIN("/chain/api/open/login", "2002", "区块链用户登录", ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_LOGIN_OUT("/chain/api/client/user/logout", "2003", "区块链用户退出",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_MODIFY_PASSWORD("/chain/api/user/client/user/updatePassword", "2004", "区块链用户修改密码",
      ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_QUERY_ROLE("/chain/api/user/client/user/queryUserRole", "2005", "查询用户角色",
      ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_UPLOAD_CERT("/chain/api/user/client/user/cert", "POST", "2006", "上传证书",
      ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_UPLOAD_PRV_KEY("/chain/api/user/client/user/privateKey", "POST", "2007", "上传私钥",
      ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_DOWNLOAD_CERT("/chain/api/user/client/user/cert", "GET", "2008", "下载证书",
      ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_DOWNLOAD_PRV_KEY("/chain/api/user/client/user/privateKey", "GET", "2009", "下载私钥",
      ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_GET_USER_COUNT("/chain/api/user/client/user/count", "2010", "获取活跃用户",
      ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_USER_REVOKE("/chain/api/user/admin/user/revoke", "2011", "冻结用户",
      ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_ACTIVATE_USER("/chain/api/user/admin/user/activate", "2012", "激活用户",
      ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_GET_USER_LIST("/chain/api/user/admin/user/list", "2013", "获取用户列表",
      ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_GET_BUSINESS_INFO("/chain/api/user/client/business/user/current", "2014", "用户所属企业详情",
      ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_GET_USER_INFO("/chain/api/user/client/user/current", "2015", "用户详情",
      ServiceTypeEnum.BLOCK_CHAIN, true),
  CREDIO_GET_SMS_CODE("/chain/api/uaa/open/getSmsCode", "2016", "获取验证码",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  //企业信息管理.
  CREDIO_CREATE_BLOCK_CHAIN_AFFILIATION(
      "/chain/api/user/admin/blockchain/createBlockchainAffiliation", "2101", "创建区块链组织信息",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_UPDATE_BLOCK_CHAIN_AFFILIATION("/chain/api/user/admin/blockchain/update", "2101",
      "更新区块链组织信息",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_LIST_BLOCK_CHAIN_AFFILIATION("/chain/api/user/admin/blockchain/businessAffiliationList",
      "2103", "查询企业列列表",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_CREATE_BU("/chain/api/user/admin/business/createBU", "2104", "创建企业信息",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_UPLOAD_LOGO_FILE("/chain/api/user/admin/business/uploadLogoFile", "2105", "上传企业标识",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_BU_USER_LIST("/chain/api/user/admin/business/userList", "2106", "获取企业用户列表",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_GET_LOGO("/chain/api/user/admin/business/getLogo", "2107", "获取企业标识",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_UPDATE_BU("/chain/api/user/admin/business/update", "2108", "更新企业信息",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  //金融资产及交易
  CREDIO_ASSET_ISSUE("/chain/api/data/asset/issue", "2201", "资产发布",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_UPDATE_ISSUE("/chain/api/data/asset/updateStatus", "2202", "资产状态更新",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_UPDATE_TRANS("/chain/api/data/asset/uploadTrans", "2203", "资产交易发布",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_UPDATE_TRANS_STATUS("/chain/api/data/asset/updateTransStatus", "2204", "资产交易状态更新",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_ASSET_AUTH("/chain/api/data/asset/auth", "2205", "数据授权",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_GET_AUTHORIZED_DATA("/chain/api/data/asset/getAuthorizedData", "2206", "授权信息查询",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_ASSET_INFO_QUERY("/chain/api/data/asset/queryAssetInfoById/*", "2207", "查询上链资产",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_QUERY_TRANS_INFO("/chain/api/data/asset/queryTransInfoById/*", "2208", "查询上链交易信息",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  //资产/交易数据检索
  CREDIO_FIND_TRANS_DETAILS("/chain/api/es-query/client/assetOnChain/findTransDetailsByTransId",
      "2331", "交易易状态变更更历史查询",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_FIND_TRANS_FLOW("/chain/api/es-query/client/assetOnChain/findTransFlowByTransId", "2302",
      "资产流转记录查询(由交易易往前追溯)",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_ASSET_SEARCH(
      "/chain/api/es-query/client/assetOnChain/findLatestStatusAssetDetailByAssetId", "2303",
      "资产查询",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_ASSET_LAST_STATUS(
      "/chain/api/es-query/client/assetOnChain/findLatestStatusTransDetailByTransId", "2304",
      "交易易最新状态查询",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  //区块链节点交互
  CREDIO_CHAIN_CODE_INVOKE("/chain/api/chain/client/chaincode/invoke", "2401", "合约调⽤用",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_CHAIN_CODE_QUERY("/chain/api/chain/client/chaincode/query", "2402", "合约查询",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_QUERY_BLOCK_BY_NUMBER("/chain/api/chain/client/block/queryBlockByNumber", "2403", "查询指定区块信息",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_QUERY_BLOCK_DETAIL("/chain/api/chain/client/block/queryBlockDetailByNumber",
      "2404", "查询区块详细信息",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_QUERY_LAST_BLOCK("/chain/api/chain/client/block/queryLatestBlock", "2405",
      "查询最新区块信息",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_GET_TRANSACTION_BY_ID(
      "/chain/api/chain/client/transaction/getTransactionById", "2406",
      "资产查询",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  //时间订阅
  CREDIO_EVENT_HUB_ADD("/chain/api/eventhub/manager/add", "2501", "申请订阅",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_EVENT_HUB_DELETE("/chain/api/eventhub/manager/delete",
      "2502", "取消订阅",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_EVENT_QUERY("/chain/api/eventhub/manager/query", "2503",
      "查询已订阅信道及节点",
      ServiceTypeEnum.BLOCK_CHAIN, true),

  CREDIO_EVENT_QUERY_UN_LISTEN(
      "/chain/api/eventhub/manager/queryUnListenChannels", "2504",
      "资产查询",
      ServiceTypeEnum.BLOCK_CHAIN, true),


  /**
   * 企业征信 3000-3999.
   */
  ADMINISTRATIVE_PENALTY("/api/v1/enterprise/basic/admin-penalty", "3001", "企业行政处罚",
      ServiceTypeEnum.COMPANY_CREDIT, true),
  BOND_DEFAULTS("/api/v1/enterprise/basic/bond-defaults", "3002", "企业债券违约",
      ServiceTypeEnum.COMPANY_CREDIT, true),
  FROZEN_SHARE("/api/v1/enterprise/basic/frozen-share", "3003", "企业股权冻结",
      ServiceTypeEnum.COMPANY_CREDIT, true),
  CREDIT_RATING("/api/v1/enterprise/basic/credit-rating", "3004", "企业主体评级",
      ServiceTypeEnum.COMPANY_CREDIT, true),
  BASE_INFO("/api/v1/enterprise/basic/base-info", "3005", "企业工商基本信息",
      ServiceTypeEnum.COMPANY_CREDIT, true),
  MANAGE_INFO("/api/v1/enterprise/basic/manage-info", "3006", "企业高管信息",
      ServiceTypeEnum.COMPANY_CREDIT, true),
  BUSINESS_CHANGE_RECORD("/api/v1/enterprise/basic/business-change-record", "3007", "企业工商信息变更",
      ServiceTypeEnum.COMPANY_CREDIT, true),
  EQUITY_PLEDGE("/api/v1/enterprise/basic/equity-pledge", "3008", "企业股权出质",
      ServiceTypeEnum.COMPANY_CREDIT, true),
  CHATTEL_MORTGAGE("/api/v1/enterprise/basic/chattel-mortgage", "3009", "企业动产抵押",
      ServiceTypeEnum.COMPANY_CREDIT, true),
  SERIOUS_OFFENCE("/api/v1/enterprise/basic/serious-offence", "3010", "企业严重违法",
      ServiceTypeEnum.COMPANY_CREDIT, true),

  /**
   * 个人征信 4000-4999.
   */
  FRAUD_001("/api/v1/mirror/anti-fraud/001", "4001", "个人反欺诈", ServiceTypeEnum.PERSONAL_CREDIT,
      true),
  ANTI_FRAUD_SCORING("/api/v1/mirror/anti-fraud-scoring", "4002", "个人反欺诈评分",
      ServiceTypeEnum.PERSONAL_CREDIT,
      true),
  CREDIT_SCORING("/api/v1/mirror/credit-scoring", "4003", "个人信用评分", ServiceTypeEnum.PERSONAL_CREDIT,
      true),
  UNKNOWN("", "9999", "未知", null, true);
  private String uri;
  private String httpMethod;
  private String apiId;
  private String apiName;
  private ServiceTypeEnum serverType;
  private boolean trace;

  public static final AntPathMatcher pathMatcher = new AntPathMatcher();

  ApiDefinesEnum(String uri, String httpMethod, String apiId, String apiName,
      ServiceTypeEnum serviceTypeEnum, boolean trace) {
    this.uri = uri;
    this.httpMethod = httpMethod;
    this.apiId = apiId;
    this.apiName = apiName;
    this.serverType = serviceTypeEnum;
    this.trace = trace;
  }

  ApiDefinesEnum(String uri, String apiId, String apiName, ServiceTypeEnum serviceTypeEnum,
      boolean trace) {
    this(uri, null, apiId, apiName, serviceTypeEnum, true);
  }

  public static final List<ApiDefinesEnum> CREDIO_NOT_LOGIN_APS = Lists
      .newArrayList(CREDIO_LOGIN, CREDIO_REGISTER);

  /**
   * 根据uri获取接口定义.
   *
   * @param uri .
   */
  public static ApiDefinesEnum getByUriAndMethod(String uri, String httpMethod) {
    for (ApiDefinesEnum apiDefinesEnum : ApiDefinesEnum.values()) {
      boolean matchResult =
          pathMatcher.isPattern(apiDefinesEnum.uri) ? pathMatcher.match(apiDefinesEnum.uri, uri)
              : apiDefinesEnum.uri.equalsIgnoreCase(uri);

      if (matchResult) {
        if (null == httpMethod || null == apiDefinesEnum.httpMethod) {
          return apiDefinesEnum;
        } else if (apiDefinesEnum.httpMethod.equalsIgnoreCase(httpMethod)) {
          return apiDefinesEnum;
        }
      }
    }
    return UNKNOWN;
  }
}
