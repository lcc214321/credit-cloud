package com.csci.cloud.core.client.mercury;

import com.csci.cloud.core.client.mercury.config.CloudMercuryConfig;
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
import feign.Headers;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cloud-mercury", url = "${client.cloud-mercury.url}",
    configuration = CloudMercuryConfig.class,
    fallbackFactory = CloudMercuryFallBackFactory.class)
public interface CloudIndustryClient {


  /**
   * 企业行政处罚.
   */
  @RequestMapping(value = "/enterprise/basic/compyAdminPenalty", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED,
      produces = MediaType.APPLICATION_JSON)
  @Headers("Content-Type: application/x-www-form-urlencoded")
  CloudMercuryResponse<List<AdminPenaltyRespVo>> adminPenalty(AdminPenaltyReqVo adminPenaltyReqVo);


  /**
   * 债券违约.
   */
  @RequestMapping(value = "/enterprise/basic/bondDefaults", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED,
      produces = MediaType.APPLICATION_JSON)
  @Headers("Content-Type: application/x-www-form-urlencoded")
  CloudMercuryResponse<List<BondDefaultsRespVo>> bondDefaults(BondDefaultsReqVo bondDefaultsReqVo);


  /**
   * 股权冻结.
   */
  @RequestMapping(value = "/enterprise/basic/compyFrozenShare", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED,
      produces = MediaType.APPLICATION_JSON)
  @Headers("Content-Type: application/x-www-form-urlencoded")
  CloudMercuryResponse<List<FrozenShareRespVo>> frozenShare(FrozenShareReqVo frozenShareReqVo);

  /**
   * 企业主体评级.
   */
  @RequestMapping(value = "/enterprise/basic/creditRating", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED,
      produces = MediaType.APPLICATION_JSON)
  @Headers("Content-Type: application/x-www-form-urlencoded")
  CloudMercuryResponse<List<CreditRatingRespVo>> creditRating(BaseReqVo baseReqVo);


  /**
   * 工商基本信息.
   */
  @RequestMapping(value = "/enterprise/basic/compyBasicInfo", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED,
      produces = MediaType.APPLICATION_JSON)
  @Headers("Content-Type: application/x-www-form-urlencoded")
  CloudMercuryResponse<IndustryBasicInfoRespVo> basicInfo(BaseReqVo baseReqVo);


  /**
   * 企业高管信息.
   */
  @RequestMapping(value = "/enterprise/basic/compyExecutivesInfo", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED,
      produces = MediaType.APPLICATION_JSON)
  @Headers("Content-Type: application/x-www-form-urlencoded")
  CloudMercuryResponse<List<ManagerInfoRespVo>> managerInfo(BaseReqVo baseReqVo);

  /**
   * 企业变更.
   */
  @RequestMapping(value = "/enterprise/basic/compyChange", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED,
      produces = MediaType.APPLICATION_JSON)
  @Headers("Content-Type: application/x-www-form-urlencoded")
  CloudMercuryResponse<List<BusinessChangeRecordRespVo>> businessChangeRecord(BaseReqVo baseReqVo);


  /**
   * 股权出质.
   */
  @RequestMapping(value = "/enterprise/basic/compyEquityPledge", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED,
      produces = MediaType.APPLICATION_JSON)
  @Headers("Content-Type: application/x-www-form-urlencoded")
  CloudMercuryResponse<List<EquityPledgeRespVo>> equityPledge(BaseReqVo baseReqVo);


  /**
   * 动产抵押.
   */
  @RequestMapping(value = "/enterprise/basic/compyChattelReg", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED,
      produces = MediaType.APPLICATION_JSON)
  @Headers("Content-Type: application/x-www-form-urlencoded")
  CloudMercuryResponse<List<ChattelMortgageRespVo>> chattelMortgage(BaseReqVo baseReqVo);


  /**
   * 企业严重违法.
   */
  @RequestMapping(value = "/enterprise/basic/seriousOffence", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED,
      produces = MediaType.APPLICATION_JSON)
  @Headers("Content-Type: application/x-www-form-urlencoded")
  CloudMercuryResponse<List<SeriousOffenceRespVo>> seriousOffence(BaseReqVo baseReqVo);

}
