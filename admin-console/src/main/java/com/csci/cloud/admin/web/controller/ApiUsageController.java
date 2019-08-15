package com.csci.cloud.admin.web.controller;

import com.csci.cloud.admin.data.vo.ApiStatisticsVo;
import com.csci.cloud.admin.data.vo.ApiUsageRecordVo;
import com.csci.cloud.admin.data.vo.PagingRespVo;
import com.csci.cloud.admin.exception.AppBizException;
import com.csci.cloud.admin.service.ApiStatisticsService;
import com.csci.cloud.admin.service.UserService;
import com.csci.cloud.admin.utils.ConfigEnums.StatisticsInternalEnum;
import com.csci.cloud.admin.utils.ErrorCode;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ben on 2018/9/12. benkris1@126.com
 */
@RestController
@RequestMapping(value = "/api/v1/usage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "API 用量统计相关", tags = {"API 用量统计相关"})
@Slf4j
public class ApiUsageController {

  @Autowired
  private ApiStatisticsService apiStatisticsService;

  @Autowired
  private UserService userService;

  @ApiOperation(value = "查询API使用记录", notes = "")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public PagingRespVo<ApiUsageRecordVo> listApiUsage(
      @ApiParam(value = "调用编号") @RequestParam(name = "seqNum", required = false) String seqNum,
      @ApiParam(value = "接口类型. 0: 区块链 1:企业征信 2:个人征信") @RequestParam(name = "type", required = false) Integer type,
      @ApiParam(value = "接口编号") @RequestParam(name = "apiId", required = false) String apiId,
      @ApiParam(value = "接口名称") @RequestParam(name = "apiName", required = false) String apiName,
      @ApiParam(value = "开始时间 yyyy-MM-dd 【2018-10-30,2018-11-05).包含开始时间") @RequestParam(name = "startTime",required = false) String startTime,
      @ApiParam(value = "结束时间 yyyy-MM-dd 【2018-10-30,2018-11-05) 不包含结束时间") @RequestParam(name = "endTime",required = false) String endTime,
      @RequestParam(name = "limit", defaultValue = "50") Integer limit,
      @RequestParam(name = "skip", defaultValue = "0") Integer skip,
      @RequestParam(value = "order", required = false) String order,
      @ApiParam(hidden = true) @RequestAttribute("userId") Integer userId) {

    Date start = null;
    Date end = null;
    try {
      if(null != startTime) {

        start = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse(startTime);
      }
      if(null != endTime) {
        end = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse(endTime);
        end = DateUtils.addMilliseconds(DateUtils.ceiling(end,Calendar.DATE), -1);//取当天最后1ms.
      }
    } catch (ParseException e) {
      throw new AppBizException(ErrorCode.DATE_FORMAT_ERROR);
    }

    return apiStatisticsService.list(userId,seqNum,type,apiId,apiName,start,end,skip,limit,order);
  }


  @ApiOperation(value = "API用量统计", notes = "")
  @RequestMapping(value = "/statistics", method = RequestMethod.GET)
  public List<ApiStatisticsVo> statistics(
      @ApiParam(value = "开始时间,格式需要和internal匹配", required = true) @RequestParam(name = "startTime") String startTime,
      @ApiParam(value = "结束时间,格式需要和internal匹配", required = true) @RequestParam(name = "endTime") String endTime,
      @ApiParam(value = "分组类型,支持可选项为:YEAR(yyyy),DAILY(yyyy-HH-dd),MONTHLY(yyyy-MM),"
          + "WEEKLY(xxxx'W'wwe),HOURLY(yyyy-MM-dd HH),MINUTE(yyyy-MM-dd HH:mm)"
          + ",SECOND(yyyy-MM-dd HH:mm:ss) ")
      @RequestParam(value = "internal", required = false, defaultValue = "DAILY") String internal,
      @ApiParam(hidden = true) @RequestAttribute("userId") Integer userId) {

    Date start = null;
    Date end = null;
    StatisticsInternalEnum internalEnum = StatisticsInternalEnum.valueOf(internal);
    try {
      start = DateTimeFormat.forPattern(internalEnum.getFormat()).parseDateTime(startTime).toDate();
      end =  DateTimeFormat.forPattern(internalEnum.getFormat()).parseDateTime(endTime).toDate();

      end = DateUtils.addMilliseconds(DateUtils.ceiling(end,internalEnum.getField() ), -1);
    } catch (Exception e) {
      throw new AppBizException(ErrorCode.DATE_FORMAT_ERROR);
    }
    return apiStatisticsService.statistics(userId, start, end, internalEnum);
  }

}
