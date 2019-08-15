package com.csci.cloud.core.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ErrorEnums {

  public interface ErrorCode {

    String getCode();

    String getDesc();
  }

  /**
   * 通用错误码. CM10000 开始
   */
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Getter
  @ToString
  public enum CommonErrorEnum implements ErrorCode {
    SUCCESS("CM10000", "请求成功"),
    INVALID_REQ_PARAMETER("CM10001", "请求参数无效"),
    OPERATOR_ERROR("CM10002", "操作失败."),
    DATE_FORMAT_ERROR("CM10003", "日期格式错误.yyyy-MM-dd"),
    INTERNAL_SERVER_ERROR("CM10004", "服务器异常,请稍后再试，如有疑问请联系客服"),
    HEADER_AUTH_ERROR("CM10005", "请求头必选包含apiKey,timestamp,sign信息 "),
    UNKNOWN_ERROR("CM19999", "未知的错误");

    private String code;
    private String desc;
  }


  /**
   * 云镜错误码匹配. MI30000 开始.
   */
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Getter
  @ToString
  public enum MirrorErrorEnum implements ErrorCode {
    SUCCESS("MI30000", "正常", "0"),
    INVALID_REQ_PARAMETER("MI30001", "参数不正确", "1003"),
    SIGN_VALID_ERROR("MI30002", "云镜验证签名不合法", "100003"),
    SIGN_EXPIRED("MI30003", "云镜:签名过期", "10004"),
    ACCESS_DENY("MI30004", "无权访问该服务", "10005"),
    SCORING_FAIL("MI30005", "评分失败", "-1"),
    SCORING_FAIL_2("MI30005", "评分失败", "-100"),//-1或者-100 都是表示评分失败.
    SERVER_CRASH("MI39998", "Hystrix.个人征信(cloud-mirror)服务器异常,请稍后再试", "MI39998"),
    UNKNOWN_ERROR("MI39999", "未知的错误", "unknown");

    private String code;
    private String desc;
    private String sourceCode;

    /**
     * 根据原始服务的错误码获取映射错误码.
     * @param sourceCode .
     * @return
     */
    public static MirrorErrorEnum getBySourceCode(String sourceCode) {
      for (MirrorErrorEnum mirrorErrorEnum : MirrorErrorEnum.values()) {
        if (mirrorErrorEnum.sourceCode.equalsIgnoreCase(sourceCode)) {
          return mirrorErrorEnum;
        }
      }
      return UNKNOWN_ERROR;
    }
  }

  /**
   * AU20001开始.
   */
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Getter
  @ToString
  public enum AuthErrorEnum implements ErrorCode {
    AUTH_SIGNATURE_ERROR("AU20001", "验证签名不通过."),
    PERMISSION_DENY("AU20002", "权限校验不通过."),
    REQUEST_TIME_OUT("AU20003","请求已经过期");

    private String code;
    private String desc;
  }


  /**
   * CP30001开始.
   */
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Getter
  @ToString
  public enum CredioPushErrorEnum implements ErrorCode {

    ASSET_ALREADY_ON_CHAIN_ERROR("CP30001", "资产已经存在链上.", "10001"),
    ASSET_ALREADY_NOT_EXIST_ERROR("CP30002", "资产不存在链上.", "10002"),
    DATA_CACHE_ERROR("CP30003", "数缓存失败.", "10003"),
    ASSET_TRAN_EXIST_CHAIN_ERROR("CP30004", "资产交易已经存在链上.", "10004"),
    CREDIO_INNER_SERVER_ERROR("CP30005", "credio plush:服务器内部错误.", "400"),
    ROLE_NOT_EXIST("CP30006","角色不存在","402"),
    USER_RULE_NOT_EXIST("CP30007","用户角色不存在","403"),
    PASSWORD_NOT_CORRECT("CP30008","授权中心验证密码不正确","404"),
    USER_RULE_INFO_NOT_EXIST("CP30009","用户角色信息不存在","406"),
    CREDIO_INNER_SERVER_ERROR_2("CP30010", "credio plush:用户中心内部错误.", "500"),
    CREDIO_ES_SERVER_ERROR("CP30010", "credio plush:搜索服务错误,请稍后再试.", "1200"),
    USER_NOT_EXIST("CP30011","⽤户中⼼无法找到该用户","502"),
    USER_NOT_ACTIVE("CP30012","用户未激活","503"),
    USER_PASSWORD_NOT_CORRECT("CP30013","用户中心验证密码不正确","504"),
    AUTH_CENTER_USER_NOT_EXIST("CP30014","授权中心⽆法找到该用户","405"),
    PASSWORD_NOT_SAME("CP30015","两次输⼊密码不一样","505"),
    APP_NOT_EXIST("CP30016","app已存在","506"),
    USER_NOT_IN_CHAIN("CP30017","用户不在该区块链组织中","507"),
    CHAIN_ORG_ALREADY_EXIST("CP30018","区块链组织已存在","508"),
    USER_ORG_EXIST("CP30019","用户企业已存在","509"),
    USER_ORG_NOT_EXIST("CP30020","用户企业不存在","510"),
    USER_ORG_NOT_MAPPING_ORG("CP30021","⽤户所在企业的区块链组织不匹配，⽆法加入","511"),
    USER_NOT_IN_SAME_CHAIN("CP30022","⽤户与查询对象不在同一个区块链组织","512"),
    ORG_TYPE_NOT_EXIST("CP30023","企业类型信息不存在","513"),
    USER_IS_CANCELLED("CP30024","⽤户已经被注销","514"),
    OP_USER_AND_CANCELLED_NOT_IN_SAME_CHAIN("CP30025","操作⽤户与被注销用户不属于同⼀个区块链组织","515"),
    CANT_NOT_CANCELLED_CURRENT_USER("CP30026","不能注销当前登录用户","516"),
    USER_ALREADY_ACTIVE("CP30027","⽤户已经为激活状态","517"),
    ORG_CHAIN_NOT_EXIST("CP30028","区块链组织不存在","518"),
    ADD_EVENT_HUB_ERROR("CP30029","添加订阅失败, 未找到相应区块链节点","600"),
    CAN_NOT_ADD_EVENT_HUB("CP30030","订阅⽆法添加，可能已经存在","601"),
    CAN_NOT_CANCEL_EVENT_HUB("CP30031","订阅无法取消,可能不存在","602"),
    CAN_NOT_GET_CHANNEL_ID_INFO("CP30032","订阅服务无法获取相应信道ID信息","603"),
    CAN_NOT_GET_CHANNEL_INFO("CP30033","订阅服务无法获取相应信道详情","604"),
    CAN_NOT_GET_NODE_CHANNEL_INFO("CP30034","订阅服务无法获取相应信道节点详情","605"),
    QUERY_PARAMETER_ERROR("CP30035","查询输入参数错误","1201"),
    TRANS_RECORD_NOT_FOUND("CP30036","交易记录未找到","20100"),
    TRANS_RECORD_INVALID("CP30037","交易记录无效","20101"),
    UNKNOWN_TRANS_TYPE("CP30038","未知交易记录格式","20102"),
    CHAIN_NODE_NOT_EXIST("CP30039","区块链节点不存在","20111"),
    USER_GROUP_NOT_NODE("CP30040","⽤户业务组无节点部署或安装产品智能合约","20206"),
    INVOKE_METHOD_NOT_MAPPING("CP30041","调用方法让与智能合约描述文件不匹配","20301"),
    CONTRACT_FILE_DESC_INVALID("CP30042","智能合约描述文件无效","20302"),
    QUERY_BLOCK_CHAIN_EMPTY("CP30043","查询区块链结果为空","20303"),
    PARAMETER_VALID_ERROR("CP30044","参数校验失败","99998"),
    PRODUCT_NOT_EXIST("CP30045","产品不存在","20202"),
    INNER_SERVER_ERROR("CP39998","credio plush:服务器内部错误","99999"),
    UNKNOWN_ERROR("CP39999", "未知的错误", "unknown");

    private String code;
    private String desc;

    private String sourceCode;

    /**
     * 根据原始服务的错误码获取映射错误码.
     * @param sourceCode .
     * @return
     */
    public static CredioPushErrorEnum getBySourceCode(String sourceCode) {
      for (CredioPushErrorEnum credioPushErrorEnum : CredioPushErrorEnum.values()) {
        if (credioPushErrorEnum.sourceCode.equalsIgnoreCase(sourceCode)) {
          return credioPushErrorEnum;
        }
      }
      return UNKNOWN_ERROR;
    }
  }

  /**
   * 企业征信相关错误码匹配. ME40000 开始.
   */
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Getter
  @ToString
  public enum MercuryErrorEnum implements ErrorCode {
    SUCCESS("ME40000", "正常", "200"),
    INVALID_REQ_PARAMETER("ME40001", "参数不正确", "400"),
    CUSTOM_NOT_EXIST("ME40002", "客户不存在", "402"),
    RESOURCE_NOT_FOUND("ME40004", "资源无法找到", "404"),
    PRODUCT_NOT_EXIST("ME40005", "无对应产品", "405"),
    REQUEST_EXPIRED("ME40008", "该请求已经过期", "408"),
    INTERNAL_ERROR("ME40009", "内部服务错误", "500"),
    SIGN_NOT_CORRECT("ME40010", "服务错误:签名不正确", "401"),
    SERVER_CRASH("ME49998", "Hystrix,企业征信(cloud-mercury)服务器异常,请稍后再试.", "ME49998"),
    UNKNOWN_ERROR("ME49999", "未知的错误", "unknown");

    private String code;
    private String desc;
    private String sourceCode;

    /**
     * 根据原始服务的错误码获取映射错误码.
     * @param sourceCode .
     * @return
     */
    public static MercuryErrorEnum getBySourceCode(String sourceCode) {
      for (MercuryErrorEnum errorEnum : MercuryErrorEnum.values()) {
        if (errorEnum.sourceCode.equalsIgnoreCase(sourceCode)) {
          return errorEnum;
        }
      }
      return UNKNOWN_ERROR;
    }
  }
}
