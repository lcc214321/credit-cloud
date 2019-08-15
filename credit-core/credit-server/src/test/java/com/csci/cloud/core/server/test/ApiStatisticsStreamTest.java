package com.csci.cloud.core.server.test;

import com.csci.cloud.core.common.ErrorEnums;
import com.csci.cloud.core.server.Bootstrap;
import com.csci.cloud.core.server.data.dto.ApiUsageRecordDto;
import com.csci.cloud.core.server.stream.ApiStatisticsStreamChannel;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bootstrap.class)
public class ApiStatisticsStreamTest {

  public static final long SENDING_MQ_TIMEOUT_MS = 3000;
  private List<String> apiNames = Lists.newArrayList("三要素验证",
      "四要输验证",
      "不良资产记录",
      "法院执行记录",
      "失信记录");

  private List<String> companyName = Lists.newArrayList("腾讯", "阿里巴巴", "点融", "百度");

  @Autowired
  private ApiStatisticsStreamChannel streamChannel;

  @Test
  public void sendData() {

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

    Message<ApiUsageRecordDto> m = MessageBuilder.withPayload(apiUsageRecordVo).build();
    boolean send = streamChannel.apiRecordInput().send(m, SENDING_MQ_TIMEOUT_MS);
    System.out.println(send);
    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
