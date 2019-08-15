package com.csci.cloud.core.server.web.controller.mercury;

import com.csci.cloud.core.client.mercury.CloudIndustryClient;
import com.csci.cloud.core.client.mercury.CloudMercuryResponse;
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
import com.csci.cloud.core.server.utils.ApiDefinesEnum;
import com.csci.cloud.core.server.web.annotation.ApiDefine;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/enterprise/basic",
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CloudIndustryController extends AbstractMercuryController {

  @Autowired
  private CloudIndustryClient cloudIndustryClient;


  /**
   * 行政处罚.
   */
  @RequestMapping(value = "/admin-penalty", method = RequestMethod.POST)
  @ApiDefine(value = ApiDefinesEnum.ADMINISTRATIVE_PENALTY)
  public List<AdminPenaltyRespVo> adminPenalty(@RequestBody AdminPenaltyReqVo adminPenaltyReqVo) {
    CloudMercuryResponse<List<AdminPenaltyRespVo>> response = cloudIndustryClient
        .adminPenalty(adminPenaltyReqVo);
    checkResponse(response);
    return response.getData();
  }


  /**
   * 债券违约.
   */
  @RequestMapping(value = "/bond-defaults", method = RequestMethod.POST)
  @ApiDefine(value = ApiDefinesEnum.BOND_DEFAULTS)
  public List<BondDefaultsRespVo> bondDefaults(@RequestBody BondDefaultsReqVo reqVo) {
    CloudMercuryResponse<List<BondDefaultsRespVo>> response = cloudIndustryClient
        .bondDefaults(reqVo);
    checkResponse(response);
    return response.getData();
  }


  /**
   * 股权冻结.
   */
  @RequestMapping(value = "/frozen-share", method = RequestMethod.POST)
  @ApiDefine(value = ApiDefinesEnum.FROZEN_SHARE)
  public List<FrozenShareRespVo> frozenShare(@RequestBody FrozenShareReqVo reqVo) {
    CloudMercuryResponse<List<FrozenShareRespVo>> response = cloudIndustryClient
        .frozenShare(reqVo);
    checkResponse(response);
    return response.getData();
  }


  /**
   * 企业主体评级.
   */
  @RequestMapping(value = "/credit-rating", method = RequestMethod.POST)
  @ApiDefine(value = ApiDefinesEnum.CREDIT_RATING)
  public List<CreditRatingRespVo> creditRating(@RequestBody BaseReqVo reqVo) {
    CloudMercuryResponse<List<CreditRatingRespVo>> response = cloudIndustryClient
        .creditRating(reqVo);
    checkResponse(response);
    return response.getData();
  }


  /**
   * 工商基本信息.
   */
  @RequestMapping(value = "/base-info", method = RequestMethod.POST)
  @ApiDefine(value = ApiDefinesEnum.BASE_INFO)
  public IndustryBasicInfoRespVo basicInfo(@RequestBody BaseReqVo reqVo) {
    CloudMercuryResponse<IndustryBasicInfoRespVo> response = cloudIndustryClient
        .basicInfo(reqVo);
    checkResponse(response);
    return response.getData();
  }


  /**
   * 企业高管信息..
   */
  @RequestMapping(value = "/manage-info", method = RequestMethod.POST)
  @ApiDefine(value = ApiDefinesEnum.MANAGE_INFO)
  public List<ManagerInfoRespVo> managerInfo(@RequestBody BaseReqVo reqVo) {
    CloudMercuryResponse<List<ManagerInfoRespVo>> response = cloudIndustryClient
        .managerInfo(reqVo);
    checkResponse(response);
    return response.getData();
  }


  /**
   * 企业工商信息变更.
   */
  @RequestMapping(value = "/business-change-record", method = RequestMethod.POST)
  @ApiDefine(value = ApiDefinesEnum.BUSINESS_CHANGE_RECORD)
  public List<BusinessChangeRecordRespVo> businessChangeRecord(@RequestBody BaseReqVo reqVo) {
    CloudMercuryResponse<List<BusinessChangeRecordRespVo>> response = cloudIndustryClient
        .businessChangeRecord(reqVo);
    checkResponse(response);
    return response.getData();
  }


  /**
   * 股权出质.
   */
  @RequestMapping(value = "/equity-pledge", method = RequestMethod.POST)
  @ApiDefine(value = ApiDefinesEnum.EQUITY_PLEDGE)
  public List<EquityPledgeRespVo> equityPledge(@RequestBody BaseReqVo reqVo) {
    CloudMercuryResponse<List<EquityPledgeRespVo>> response = cloudIndustryClient
        .equityPledge(reqVo);
    checkResponse(response);
    return response.getData();
  }


  /**
   * 动产抵押.
   */
  @RequestMapping(value = "/chattel-mortgage", method = RequestMethod.POST)
  @ApiDefine(value = ApiDefinesEnum.CHATTEL_MORTGAGE)
  public List<ChattelMortgageRespVo> chattelMortgage(@RequestBody BaseReqVo reqVo) {
    CloudMercuryResponse<List<ChattelMortgageRespVo>> response = cloudIndustryClient
        .chattelMortgage(reqVo);
    checkResponse(response);
    return response.getData();
  }

  /**
   * 企业严重违法.
   */
  @RequestMapping(value = "/serious-offence", method = RequestMethod.POST)
  @ApiDefine(value = ApiDefinesEnum.SERIOUS_OFFENCE)
  public List<SeriousOffenceRespVo> seriousOffence(@RequestBody BaseReqVo reqVo) {
    CloudMercuryResponse<List<SeriousOffenceRespVo>> response = cloudIndustryClient
        .seriousOffence(reqVo);
    checkResponse(response);
    return response.getData();
  }

}
