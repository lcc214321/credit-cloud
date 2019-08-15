package com.csci.cloud.core.client.mirror;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 个人信用评分结果.
 * Created by ben on 2018/9/25. benkris1@126.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditScoringMirrorResponse extends MirrorResponse {

  /**
   * 评分结果.
   */
  private Number fkScore;

  /**
   * 个人信用评分.
   * @param retCode .
   * @param retMsg .
   * @param fkScore .
   */
  public CreditScoringMirrorResponse(String retCode, String retMsg, Number fkScore) {
    super(retCode, retMsg);
    this.fkScore = fkScore;

  }

}
