package com.csci.cloud.core.client.mirror;

import com.csci.cloud.core.client.mirror.config.CloudMirrorConfig;
import com.csci.cloud.core.client.mirror.model.AntiFraudScoreReqVo;
import com.csci.cloud.core.client.mirror.model.CreditScoreReqVo;
import com.csci.cloud.core.client.mirror.model.MirrorFraud001ReqVo;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ben on 2018/9/25. benkris1@126.com 个人征信业务.
 */
@FeignClient(name = "cloud-mirror", url = "${client.cloud-mirror.url}",
    configuration = CloudMirrorConfig.class, fallbackFactory = CloudMirrorFallBackFactory.class)
public interface CloudMirrorClient {


  /**
   * 反欺诈业务.
   */
  @RequestMapping(value = "/rest/xyy/yj/fraud001", method = RequestMethod.POST)
  @Headers({"Content-Type:application/json"})
  FraudMirrorResponse fraud001(@RequestBody MirrorFraud001ReqVo fraud001ReqVo);


  /**
   * 个人反欺诈评分.
   */
  @RequestMapping(value = "/rest/xyy/fk/fraudpmml", method = RequestMethod.POST)
  @Headers({"Content-Type:application/json"})
  AntiFraudMirrorResponse antiFraudScoring(@RequestBody AntiFraudScoreReqVo reqVo);


  /**
   * 个人信用评分.
   */
  @RequestMapping(value = "/rest/xyy/fk/fkcs", method = RequestMethod.POST)
  @Headers({"Content-Type:application/json"})
  CreditScoringMirrorResponse creditScoring(@RequestBody CreditScoreReqVo reqVo);

}
