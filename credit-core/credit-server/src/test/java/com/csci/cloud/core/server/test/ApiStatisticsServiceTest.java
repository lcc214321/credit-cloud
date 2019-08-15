package com.csci.cloud.core.server.test;

import com.csci.cloud.core.common.ErrorEnums;
import com.csci.cloud.core.server.Bootstrap;
import com.csci.cloud.core.server.data.dto.ApiUsageRecordDto;
import com.csci.cloud.core.server.service.ApiStatisticsService;
import com.google.common.collect.Lists;
import io.searchbox.client.JestResultHandler;
import io.searchbox.core.DocumentResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bootstrap.class)
public class ApiStatisticsServiceTest {

  private List<String> apiNames = Lists.newArrayList("三要素验证",
      "四要输验证",
      "不良资产记录",
      "法院执行记录",
      "失信记录");

  private List<String> companyName = Lists.newArrayList("腾讯", "阿里巴巴", "点融", "百度");

  @Autowired
  private ApiStatisticsService apiStatisticsService;


  @Test
  public void testCreate() {
    Date date = new Date();
    ApiUsageRecordDto apiUsageRecordVo = ApiUsageRecordDto.builder()
        .apiName(apiNames.get(RandomUtils.nextInt(0, apiNames.size())))
        .appId(1)
        .apiId(RandomStringUtils.randomAlphabetic(10))
        .type(RandomUtils.nextInt(0, 3))
        .tenantId(1)
        .seqNum(RandomStringUtils.randomAlphabetic(16))
        .companyName(companyName.get(RandomUtils.nextInt(0, companyName.size())))
        .companyCode("9999")
        .resultCode(
            ErrorEnums.MirrorErrorEnum.values()[RandomUtils
                .nextInt(0, ErrorEnums.MirrorErrorEnum.values().length)].getCode())
        .resultMessage(
            ErrorEnums.MirrorErrorEnum.values()[RandomUtils
                .nextInt(0, ErrorEnums.MirrorErrorEnum.values().length)].getDesc())
        .duration(RandomUtils.nextLong(1, 1000))
        .createdAt(date)
        .build();

    String s = apiStatisticsService.create(apiUsageRecordVo);
    System.out.println(s);
  }


  @Test
  public void testCreateAsync() {
    CountDownLatch countDownLatch = new CountDownLatch(1);
    Date date = new Date();
    ApiUsageRecordDto apiUsageRecordVo = ApiUsageRecordDto.builder()
        .apiName(apiNames.get(RandomUtils.nextInt(0, apiNames.size())))
        .appId(1)
        .apiId(RandomStringUtils.randomAlphabetic(10))
        .type(RandomUtils.nextInt(0, 3))
        .tenantId(1)
        .seqNum(RandomStringUtils.randomAlphabetic(16))
        .companyName(companyName.get(RandomUtils.nextInt(0, companyName.size())))
        .companyCode("9999")
        .resultCode(
            ErrorEnums.MirrorErrorEnum.values()[RandomUtils
                .nextInt(0, ErrorEnums.MirrorErrorEnum.values().length)].getCode())
        .resultMessage(
            ErrorEnums.MirrorErrorEnum.values()[RandomUtils
                .nextInt(0, ErrorEnums.MirrorErrorEnum.values().length)].getDesc())
        .duration(RandomUtils.nextLong(1, 1000))
        .createdAt(date)
        .build();

    apiStatisticsService.createAsync(apiUsageRecordVo, new JestResultHandler<DocumentResult>() {
      @Override
      public void completed(DocumentResult result) {
        System.out.println("result = [" + result.getJsonString() + "]");
        countDownLatch.countDown();
      }

      @Override
      public void failed(Exception ex) {

        ex.printStackTrace();
        countDownLatch.countDown();
      }
    });
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
