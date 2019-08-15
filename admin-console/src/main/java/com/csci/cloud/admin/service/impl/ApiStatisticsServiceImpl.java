package com.csci.cloud.admin.service.impl;

import com.csci.cloud.admin.convert.ApiStatisticsConvert;
import com.csci.cloud.admin.dao.ExUserDao;
import com.csci.cloud.admin.data.vo.ApiStatisticsVo;
import com.csci.cloud.admin.data.vo.ApiUsageRecordVo;
import com.csci.cloud.admin.data.vo.PagingRespVo;
import com.csci.cloud.admin.exception.InternalBizException;
import com.csci.cloud.admin.jooq.tables.pojos.TUser;
import com.csci.cloud.admin.service.ApiStatisticsService;
import com.csci.cloud.admin.utils.ConfigEnums.StatisticsInternalEnum;
import com.csci.cloud.admin.utils.ApiStatisticsConstants;
import com.csci.cloud.admin.utils.ErrorCode;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ben on 2018/9/19. benkris1@126.com
 */
@Service
@Slf4j
public class ApiStatisticsServiceImpl implements ApiStatisticsService {

  @Autowired
  private JestClient jestClient;

  @Autowired
  private ExUserDao userDao;


  public List<ApiStatisticsVo> statistics(int userId, Date startTime, Date endTime,
      StatisticsInternalEnum internalEnum) {

    TUser useDo = userDao.fetchOneById(userId);
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.size(0);
    String startTimeRaw = DateFormatUtils.format(startTime, ApiStatisticsConstants.API_STATISTICS_DATE_FORMAT);
    String endTimeRaw = DateFormatUtils.format(endTime, ApiStatisticsConstants.API_STATISTICS_DATE_FORMAT);
    QueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("createdAt")
        .gte(startTimeRaw)
        .lte(endTimeRaw);
    QueryBuilder termQuery = QueryBuilders.termQuery("tenantId", useDo.getTenantId());

    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    boolQueryBuilder.must(rangeQueryBuilder).must(termQuery);
    searchSourceBuilder.query(boolQueryBuilder);

    AbstractAggregationBuilder aggregation = AggregationBuilders.dateHistogram(
        ApiStatisticsConstants.GROUP_BY_INTERNAL_API)
        .field("createdAt")
        .timeZone(DateTimeZone.forID("+08:00"))
        .dateHistogramInterval(new DateHistogramInterval(internalEnum.getValue()))
        .format(internalEnum.getFormat())
        .minDocCount(0L)
        .extendedBounds(new ExtendedBounds(
            new DateTime(startTime).toString(internalEnum.getFormat()),
                new DateTime(endTime).toString(internalEnum.getFormat())));

    AbstractAggregationBuilder aggregationType = AggregationBuilders.terms(ApiStatisticsConstants.GROUP_BY_API_TYPE)
        .field("type");
    aggregation.subAggregation(aggregationType);
    searchSourceBuilder.aggregation(aggregation);
    System.out.println(searchSourceBuilder.toString());
    Search search = new Search.Builder(searchSourceBuilder.toString())
        .addIndex(ApiStatisticsConstants.INDEX_CREDIT_CLOUD)
        .addType(ApiStatisticsConstants.TYPE_API_USAGE)
        .build();

    try {
      SearchResult searchResult = jestClient.execute(search);
      if (searchResult.isSucceeded()) {
        return  ApiStatisticsConvert.convertApiStatistics(internalEnum,searchResult);
      }else {
        log.error("search api statistics from es error {}",searchResult.getErrorMessage());
        throw new InternalBizException(ErrorCode.INTERNAL_SERVER_ERROR);
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new InternalBizException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public PagingRespVo<ApiUsageRecordVo> list(int userId, String seqNum, Integer type, String apiId,
      String apiName, Date startTime, Date endTime,int skip,int limit,String order) {
    TUser useDo = userDao.fetchOneById(userId);
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    QueryBuilder termQuery = QueryBuilders.termQuery("tenantId", useDo.getTenantId());
    boolQueryBuilder.must(termQuery);


    if (null != startTime && null != endTime) {
      boolean sameInstant = DateUtils.isSameDay(startTime, endTime);
      if(sameInstant) {
        endTime = DateUtils.addMilliseconds(DateUtils.ceiling(endTime, Calendar.DATE), -1);
      }
      String startTimeRaw = DateFormatUtils.format(startTime,ApiStatisticsConstants.API_STATISTICS_DATE_FORMAT);
      String endTimeRaw = DateFormatUtils.format(endTime,ApiStatisticsConstants.API_STATISTICS_DATE_FORMAT);
      QueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("createdAt")
          .gte(startTimeRaw)
          .lte(endTimeRaw);
      boolQueryBuilder.must(rangeQueryBuilder);

    }

    if(StringUtils.isNotBlank(seqNum)){
      QueryBuilder seqQueryBuilder  = QueryBuilders.termQuery("seqNum", seqNum);
      boolQueryBuilder.must(seqQueryBuilder);
    }

    if(null != type) {
      QueryBuilder typeQueryBuilder = QueryBuilders.termQuery("type",type);
      boolQueryBuilder.must(typeQueryBuilder);
    }

    if(StringUtils.isNotBlank(apiId)) {
      QueryBuilder apiIdQueryBuilder = QueryBuilders.termQuery("apiId",apiId);
      boolQueryBuilder.must(apiIdQueryBuilder);
    }

    if(StringUtils.isNotBlank(apiName)) {
      QueryBuilder apiNameQueryBuilder = QueryBuilders.matchQuery("apiName",apiName);
      boolQueryBuilder.must(apiNameQueryBuilder);
    }

    searchSourceBuilder.query(boolQueryBuilder);
    searchSourceBuilder.size(limit);
    searchSourceBuilder.from(skip);
    System.out.println(searchSourceBuilder.toString());
    Search search = new Search.Builder(searchSourceBuilder.toString())
        .addIndex(ApiStatisticsConstants.INDEX_CREDIT_CLOUD)
        .addType(ApiStatisticsConstants.TYPE_API_USAGE)
        .build();

    try {
      SearchResult searchResult = jestClient.execute(search);
      if (searchResult.isSucceeded()) {
        PagingRespVo<ApiUsageRecordVo> pagingRespVo = ApiStatisticsConvert
            .convertApiList(searchResult);

        pagingRespVo.setLimit(Long.valueOf(limit));
        pagingRespVo.setSkip(Long.valueOf(skip));
        return pagingRespVo;
      }else {
        log.error("search api list record from es error {}",searchResult.getErrorMessage());
        throw new InternalBizException(ErrorCode.INTERNAL_SERVER_ERROR);
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new InternalBizException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }
}
