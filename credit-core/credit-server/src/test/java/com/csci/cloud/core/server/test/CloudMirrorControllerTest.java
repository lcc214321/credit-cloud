package com.csci.cloud.core.server.test;

import com.csci.cloud.core.client.mirror.model.CreditScoreReqVo;
import com.csci.cloud.core.client.mirror.model.MirrorFraud001ReqVo;
import com.csci.cloud.core.common.utils.JsonUtils;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by ben on 2018/10/8. benkris1@126.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class CloudMirrorControllerTest extends AbstractTestController {


  private MockMvc mvc;
  @Autowired
  private WebApplicationContext context;

  @Before
  public void setupMockMvc() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void fraud001() throws Exception {
    MirrorFraud001ReqVo reqVo = MirrorFraud001ReqVo.builder()
        .idNo("341225198906274952")
        .name("马传林")
        .orgName("腾通讯")
        .mobile("15618579663").build();
    Long timestamp = System.currentTimeMillis();
    String uri = "/api/v1/mirror/anti-fraud/001";
    String contentAsString = mvc.perform(MockMvcRequestBuilders.post(uri)
        .header("apiKey", API_KEY)
        .header("timestamp", timestamp)
        .header("sign", calSign(API_KEY, SECRET, timestamp, uri, Maps.newHashMap()))
        .content(JsonUtils.toJson(reqVo))
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andReturn().getResponse()
        .getContentAsString();
    System.out.println(contentAsString);

  }


  @Test
  public void creditScoring() throws Exception {
    CreditScoreReqVo reqVo = new CreditScoreReqVo();
    reqVo.setIdNo("3412258198906275950");
    reqVo.setMobile("15618579662");
    reqVo.setName("马传林");
    Long timestamp = System.currentTimeMillis();
    String uri = "/api/v1/mirror/credit-scoring";
    String contentAsString = mvc.perform(MockMvcRequestBuilders.post(uri)
        .header("apiKey", API_KEY)
        .header("timestamp", timestamp)
        .header("sign", calSign(API_KEY, SECRET, timestamp, uri, Maps.newHashMap()))
        .content(JsonUtils.toJson(reqVo))
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andReturn().getResponse()
        .getContentAsString();
    System.out.println(contentAsString);

  }


}
