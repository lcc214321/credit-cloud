package com.csci.cloud.core.server.test;


import com.csci.cloud.core.client.mercury.model.req.AdminPenaltyReqVo;
import com.csci.cloud.core.common.utils.JsonUtils;
import com.google.common.collect.Maps;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class CloudIndustryController extends AbstractTestController {

  private MockMvc mvc;
  @Autowired
  private WebApplicationContext context;

  @Before
  public void setupMockMvc() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void adminPenalty() throws Exception {
    AdminPenaltyReqVo adminPenaltyReqVo = new AdminPenaltyReqVo();
    adminPenaltyReqVo.setCompanyName("上海申能星火热电有限责任公司");
    String uri = "/api/v1/enterprise/basic/admin-penalty";
    Long timestamp = System.currentTimeMillis();
    String contentAsString = mvc.perform(MockMvcRequestBuilders.post(uri)
        .header("apiKey", API_KEY)
        .header("timestamp", timestamp)
        .header("sign", calSign(API_KEY, SECRET, timestamp, uri, Maps.newHashMap()))
        .content(JsonUtils.toJson(adminPenaltyReqVo))
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
        .getContentAsString();
    System.out.println(contentAsString);
  }
}
