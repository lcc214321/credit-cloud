package com.csci.cloud.core.common.enums;

/**
 * Created by ben on 2018/9/27. benkris1@126.com
 */

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 服务类型.
 */
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public enum ServiceTypeEnum {
  BLOCK_CHAIN("区块链", 0),
  COMPANY_CREDIT("企业征信", 1),
  PERSONAL_CREDIT("个人征信", 2);
  private String label;
  private Integer value;

  @JsonValue
  public Integer getValue() {
    return value;
  }
}
