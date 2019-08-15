package com.csci.cloud.core.client.mirror.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 个人信用评分.
 * @author ben
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder
public class CreditScoreReqVo {

  /**
   * 身份证号.
   */
  @JsonAlias(value = {"uid", "idNo"})
  @JsonProperty(value = "uid")
  private String idNo;


  /**
   * 姓名.
   */
  private String name;

  /**
   * 电话.
   */
  private String mobile;


}
