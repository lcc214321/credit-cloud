package com.csci.cloud.client.test.mars;

import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.ResponseVo;
import com.csci.cloud.client.test.BaseClientTest;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import java.io.File;
import java.util.Base64;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * 接口文档 https://www.showdoc.cc/zxhapi?page_id=1477759567737112
 * @Author: ben.ma
 * @Date: 2019/3/20 3:07 PM benkris1@gmail.com
 */
public class CloudMarsClientTest  extends BaseClientTest {

  private String token ;
  public static final String USER_NAME = "zzxy";
  public static final String PASSWORD = Hashing.md5().hashString("1234", Charsets.UTF_8).toString();

  public Map<String,Object> setUp() {
    Map<String,Object> reqMap  = Maps.newHashMap();
    reqMap.put("instId","MGSJ");
    reqMap.put("creditType","A");
    reqMap.put("channel","MG");
    reqMap.put("productCode","000509");
    reqMap.put("deptCode","H");
    reqMap.put("instId","MGSJ");
    reqMap.put("requestId","MGSJ"+ RandomStringUtils.randomNumeric(10));
    return reqMap;
  }

  @Before
  public void before() throws Exception {
    String uri = "/api/v1/mars/creditCertificationRt";
    Map reqMap = setUp();
    reqMap.put("userName",USER_NAME);
    reqMap.put("password",PASSWORD) ;
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(reqMap), null, null);
    System.out.println(JsonUtils.toJson(responseVo));
    Map data = (Map)responseVo.getData();
    token = (String)data.get("token");
  }

  @Test
  public void creditFindRt() throws Exception {
    String uri = "/api/v1/mars/creditFindRt";
    Map reqMap = setUp();
    Map map = creditApplyRt();

    reqMap.put("verificationCode",(String)map.get("verificationCode"));
    reqMap.put("queryType", "02");
    reqMap.put("isFileReturn","1");

    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST",
            JsonUtils.toJson(reqMap),
            null,
            ImmutableMap.<String,String>builder().put("X-Credit-Rt-Token",token).build());
    System.out.println(JsonUtils.toJson(responseVo));
  }


  @Test
  public void creditApplyRtTest() throws Exception {
    Map map = creditApplyRt();
    System.out.println(JsonUtils.toJson(map));
  }

  @Test
  public void creditActivateRt() throws Exception {
    String uri = "/api/v1/mars/creditActivateRt";
    Map reqMap = setUp();
    String idNo = "420982199206026031";
    String idType = "01";
    String customerName = "lisi";
    reqMap.put("creditCode",preApply(idNo,idType,customerName));
    reqMap.put("idNo",idNo);
    reqMap.put("customerName",customerName);

    reqMap.put("idType",idType);
    reqMap.put("isFileReturn","1");

    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST",
            JsonUtils.toJson(reqMap),
            null,
            ImmutableMap.<String,String>builder().put("X-Credit-Rt-Token",token).build());
    System.out.println(JsonUtils.toJson(responseVo));
  }

  @Test
  public void creditVerificationRt() throws Exception {
    Map map = creditApplyRt();
    String uri = "/api/v1/mars/creditVerificationRt";
    Map reqMap = setUp();
    reqMap.put("creditCode",(String)map.get("creditCode"));
    reqMap.put("verType","01");
//    reqMap.put("pdf",Base64.getEncoder().encode(
//        Files.toByteArray(new File(this.getClass()
//            .getClassLoader()
//            .getResource("HMGA1953788116.pdf").getFile()))
//        )
//    );

    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST",
            JsonUtils.toJson(reqMap),
            null,
            ImmutableMap.<String,String>builder().put("X-Credit-Rt-Token",token).build());
    System.out.println(JsonUtils.toJson(responseVo));
  }


  /**
   * 返回预申请creditCode.
   * @param idNo
   * @param idType
   * @param customerName
   * @return
   * @throws Exception
   */
  private String  preApply(String idNo,String idType,String customerName) throws Exception {
    String uri = "/api/v1/mars/creditPreApplyRt";
    Map reqMap = setUp();
    reqMap.put("amount","10000.00");
    reqMap.put("startDate", "20190122");
    reqMap.put("endDate","20190228");
    reqMap.put("customerName",customerName);
    reqMap.put("idNo",idNo);
    reqMap.put("custType","1");
    reqMap.put("idType",idType);
    Map customMap = Maps.newHashMap();
    customMap.put("mobile","15801569865");
    customMap.put("telphone","8556555");
    customMap.put("creditRate","23");
    customMap.put("creditAmount","100.00");
    customMap.put("creditServNo","34234");
    reqMap.put("customFields",customMap);

    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST",
            JsonUtils.toJson(reqMap),
            null,
            ImmutableMap.<String,String>builder().put("X-Credit-Rt-Token",token).build());

    Map data = (Map)responseVo.getData();
    return (String)data.get("creditCode");
  }

  private Map creditApplyRt() throws Exception {
    String uri = "/api/v1/mars/creditApplyRt";
    Map reqMap = setUp();
    reqMap.put("amount","10000.00");
    reqMap.put("startDate", "20190122");
    reqMap.put("endDate","20190228");
    reqMap.put("customerName","lisi");
    reqMap.put("idNo","420982199206026031");
    reqMap.put("custType","1");
    reqMap.put("idType","01");
    reqMap.put("isFileReturn","1");
    Map customMap = Maps.newHashMap();
    customMap.put("mobile","15801569865");
    customMap.put("telphone","8556555");
    customMap.put("creditRate","23");
    customMap.put("creditAmount","100.00");
    customMap.put("creditServNo","34234");
    reqMap.put("customFields",customMap);

    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST",
            JsonUtils.toJson(reqMap),
            null,
            ImmutableMap.<String,String>builder().put("X-Credit-Rt-Token",token).build());
    return (Map)responseVo.getData();
  }

}
