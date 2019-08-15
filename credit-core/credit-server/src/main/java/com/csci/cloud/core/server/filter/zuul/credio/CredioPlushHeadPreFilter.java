package com.csci.cloud.core.server.filter.zuul.credio;

import com.csci.cloud.core.common.utils.Constants;
import com.csci.cloud.core.server.utils.ApiDefinesEnum;
import com.csci.cloud.core.server.utils.CredioConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CredioPlushHeadPreFilter extends ZuulFilter {

  @Override
  public String filterType() {
    return FilterConstants.PRE_TYPE;
  }

  @Override
  public int filterOrder() {
    return 10;
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
  public Object run() throws ZuulException {
    log.info(" ---->>>> start credio plush header pre filter ... ");

    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    String token = request.getHeader(CredioConstants.CREDIO_PLUS_CLOUD_TOKEN_HEADER);
    ApiDefinesEnum apiDefinesEnum = ApiDefinesEnum
        .getByUriAndMethod(request.getRequestURI(), request.getMethod());
    if (!ApiDefinesEnum.CREDIO_NOT_LOGIN_APS.contains(apiDefinesEnum)) {
      ctx.addZuulRequestHeader(CredioConstants.CREDIO_PLUS_SOURCE_TOKEN_HEADER, token);
    }
    ProxyRequestHelper proxyRequestHelper = new ProxyRequestHelper();
    proxyRequestHelper.addIgnoredHeaders(Constants.API_HEAD_KEY,
        Constants.API_HEAD_TIMESTAMP, Constants.API_HEAD_SIGN,
        CredioConstants.CREDIO_PLUS_CLOUD_TOKEN_HEADER);

    return null;
  }
}
