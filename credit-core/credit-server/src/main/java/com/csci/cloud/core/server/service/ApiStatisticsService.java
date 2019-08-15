package com.csci.cloud.core.server.service;

import com.csci.cloud.core.server.data.dto.ApiUsageRecordDto;
import io.searchbox.client.JestResult;
import io.searchbox.client.JestResultHandler;
import io.searchbox.core.DocumentResult;

/**
 * Created by ben on 2018/9/19. benkris1@126.com API统计相关.
 */
public interface ApiStatisticsService {


  /**
   * trace数据.
   *
   * @return document id.
   */
  String create(ApiUsageRecordDto apiUsageRecordDto);

  /**
   * 异步创建.
   */
  void createAsync(ApiUsageRecordDto apiUsageRecordDto,
                   JestResultHandler<DocumentResult> jestResultHandler);

}
