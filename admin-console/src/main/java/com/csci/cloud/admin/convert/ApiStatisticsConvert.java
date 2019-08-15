package com.csci.cloud.admin.convert;

import com.csci.cloud.admin.data.vo.ApiStatisticsVo;
import com.csci.cloud.admin.data.vo.ApiStatisticsVo.ApiStatisticsItem;
import com.csci.cloud.admin.data.vo.ApiUsageRecordVo;
import com.csci.cloud.admin.data.vo.PagingRespVo;
import com.csci.cloud.admin.utils.ConfigEnums.StatisticsInternalEnum;
import com.csci.cloud.admin.utils.ApiStatisticsConstants;
import com.csci.cloud.core.common.enums.ServiceTypeEnum;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.core.search.aggregation.HistogramAggregation;
import io.searchbox.core.search.aggregation.HistogramAggregation.Histogram;
import io.searchbox.core.search.aggregation.MetricAggregation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * Created by ben on 2018/9/19. benkris1@126.com
 */
public class ApiStatisticsConvert {



  public static List<ApiStatisticsVo> convertApiStatistics(StatisticsInternalEnum internalEnum, SearchResult searchResult) {
    MetricAggregation aggregations = searchResult.getAggregations();
    HistogramAggregation histogramAggregation = aggregations
        .getHistogramAggregation(ApiStatisticsConstants.GROUP_BY_INTERNAL_API);
    List<Histogram> buckets = histogramAggregation.getBuckets();

    return buckets.stream().map(histogram -> {
      ApiStatisticsVo apiStatisticsVo = new ApiStatisticsVo();
      apiStatisticsVo.setInternal(internalEnum.name());
      apiStatisticsVo.setTimePoint(new DateTime(histogram.getKey()).toString(internalEnum.getFormat()));
      apiStatisticsVo.setTotal(histogram.getCount());

      List<ApiStatisticsItem> apiStatisticsItems = CollectionUtils
          .emptyIfNull(histogram.getTermsAggregation(ApiStatisticsConstants.GROUP_BY_API_TYPE)
              .getBuckets()).stream()
          .map(item -> ApiStatisticsItem.builder()
              .count(item.getCount())
              .serverType(Integer.valueOf(item.getKey()))
              .build())
          .collect(Collectors.toList());
      if (apiStatisticsItems.size() < ServiceTypeEnum.values().length) {
        Map<Integer, ApiStatisticsItem> apiStatisticsItemMap = apiStatisticsItems.stream()
            .collect(Collectors.toMap(ApiStatisticsItem::getServerType, a -> a, (k1, k2) -> k1));
        for(ServiceTypeEnum serviceTypeEnum : ServiceTypeEnum.values()) {
           if (!apiStatisticsItemMap.containsKey(serviceTypeEnum.getValue())) {
             apiStatisticsItems.add(ApiStatisticsItem.builder()
                 .count(0L)
                 .serverType(serviceTypeEnum.getValue())
                 .build());
           }
        }
      }
      apiStatisticsVo.setItems(apiStatisticsItems);
      return apiStatisticsVo;
    }).collect(Collectors.toList());
  }

  public static PagingRespVo<ApiUsageRecordVo> convertApiList(SearchResult searchResult){
    PagingRespVo<ApiUsageRecordVo> pagingRespVo = new PagingRespVo<>();
    pagingRespVo.setTotalCount(searchResult.getTotal());
    List<Hit<ApiUsageRecordVo, Void>> hits = searchResult.getHits(ApiUsageRecordVo.class);

    List<ApiUsageRecordVo> apiUsageRecordVos = hits.stream().map(hit -> hit.source).collect(
        Collectors.toList());

    pagingRespVo.setContent(apiUsageRecordVos);
    return pagingRespVo;
  }
}
