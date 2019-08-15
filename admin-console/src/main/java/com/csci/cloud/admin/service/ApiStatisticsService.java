package com.csci.cloud.admin.service;

import com.csci.cloud.admin.data.vo.ApiStatisticsVo;
import com.csci.cloud.admin.data.vo.ApiUsageRecordVo;
import com.csci.cloud.admin.data.vo.PagingRespVo;
import com.csci.cloud.admin.utils.ConfigEnums.StatisticsInternalEnum;
import java.util.Date;
import java.util.List;

/**
 * Created by ben on 2018/9/19. benkris1@126.com
 * API统计相关.
 */
public interface ApiStatisticsService {

   /**
    * 按照时间间隔统计API使用量.
    * @param userId
    * @param startTime
    * @param endTime
    * @param internalEnum
    * @return
    */
   List<ApiStatisticsVo> statistics(int userId, Date startTime, Date endTime,
                                    StatisticsInternalEnum internalEnum);

   /**
    * 获取API调用列表.
    * @param userId 用户的Id
    * @param seqNum 调用编号
    * @param type 接口类型
    * @param apiId 接口编号
    * @param apiName 接口名称
    * @param startTime 开始时间
    * @param endTime 结束时间.
    * @param order 排序字段.
    * @return
    */
   PagingRespVo<ApiUsageRecordVo> list(int userId, String seqNum, Integer type, String apiId,
                                       String apiName, Date startTime, Date endTime, int skip, int limit, String order);
}
