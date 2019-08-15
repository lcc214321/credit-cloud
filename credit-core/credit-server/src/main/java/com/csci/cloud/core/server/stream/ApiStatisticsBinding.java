package com.csci.cloud.core.server.stream;

import com.csci.cloud.core.server.data.dto.ApiUsageRecordDto;
import com.csci.cloud.core.server.service.ApiStatisticsService;
import io.searchbox.client.JestResultHandler;
import io.searchbox.core.DocumentResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

@EnableBinding(value = ApiStatisticsStreamChannel.class)
@Slf4j
public class ApiStatisticsBinding {

  @Autowired
  private ApiStatisticsService apiStatisticsService;

  /**
   * api使用记录消费者.
   * @param message .
   */
  @StreamListener(ApiStatisticsStreamChannel.API_RECORD_INPUT_CHANNEL)
  public void consumerApiUsageListener(Message<ApiUsageRecordDto> message) {

    ApiUsageRecordDto apiUsageRecordDto = message.getPayload();
    if (log.isDebugEnabled()) {
      log.debug("rabbit mq consumer:{}", apiUsageRecordDto.toString());
    }
    apiStatisticsService.createAsync(apiUsageRecordDto, new JestResultHandler<DocumentResult>() {
      @Override
      public void completed(DocumentResult result) {
        if (log.isDebugEnabled()) {
          log.debug(result.getJsonString());
        }
      }

      @Override
      public void failed(Exception ex) {
        log.error(ExceptionUtils.getMessage(ex));
      }
    });
  }
}
