package com.csci.cloud.admin.test;

import com.csci.cloud.admin.AccountBootstrap;
import com.csci.cloud.admin.data.vo.ApiUsageRecordVo;
import com.csci.cloud.admin.utils.ErrorCode;
import com.csci.cloud.core.common.ErrorEnums.CommonErrorEnum;
import com.csci.cloud.core.common.utils.SnowFlake;
import com.google.common.collect.Lists;
import io.searchbox.client.JestClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.BulkResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import java.io.IOException;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ben on 2018/9/19. benkris1@126.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountBootstrap.class)
public class ApiStatisticsServiceTest {

  private List<String> companyName = Lists.newArrayList("腾讯","阿里巴巴","点融","百度");
  @Autowired
  private JestClient jestClient;

  private SnowFlake snowFlake = new SnowFlake(1,2);
  @Test
  public void mockAllYear() {
    for(Month month:Month.values()) {
      Date date = new Date();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.set(Calendar.MONTH,month.ordinal());
      mock(calendar.getTime());
    }
  }

  @Test
  public void mockNow() {
    mock(new Date());
  }

  @Test
  public void mockRange() {
    LocalDate start = new LocalDate(2019,1,01);
    LocalDate end = new LocalDate(2019,5,30);
    Days days = Days.daysBetween(start, end);
    for(int i =0;i<days.getDays();i++) {
      mock(start.plusDays(i).toDate());
    }
    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void mock(Date date)   {

    List<ApiUsageRecordVo> list = Lists.newArrayList();
    for (int i = 0; i < 1000; i++) {
      ApiDefinesEnum apiDefinesEnum = ApiDefinesEnum.values()[RandomUtils.nextInt(0, ApiDefinesEnum.values().length)];
      CommonErrorEnum errorCode = CommonErrorEnum.values()[RandomUtils.nextInt(0, CommonErrorEnum.values().length)];
      ApiUsageRecordVo apiUsageRecordVo = ApiUsageRecordVo.builder()
          .apiName(apiDefinesEnum.getApiName())
          .appId(1)
          .apiId(apiDefinesEnum.getApiId())
          .type(apiDefinesEnum.getServerType().getValue())
          .tenantId(1)
          .seqNum(snowFlake.nextId()+"")
         // .companyName(companyName.get(RandomUtils.nextInt(0,companyName.size())))
         // .companyCode("9999")
          .resultCode(
              errorCode.getCode())
          .resultMessage(
              errorCode.getDesc())
          .duration(RandomUtils.nextLong(1, 1000))
          .createdAt(date)
          .build();
      list.add(apiUsageRecordVo);
    }
    Bulk.Builder bulk = new Bulk.Builder();
    for (ApiUsageRecordVo entity : list) {
      Index index = new Index.Builder(entity).index("credit-cloud").type("api-usage").build();
      bulk.addAction(index);
    }
    BulkResult execute = null;
    try {
      execute = jestClient.execute(bulk.build());
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(execute);

  }

  @Test
  public void statistics() throws IOException {
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.size(0);
    searchSourceBuilder.query(QueryBuilders.rangeQuery("createdAt").gt("2018-09-19T00:25:13.384+0800").lt("2018-09-19T15:26:13.384+0800"));

    AbstractAggregationBuilder aggregation = AggregationBuilders.dateHistogram("group_by_day")
        .field("createdAt")
        .dateHistogramInterval(DateHistogramInterval.HOUR)
        .format("yyyy-MM-dd")
        .minDocCount(0L)
        .extendedBounds(new ExtendedBounds(
        "2018-09-19", "2018-09-20"));
    AbstractAggregationBuilder aggregationType = AggregationBuilders.terms("group_by_type").field("type");
    aggregation.subAggregation(aggregationType);
    searchSourceBuilder.aggregation(aggregation);
    System.out.println(searchSourceBuilder.toString());
    Search search = new Search.Builder(searchSourceBuilder.toString())
        .addIndex("credit-cloud")
        .addType("api-usage")
        .build();
    SearchResult searchResult = jestClient.execute(search);
    System.out.println(searchResult.getAggregations().getDateHistogramAggregation("group_by_day"));
  }


}
