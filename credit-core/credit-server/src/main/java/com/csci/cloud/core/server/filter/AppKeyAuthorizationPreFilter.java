package com.csci.cloud.core.server.filter;

import com.csci.cloud.auth.client.AuthClient;
import com.csci.cloud.auth.client.model.res.AuthResponse;
import com.csci.cloud.auth.common.data.AuthenticationReqVo;
import com.csci.cloud.auth.common.data.AuthenticationRespVo;
import com.csci.cloud.core.common.ErrorEnums;
import com.csci.cloud.core.common.utils.Constants;
import com.csci.cloud.core.common.utils.JsonUtils;
import com.csci.cloud.core.server.exception.handler.ParameterBizException;
import com.csci.cloud.core.server.utils.ApiDefinesEnum;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.TransformedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppKeyAuthorizationPreFilter extends ZuulFilter {

  @Autowired
  private AuthClient authClient;


  @Override
  public String filterType() {
    return FilterConstants.PRE_TYPE;
  }

  @Override
  public int filterOrder() {
    return 5;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {

    log.info(" ---->>>> start authorization filter ... ");

    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    // log.info(JsonUtils.toJson(request.getParameterMap()));
    String appKey = request.getHeader(Constants.API_HEAD_KEY);
    String ts = request.getHeader(Constants.API_HEAD_TIMESTAMP);
    String sign = request.getHeader(Constants.API_HEAD_SIGN);
    if (StringUtils.isBlank(appKey) || StringUtils
        .isBlank(ts) || StringUtils.isBlank(sign)) {
      throw new ParameterBizException(ErrorEnums.CommonErrorEnum.HEADER_AUTH_ERROR);
    }
    String uri = request.getRequestURI();
    Map<String, String> queryMap = Maps.transformValues(
        null != ctx.getRequestQueryParams() ? ctx.getRequestQueryParams() : Maps.newHashMap(),
        new Function<List<String>, String>() {
          @Nullable
          @Override
          public String apply(@Nullable List<String> values) {
            return CollectionUtils.isNotEmpty(values) ? values.get(0) :"";
          }
        }
    );

    AuthenticationReqVo reqVo = AuthenticationReqVo.builder()
        .apiKey(appKey)
        .uri(uri)
        .sign(sign)
        .timestamp(Long.valueOf(ts))
        .queryMap(queryMap)
        .build();

    ApiDefinesEnum apiDefinesEnum = ApiDefinesEnum
        .getByUriAndMethod(request.getRequestURI(), request.getMethod());
    if (ApiDefinesEnum.UNKNOWN != apiDefinesEnum) {
      reqVo.setServerType(apiDefinesEnum.getServerType().getValue());
    }

    AuthResponse<AuthenticationRespVo> authentication = authClient.authentication(reqVo);
    if (null != authentication.getData()) {
      AuthenticationRespVo authenticationRespVo = authentication.getData();
      request.setAttribute("tenantId", authenticationRespVo.getTenantId());
      request.setAttribute("appId", authenticationRespVo.getAppId());
      request.setAttribute("companyName", authenticationRespVo.getCompanyName());
      request.setAttribute("companyCode", authenticationRespVo.getCompanyCode());
      request.setAttribute("startTs",System.currentTimeMillis());
    }
    return null;
  }
}
