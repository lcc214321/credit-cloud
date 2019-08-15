package com.csci.cloud.core.server.web.controller;

import static com.csci.cloud.core.server.utils.ApiDefinesEnum.ANTI_FRAUD_SCORING;
import static com.csci.cloud.core.server.utils.ApiDefinesEnum.CREDIT_SCORING;
import static com.csci.cloud.core.server.utils.ApiDefinesEnum.FRAUD_001;

import com.csci.cloud.core.client.mirror.AntiFraudMirrorResponse;
import com.csci.cloud.core.client.mirror.CloudMirrorClient;
import com.csci.cloud.core.client.mirror.CreditScoringMirrorResponse;
import com.csci.cloud.core.client.mirror.FraudMirrorResponse;
import com.csci.cloud.core.client.mirror.MirrorResponse;
import com.csci.cloud.core.client.mirror.exception.CloudMirrorClientException;
import com.csci.cloud.core.client.mirror.model.AntiFraudScoreReqVo;
import com.csci.cloud.core.client.mirror.model.CreditScoreReqVo;
import com.csci.cloud.core.client.mirror.model.MirrorFraud001ReqVo;
import com.csci.cloud.core.common.ErrorEnums.MirrorErrorEnum;
import com.csci.cloud.core.server.web.annotation.ApiDefine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ben on 2018/9/25. benkris1@126.com 接口编号 10000-1999.
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/v1/mirror", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CloudMirrorController extends AbstractController {

  @Autowired
  private CloudMirrorClient cloudMirrorClient;

  /**
   * 反欺诈业务. 反欺诈规则结果 pass：通过 attention：关注 reject：拒绝
   */
  @RequestMapping(value = "/anti-fraud/001", method = RequestMethod.POST)
  @ApiDefine(value = FRAUD_001)
  public String fraud001(@RequestBody MirrorFraud001ReqVo reqVo) {

    FraudMirrorResponse response = cloudMirrorClient.fraud001(reqVo);
    checkCloudMirrorResponse(response);
    return response.getRuleResult();
  }

  /**
   * 个人反欺诈评分.
   *
   * @param reqVo .
   */
  @RequestMapping(value = "/anti-fraud-scoring", method = RequestMethod.POST)
  @ApiDefine(value = ANTI_FRAUD_SCORING)
  public Number antiFraudScoring(@RequestBody AntiFraudScoreReqVo reqVo) {

    AntiFraudMirrorResponse response = cloudMirrorClient.antiFraudScoring(reqVo);
    checkCloudMirrorResponse(response);
    return response.getScore();
  }

  /**
   * 个人反欺诈评分.
   *
   * @param reqVo .
   */
  @RequestMapping(value = "/credit-scoring", method = RequestMethod.POST)
  @ApiDefine(value = CREDIT_SCORING)
  public Number antiFraudScoring(@RequestBody CreditScoreReqVo reqVo) {

    CreditScoringMirrorResponse response = cloudMirrorClient
        .creditScoring(reqVo);
    checkCloudMirrorResponse(response);
    return response.getFkScore();
  }


  private void checkCloudMirrorResponse(MirrorResponse response) {
    if (!response.getRetCode().equalsIgnoreCase(MirrorErrorEnum.SUCCESS.getSourceCode())) {
      MirrorErrorEnum mirrorErrorEnum = MirrorErrorEnum.getBySourceCode(response.getRetCode());
      throw new CloudMirrorClientException(mirrorErrorEnum);
    }
  }
}
