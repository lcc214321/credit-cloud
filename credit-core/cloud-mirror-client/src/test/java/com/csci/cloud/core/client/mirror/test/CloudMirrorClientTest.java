package com.csci.cloud.core.client.mirror.test;

import com.csci.cloud.core.client.mirror.AntiFraudMirrorResponse;
import com.csci.cloud.core.client.mirror.CloudMirrorClient;
import com.csci.cloud.core.client.mirror.FraudMirrorResponse;
import com.csci.cloud.core.client.mirror.CreditScoringMirrorResponse;
import com.csci.cloud.core.client.mirror.model.AntiFraudScoreReqVo;
import com.csci.cloud.core.client.mirror.model.CreditScoreReqVo;
import com.csci.cloud.core.client.mirror.model.MirrorFraud001ReqVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 个人反欺诈业务.
 */
@RunWith(SpringRunner.class)
@SpringBootApplication
@EnableFeignClients(basePackages = "com.csci.cloud.core.client.mirror")
@SpringBootTest()
public class CloudMirrorClientTest {

  @Autowired
  private CloudMirrorClient cloudMirrorClient;

  /**
   * 个人反欺诈评分.
   */
  @Test
  public void testAntiFraudScore() {
    AntiFraudScoreReqVo reqVo = new AntiFraudScoreReqVo();
    reqVo.setIdNo("3412258198906275950");
    reqVo.setMobile("15618579662");
    reqVo.setName("马传林");
    AntiFraudMirrorResponse response = cloudMirrorClient.antiFraudScoring(reqVo);
    System.out.println(response);
  }

  /**
   * 个人信用评分.
   */
  @Test
  public void testCreditScoring() {
    CreditScoreReqVo reqVo = new CreditScoreReqVo();
    reqVo.setIdNo("3412258198906275950");
    reqVo.setMobile("15618579662");
    reqVo.setName("马传林");
    CreditScoringMirrorResponse response = cloudMirrorClient.creditScoring(reqVo);
    System.out.println(response);
  }


  /**
   * 个人反欺诈业务.
   */
  @Test
  public void testFraud001() {
    MirrorFraud001ReqVo reqVo = MirrorFraud001ReqVo.builder()
        .idNo("341225198906274952")
        .name("马传林")
        .orgName("腾通讯")
        .mobile("15618579663").build();

    FraudMirrorResponse response = cloudMirrorClient.fraud001(reqVo);
    System.out.println(response);
  }
}
