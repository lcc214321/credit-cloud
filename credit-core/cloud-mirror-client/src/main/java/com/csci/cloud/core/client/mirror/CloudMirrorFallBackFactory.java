package com.csci.cloud.core.client.mirror;

import com.csci.cloud.core.client.mirror.model.AntiFraudScoreReqVo;
import com.csci.cloud.core.client.mirror.model.CreditScoreReqVo;
import com.csci.cloud.core.client.mirror.model.MirrorFraud001ReqVo;
import com.csci.cloud.core.common.ErrorEnums.MirrorErrorEnum;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 熔断处理.
 */
@Slf4j
public class CloudMirrorFallBackFactory implements FallbackFactory<CloudMirrorClient> {

  @Override
  public CloudMirrorClient create(Throwable cause) {
    if (log.isDebugEnabled()) {
      log.debug(ExceptionUtils.getStackTrace(cause));
    }
    return new CloudMirrorClient() {
      @Override
      public FraudMirrorResponse fraud001(MirrorFraud001ReqVo fraud001ReqVo) {
        return new FraudMirrorResponse(MirrorErrorEnum.SERVER_CRASH.getCode(),
            MirrorErrorEnum.SERVER_CRASH.getDesc(), null);
      }

      @Override
      public AntiFraudMirrorResponse antiFraudScoring(AntiFraudScoreReqVo reqVo) {
        return new AntiFraudMirrorResponse(MirrorErrorEnum.SERVER_CRASH.getCode(),
            MirrorErrorEnum.SERVER_CRASH.getDesc(), null);
      }

      @Override
      public CreditScoringMirrorResponse creditScoring(CreditScoreReqVo reqVo) {
        return new CreditScoringMirrorResponse(MirrorErrorEnum.SERVER_CRASH.getCode(),
            MirrorErrorEnum.SERVER_CRASH.getDesc(), null);
      }
    };
  }
}
