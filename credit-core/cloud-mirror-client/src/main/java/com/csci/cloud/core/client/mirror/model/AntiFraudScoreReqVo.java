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
 * 反欺诈评分.
 * @author ben .
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder
public class AntiFraudScoreReqVo {

  @JsonAlias(value = {"id", "idNo"})
  @JsonProperty("id")
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
