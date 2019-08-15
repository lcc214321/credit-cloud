package com.csci.cloud.core.client.mercury;

import static com.csci.cloud.core.client.mercury.CloudMercuryResponse.HYSTRIX_RESPONSE;

import com.csci.cloud.core.client.mercury.model.req.AdminPenaltyReqVo;
import com.csci.cloud.core.client.mercury.model.req.BaseReqVo;
import com.csci.cloud.core.client.mercury.model.req.BondDefaultsReqVo;
import com.csci.cloud.core.client.mercury.model.req.FrozenShareReqVo;
import com.csci.cloud.core.client.mercury.model.resp.AdminPenaltyRespVo;
import com.csci.cloud.core.client.mercury.model.resp.BondDefaultsRespVo;
import com.csci.cloud.core.client.mercury.model.resp.BusinessChangeRecordRespVo;
import com.csci.cloud.core.client.mercury.model.resp.ChattelMortgageRespVo;
import com.csci.cloud.core.client.mercury.model.resp.CreditRatingRespVo;
import com.csci.cloud.core.client.mercury.model.resp.EquityPledgeRespVo;
import com.csci.cloud.core.client.mercury.model.resp.FrozenShareRespVo;
import com.csci.cloud.core.client.mercury.model.resp.IndustryBasicInfoRespVo;
import com.csci.cloud.core.client.mercury.model.resp.ManagerInfoRespVo;
import com.csci.cloud.core.client.mercury.model.resp.SeriousOffenceRespVo;
import com.csci.cloud.core.common.ErrorEnums.MercuryErrorEnum;
import feign.hystrix.FallbackFactory;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 熔断处理.
 */
@Slf4j
public class CloudMercuryFallBackFactory implements FallbackFactory<CloudIndustryClient> {

  @Override
  public CloudIndustryClient create(Throwable cause) {

    log.error(ExceptionUtils.getStackTrace(cause));
    return new CloudIndustryClient() {
      @Override
      public CloudMercuryResponse<List<AdminPenaltyRespVo>> adminPenalty(
          AdminPenaltyReqVo adminPenaltyReqVo) {
        return HYSTRIX_RESPONSE;
      }

      @Override
      public CloudMercuryResponse<List<BondDefaultsRespVo>> bondDefaults(
          BondDefaultsReqVo bondDefaultsReqVo) {
        return HYSTRIX_RESPONSE;
      }

      @Override
      public CloudMercuryResponse<List<FrozenShareRespVo>> frozenShare(
          FrozenShareReqVo frozenShareReqVo) {
        return HYSTRIX_RESPONSE;
      }

      @Override
      public CloudMercuryResponse<List<CreditRatingRespVo>> creditRating(BaseReqVo baseReqVo) {
        return HYSTRIX_RESPONSE;
      }

      @Override
      public CloudMercuryResponse<IndustryBasicInfoRespVo> basicInfo(BaseReqVo baseReqVo) {
        return HYSTRIX_RESPONSE;
      }

      @Override
      public CloudMercuryResponse<List<ManagerInfoRespVo>> managerInfo(BaseReqVo baseReqVo) {
        return HYSTRIX_RESPONSE;
      }

      @Override
      public CloudMercuryResponse<List<BusinessChangeRecordRespVo>> businessChangeRecord(
          BaseReqVo baseReqVo) {
        return HYSTRIX_RESPONSE;
      }

      @Override
      public CloudMercuryResponse<List<EquityPledgeRespVo>> equityPledge(BaseReqVo baseReqVo) {
        return HYSTRIX_RESPONSE;
      }

      @Override
      public CloudMercuryResponse<List<ChattelMortgageRespVo>> chattelMortgage(
          BaseReqVo baseReqVo) {
        return HYSTRIX_RESPONSE;
      }

      @Override
      public CloudMercuryResponse<List<SeriousOffenceRespVo>> seriousOffence(BaseReqVo baseReqVo) {
        return HYSTRIX_RESPONSE;
      }
    };
  }
}
