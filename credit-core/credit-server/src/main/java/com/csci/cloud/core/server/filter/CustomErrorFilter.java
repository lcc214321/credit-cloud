package com.csci.cloud.core.server.filter;

import com.csci.cloud.auth.client.exception.CloudAuthClientException;
import com.csci.cloud.core.common.ErrorEnums;
import com.csci.cloud.core.common.exception.BaseCloudException;
import com.csci.cloud.core.common.utils.JsonUtils;
import com.csci.cloud.core.server.data.vo.ResponseVo;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
@Slf4j
public class CustomErrorFilter extends SendErrorFilter {

  private static final Logger LOG = LoggerFactory.getLogger(CustomErrorFilter.class);

  @Override
  public String filterType() {
    return "error";
  }

  @Override
  public int filterOrder() {
    return -1; // Needs to run before SendErrorFilter which has filterOrder == 0
  }

  @Override
  public boolean shouldFilter() {
    // only forward to errorPath if it hasn't been forwarded to already
    RequestContext ctx = RequestContext.getCurrentContext();
    return null != ctx.getThrowable() || true;
  }

  @Override
  public Object run() {
    try {

      RequestContext ctx = RequestContext.getCurrentContext();
      log.info(" ---->>>> start custom error filter {}", ctx.getRequest().getRequestURI());
      Throwable throwable = ctx.getThrowable();

      if (throwable != null && throwable instanceof ZuulException) {
        ExceptionHolder exceptionHolder = findZuulException(ctx.getThrowable());

        // Remove error code to prevent further error handling in follow up filters
        ctx.remove("throwable");

        // Populate context with new response values

        Throwable cause = ExceptionUtils.getRootCause(exceptionHolder.getThrowable());
        if (cause instanceof BaseCloudException) {
          BaseCloudException baseCloudException = (BaseCloudException) cause;
          String s = JsonUtils.toJson(ResponseVo.error(baseCloudException.getErrorMessage(),
              baseCloudException.getErrorCode(),
              baseCloudException.getData()));
          ctx.setResponseBody(s);
          if (cause instanceof CloudAuthClientException) {
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
          } else {
            ctx.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
          }
        } else {
          log.error(ExceptionUtils.getStackTrace(exceptionHolder.getThrowable()));
          String result = JsonUtils.toJson(ResponseVo.error(exceptionHolder.getErrorCause(),
              ErrorEnums.CommonErrorEnum.INTERNAL_SERVER_ERROR.getCode()));
          ctx.setResponseBody(result);
          ctx.setResponseStatusCode(HttpStatus.BAD_GATEWAY.value());
        }
      }
      ctx.setSendZuulResponse(false);
      ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    } catch (Exception ex) {
      LOG.error("Exception filtering in custom error filter", ex);
      ReflectionUtils.rethrowRuntimeException(ex);
    }
    return null;
  }

}
