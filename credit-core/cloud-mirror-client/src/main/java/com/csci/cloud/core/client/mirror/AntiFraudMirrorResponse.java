package com.csci.cloud.core.client.mirror;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ben on 2018/9/25. benkris1@126.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntiFraudMirrorResponse extends MirrorResponse {

  /**
   * 评分结果.
   */
  private Number score;

  public AntiFraudMirrorResponse(String retCode, String retMsg, Number score) {
    super(retCode, retMsg);
    this.score = score;
  }
}
