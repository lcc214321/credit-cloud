package com.csci.cloud.core.server.web.aop;

import static com.csci.cloud.core.server.utils.StreamConstants.SENDING_MQ_TIMEOUT_MS;

import com.csci.cloud.core.common.ErrorEnums.CommonErrorEnum;
import com.csci.cloud.core.common.exception.BaseCloudException;
import com.csci.cloud.core.common.utils.SnowFlake;
import com.csci.cloud.core.server.data.dto.ApiUsageRecordDto;
import com.csci.cloud.core.server.data.dto.ApiUsageRecordDto.ApiUsageRecordDtoBuilder;
import com.csci.cloud.core.server.stream.ApiStatisticsStreamChannel;
import com.csci.cloud.core.server.utils.ApiDefinesEnum;
import com.csci.cloud.core.server.web.annotation.ApiDefine;
import java.lang.reflect.Method;
import java.util.Date;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by ben on 2018/9/27. benkris1@126.com
 */
@Order(1)
@Aspect
@Component
public class ApiUsageAspect {

  @Autowired
  private ApiStatisticsStreamChannel streamChannel;

  @Autowired
  private SnowFlake snowFlake;

  /**
   * 记录接口使用记录.
   *
   * @param pjp .
   * @return .
   * @throws Throwable .
   */
  @Around("executePointcut()")
  public Object requestRpcException(ProceedingJoinPoint pjp) throws Throwable {

    ServletRequestAttributes servletRequestAttributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    Integer tenantId = (Integer) servletRequestAttributes.getRequest().getAttribute("tenantId");
    String companyCode = (String) servletRequestAttributes.getRequest().getAttribute("companyCode");
    String companyName = (String) servletRequestAttributes.getRequest().getAttribute("companyName");
    Integer appId = (Integer) servletRequestAttributes.getRequest().getAttribute("appId");

    MethodSignature signature = (MethodSignature) pjp.getSignature();
    Method method = signature.getMethod();
    ApiDefine annotation = method.getAnnotation(ApiDefine.class);
    if (null == annotation) {
      return pjp.proceed();
    }
    ApiDefinesEnum apiDefinesEnum = annotation.value();
    Date now = new Date();
    ApiUsageRecordDtoBuilder builder = ApiUsageRecordDto.builder();
    builder.createdAt(now)
        .seqNum(snowFlake.nextId() + "")
        .apiId(apiDefinesEnum.getApiId())
        .apiName(apiDefinesEnum.getApiName())
        .tenantId(tenantId)
        .appId(appId)
        .companyCode(companyCode)
        .companyName(companyName)
        .resultCode(CommonErrorEnum.SUCCESS.getCode())
        .resultMessage(CommonErrorEnum.SUCCESS.getDesc())
        .type(apiDefinesEnum.getServerType().getValue());
    long start = System.currentTimeMillis();
    try {
      return pjp.proceed();
    } catch (BaseCloudException e) {
      builder.resultCode(e.getErrorCode()).resultMessage(e.getErrorMessage());
      throw e;
    } catch (Throwable e) {
      builder.resultCode(CommonErrorEnum.UNKNOWN_ERROR.getCode())
          .resultMessage(CommonErrorEnum.UNKNOWN_ERROR.getDesc());
      throw e;
    } finally {
      long end = System.currentTimeMillis();
      ApiUsageRecordDto apiUsageRecordDto = builder.duration((end - start))
          .build();
      Message<ApiUsageRecordDto> m = MessageBuilder.withPayload(apiUsageRecordDto).build();
      streamChannel.apiRecordInput().send(m, SENDING_MQ_TIMEOUT_MS);
    }
  }

  @Pointcut("execution(* com.csci.cloud.core.server.web.controller..*.*(..))")
  protected void executePointcut() {
  }

}
