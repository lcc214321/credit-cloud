package com.csci.cloud.admin.utils;

import com.csci.cloud.admin.exception.AppBizException;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Calendar;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by ben on 2018/9/11. benkris1@126.com
 */
public class ConfigEnums {

  /**
   * 租户类型.
   */
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Getter
  @ToString
  public enum TenantTypeEnum {
    PERSONAL("个人",0),
    COMPANY("公司",1);
    private String label;
    private Integer value;

    @JsonValue
    public Integer getValue() {
      return value;
    }
  }


  /**
   * 组类型.
   * 默认为普通管理员.
   */
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Getter
  @ToString
  public enum GroupTypeEnum {
    ADMIN("普通管理员",0),
    OTHER("非管理员",1),
    SYS_ADMIN("系统管理员",2);
    private String label;
    private Integer value;

    @JsonValue
    public Integer getValue() {
      return value;
    }
  }


  /**
   * 服务状态.
   */
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Getter
  @ToString
  public enum ServerStatusEnum {
    ENABLE("开启",1),
    CLOSE("关闭",0);
    private String label;
    private Integer value;

    public static ServerStatusEnum fromValue(int value) {
      for(ServerStatusEnum statusEnum:ServerStatusEnum.values()) {
        if(statusEnum.getValue().equals(value)){
          return statusEnum;
        }
      }
      throw new AppBizException(ErrorCode.TENANT_SERVER_STATUS_ERROR);
    }
    @JsonValue
    public Integer getValue() {
      return value;
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Getter
  @ToString
  public enum StatisticsInternalEnum {
    YEAR("1y","yyyy",Calendar.YEAR),
    MONTHLY("1M","yyyy-MM",Calendar.MONTH),
    WEEKLY("1w","xxxx'W'wwe",Calendar.DATE),
    DAILY("1d","yyyy-MM-dd",Calendar.DATE),
    HOURLY("1h","yyyy-MM-dd HH",Calendar.HOUR_OF_DAY),
    MINUTE("1m","yyyy-MM-dd HH:mm",Calendar.MINUTE),
    SECOND("1s","yyyy-MM-dd HH:mm:ss",Calendar.SECOND);

    private String value;
    private String format;
    private int field;

    public static ServerStatusEnum fromValue(String value) {
      for(ServerStatusEnum statusEnum: ServerStatusEnum.values()) {
        if(statusEnum.getValue().equals(value)){
          return statusEnum;
        }
      }
      throw new AppBizException(ErrorCode.TENANT_SERVER_STATUS_ERROR);
    }
    @JsonValue
    public String getValue() {
      return value;
    }
  }

}
