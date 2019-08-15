package com.csci.cloud.auth.client;

import com.csci.cloud.auth.client.model.res.AuthResponse;
import com.csci.cloud.auth.common.data.AuthenticationReqVo;
import com.csci.cloud.auth.common.data.AuthenticationRespVo;
import com.google.common.collect.Maps;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

/**
 */
@RunWith(SpringRunner.class)
@SpringBootApplication
@EnableFeignClients(basePackages = "com.csci.cloud.auth.client")
@SpringBootTest(properties = {"client.auth.url:http://auth-cc.demo.chinacsci.com"})
public class AuthClientTest {

  @Autowired
  private AuthClient authClient;

  @Test
  public void testAuthClient() {
    Map paraMap = Maps.newHashMap();
    paraMap.put("status","0");
    paraMap.put("page_no","1");
    paraMap.put("page_size","50");
    AuthenticationReqVo authenticationReqVo = AuthenticationReqVo.builder()
    .apiKey("ntjhb0v6thrwaujqttytbzayow5ozw")
        .timestamp(System.currentTimeMillis()/1000)
        .uri("/ark/open_api/v1/items")
        .sign("72be6fad4dd0e5104dbdebbcdadb2a06")
        .queryMap(paraMap)
        .build();
    AuthResponse<AuthenticationRespVo> authentication = authClient
        .authentication(authenticationReqVo);
    System.out.println(authentication);
  }
}
