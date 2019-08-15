package com.csci.cloud.core.server.service.impl;

import com.csci.cloud.core.server.data.dto.ApiUsageRecordDto;
import com.csci.cloud.core.server.service.ApiStatisticsService;
import com.csci.cloud.core.server.utils.EsConstants;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResultHandler;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApiStatisticsServiceImpl implements ApiStatisticsService {

  @Autowired
  private JestClient jestClient;


  @Override
  public String create(ApiUsageRecordDto apiUsageRecordDto) {
    Index index = new Index.Builder(apiUsageRecordDto).index(EsConstants.INDEX_CREDIT_CLOUD)
        .type(EsConstants.TYPE_API_USAGE).build();
    try {
      DocumentResult execute = jestClient.execute(index);
      if (execute.isSucceeded()) {
        return execute.getId();
      }
      throw new RuntimeException(execute.getErrorMessage());
    } catch (Throwable e) {
      ExceptionUtils.wrapAndThrow(e);
    }
    return null;
  }

  @Override
  public void createAsync(ApiUsageRecordDto apiUsageRecordDto,
      JestResultHandler<DocumentResult> jestResultHandler) {
    Index index = new Index.Builder(apiUsageRecordDto).index(EsConstants.INDEX_CREDIT_CLOUD)
        .type(EsConstants.TYPE_API_USAGE).build();
    jestClient.executeAsync(index, jestResultHandler);
  }
}
