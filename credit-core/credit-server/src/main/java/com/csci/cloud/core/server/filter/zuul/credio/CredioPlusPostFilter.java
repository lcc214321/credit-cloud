package com.csci.cloud.core.server.filter.zuul.credio;

import static com.csci.cloud.core.server.utils.StreamConstants.SENDING_MQ_TIMEOUT_MS;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

import com.csci.cloud.core.common.ErrorEnums;
import com.csci.cloud.core.common.utils.GzipUtils;
import com.csci.cloud.core.common.utils.JsonUtils;
import com.csci.cloud.core.common.utils.SnowFlake;
import com.csci.cloud.core.server.data.dto.ApiUsageRecordDto;
import com.csci.cloud.core.server.data.vo.CredioPlusResponseVo;
import com.csci.cloud.core.server.data.vo.ResponseVo;
import com.csci.cloud.core.server.stream.ApiStatisticsStreamChannel;
import com.csci.cloud.core.server.utils.ApiDefinesEnum;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.zip.GZIPInputStream;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

/**
 * 1.转换区块链接口返回格式. 2.错误码匹配. 3.调用记录保存.
 */
@Component
@Slf4j
public class CredioPlusPostFilter extends ZuulFilter {

  @Autowired
  private ApiStatisticsStreamChannel streamChannel;

  @Autowired
  private SnowFlake snowFlake;

  @Override
  public String filterType() {
    return FilterConstants.POST_TYPE;
  }

  @Override
  public int filterOrder() {
    return 15;
  }

  @Override
  public boolean shouldFilter() {
    RequestContext ctx = RequestContext.getCurrentContext();
    if ((ctx.get("proxy") != null) && ctx.get("proxy").equals("credio-plus")) {
      return true;
    }
    return false;
  }

  @Override
  public Object run() {
    try {

      log.info(" ---->>>> start credio plush filter ... ");
      RequestContext ctx = RequestContext.getCurrentContext();
      /*
     if (!HttpStatus.valueOf(ctx.getResponseStatusCode()).is2xxSuccessful()) {
        return null;
      }*/
      //映射错误码&返回格式.
      HttpServletRequest request = ctx.getRequest();
      Integer tenantId = (Integer) request.getAttribute("tenantId");
      String companyCode = (String) request.getAttribute("companyCode");
      String companyName = (String) request.getAttribute("companyName");
      Integer appId = (Integer) request.getAttribute("appId");
      Long startTs =
          null != request.getAttribute("startTs") ? ((Number) request.getAttribute("startTs"))
              .longValue() : System.currentTimeMillis();

      long duration = System.currentTimeMillis() - startTs;
      ApiDefinesEnum apiDefinesEnum = ApiDefinesEnum
          .getByUriAndMethod(request.getRequestURI(), request.getMethod());
      Date now = new Date();
      ApiUsageRecordDto.ApiUsageRecordDtoBuilder builder = ApiUsageRecordDto.builder();
      builder.createdAt(now)
          .seqNum(snowFlake.nextId() + "")
          .apiId(apiDefinesEnum.getApiId())
          .apiName(apiDefinesEnum.getApiName())
          .tenantId(tenantId)
          .appId(appId)
          .duration(duration)
          .companyCode(companyCode)
          .companyName(companyName)
          .type(apiDefinesEnum.getServerType().getValue());

      InputStream stream = ctx.getResponseDataStream();
      String body;
      if (ctx.getResponseGZipped() && null != stream) {
        body = StreamUtils.copyToString(new GZIPInputStream(stream),
            Charset.forName("UTF-8"));
      } else if(null != stream){
        body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
      }else {
        body = ctx.getResponseBody();
      }
      CredioPlusResponseVo<?> credioPlusResponseVo = StringUtils.isNotBlank(body) ? JsonUtils
          .toObj(body, CredioPlusResponseVo.class) : null;
      if (null != credioPlusResponseVo
          && null != credioPlusResponseVo.getResult()) {
        ResponseVo responseVo = null;
        if (credioPlusResponseVo.getResult().equalsIgnoreCase("success")) {
          responseVo = responseVo.success(credioPlusResponseVo.getContent());

        } else {
          responseVo = ResponseVo.error(credioPlusResponseVo.getErrorMsg(),
              ErrorEnums.CredioPushErrorEnum.getBySourceCode(credioPlusResponseVo.getErrorCode())
                  .getCode(),
              credioPlusResponseVo.getContent());
        }

        if (null != responseVo) {
          builder.resultCode(responseVo.getCode()).resultMessage(responseVo.getErrorMessage());
        }
        body = JsonUtils.toJson(responseVo);
        // 发送访问记录到MQ.
        Message<ApiUsageRecordDto> m = MessageBuilder.withPayload(builder.build()).build();
        streamChannel.apiRecordInput().send(m, SENDING_MQ_TIMEOUT_MS);
      }

      if (ctx.getResponseGZipped() && StringUtils.isNotBlank(body)) {
        ctx.setResponseDataStream(GzipUtils.gzip(body));
      } else {
        ctx.setResponseBody(body);
      }

    } catch (Exception e) {
      rethrowRuntimeException(e);
    }
    return null;
  }

}
