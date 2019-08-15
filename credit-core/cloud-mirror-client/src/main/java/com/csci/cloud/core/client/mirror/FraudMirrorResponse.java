package com.csci.cloud.core.client.mirror;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * 反欺诈评分结果.
 * Created by ben on 2018/9/25. benkris1@126.com
 */
@Data()
@AllArgsConstructor()
@NoArgsConstructor
public class FraudMirrorResponse extends MirrorResponse {

  private String ruleResult;

  /** 构造函数.
   * @param retCode 错误码.
   * @param retMsg 错误信息.
   * @param ruleResult 返回结果.
   */
  public FraudMirrorResponse(String retCode, String retMsg, String ruleResult) {
    super(retCode, retMsg);
    this.ruleResult = ruleResult;

  }
}
