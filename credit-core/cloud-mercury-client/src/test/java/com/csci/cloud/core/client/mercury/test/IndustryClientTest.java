package com.csci.cloud.core.client.mercury.test;

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
import com.csci.cloud.core.common.utils.JsonUtils;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 企业征信单元测试.
 */
@RunWith(SpringRunner.class)
@SpringBootApplication
@EnableFeignClients(basePackages = "com.csci.cloud.core.client.mercury")
@SpringBootTest(properties = {"client.cloud-mercury.url:https://daas.chinacscs.com/api"})
public class IndustryClientTest {

  @Autowired
  private CloudIndustryClient industryClient;

  /**
   * 行政处罚.
   */
  @Test
  public void testAdminPenalty() {
    AdminPenaltyReqVo adminPenaltyReqVo = new AdminPenaltyReqVo();
    adminPenaltyReqVo.setCompanyName("上海申能星火热电有限责任公司");
    CloudMercuryResponse<List<AdminPenaltyRespVo>> response = industryClient
        .adminPenalty(adminPenaltyReqVo);
    System.out.println(response);
  }

  /**
   * 债券违约.
   */
  @Test
  public void testBondDefaults() {
    BondDefaultsReqVo bondDefaultsReqVo = new BondDefaultsReqVo();
    bondDefaultsReqVo.setCompanyName("萧县建设投资有限责任公司");
    CloudMercuryResponse<List<BondDefaultsRespVo>> response = industryClient
        .bondDefaults(bondDefaultsReqVo);

    System.out.println(response);

  }

  /**
   * 股权冻结.
   */
  @Test
  public void testFrozenShare() {
    FrozenShareReqVo frozenShareReqVo = new FrozenShareReqVo();
    frozenShareReqVo.setCompanyName("中国民生银行股份有限公司");
    CloudMercuryResponse<List<FrozenShareRespVo>> response = industryClient
        .frozenShare(frozenShareReqVo);

    System.out.println(response);

  }

  /**
   * 企业主体评级.
   */
  @Test
  public void testCreditRating() {
    BaseReqVo baseReqVo = new BaseReqVo();

    baseReqVo.setCompanyName("萧县建设投资有限责任公司");

    CloudMercuryResponse<List<CreditRatingRespVo>> response = industryClient
        .creditRating(baseReqVo);

    System.out.println(response);
  }

  /**
   * 企业基本信息 .
   */
  @Test
  public void testBasicInfo() {
    BaseReqVo baseReqVo = new BaseReqVo();

    baseReqVo.setCompanyName("中证信用增进股份有限公司");

    CloudMercuryResponse<IndustryBasicInfoRespVo> response = industryClient
        .basicInfo(baseReqVo);

    System.out.println(response);
  }

  /**
   * 企业高管信息 .
   */
  @Test
  public void testManagerInfo() {
    BaseReqVo baseReqVo = new BaseReqVo();

    baseReqVo.setCompanyName("中证信用增进股份有限公司");

    CloudMercuryResponse<List<ManagerInfoRespVo>> response = industryClient
        .managerInfo(baseReqVo);

    System.out.println(response);
  }

  /**
   * 企业工商信息变更记录 .
   */
  @Test
  public void testChangeRecord() {
    BaseReqVo baseReqVo = new BaseReqVo();
    baseReqVo.setCompanyName("中证信用增进股份有限公司");

    CloudMercuryResponse<List<BusinessChangeRecordRespVo>> response = industryClient
        .businessChangeRecord(baseReqVo);
    System.out.println(response);
  }


  /**
   * 股权出质 .
   */
  @Test
  public void testEquityPledge() {
    BaseReqVo baseReqVo = new BaseReqVo();
    baseReqVo.setCompanyName("保亭黎族苗族自治县农村信用合作联社");

    CloudMercuryResponse<List<EquityPledgeRespVo>> response = industryClient
        .equityPledge(baseReqVo);
    System.out.println(response);
  }

  /**
   * 动产抵押 .
   */
  @Test
  public void testChattelMortgage() {
    BaseReqVo baseReqVo = new BaseReqVo();
    baseReqVo.setCompanyName("柳州东风容泰化工股份有限公司");

    CloudMercuryResponse<List<ChattelMortgageRespVo>> response = industryClient
        .chattelMortgage(baseReqVo);
    System.out.println(response);
  }

  /**
   * 企业严重违法.
   */
  @Test
  public void testSeriousOffence() {
    BaseReqVo baseReqVo = new BaseReqVo();
    baseReqVo.setCompanyName("倬可儿服饰(深圳)有限公司");

    CloudMercuryResponse<List<SeriousOffenceRespVo>> response = industryClient
        .seriousOffence(baseReqVo);
    System.out.println(response);
  }

}
