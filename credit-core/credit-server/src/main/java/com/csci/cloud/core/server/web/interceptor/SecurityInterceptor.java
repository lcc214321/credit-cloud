package com.csci.cloud.core.server.web.interceptor;

import com.csci.cloud.auth.client.AuthClient;
import com.csci.cloud.auth.client.exception.CloudAuthClientException;
import com.csci.cloud.auth.client.model.res.AuthResponse;
import com.csci.cloud.auth.common.data.AuthenticationReqVo;
import com.csci.cloud.auth.common.data.AuthenticationRespVo;
import com.csci.cloud.auth.common.data.ConfigServerVo;
import com.csci.cloud.core.common.ErrorEnums.CommonErrorEnum;
import com.csci.cloud.core.common.ErrorEnums.ErrorCode;
import com.csci.cloud.core.common.enums.ServiceTypeEnum;
import com.csci.cloud.core.server.exception.handler.ParameterBizException;
import com.csci.cloud.core.server.utils.ApiDefinesEnum;
import com.csci.cloud.core.server.web.annotation.ApiDefine;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ben on 2018/9/26. benkris1@126.com 验证接口访问权限. 放到AppKeyAuthorizationFilter里面做
 */
@Deprecated
@Configuration
public class SecurityInterceptor implements HandlerInterceptor {

  @Autowired
  private AuthClient authClient;


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String appKey = request.getHeader("apiKey");
    String ts = request.getHeader("timestamp");
    String sign = request.getHeader("sign");
    if (StringUtils.isBlank(appKey) || StringUtils.isBlank(ts) || StringUtils.isBlank(sign)) {
      throw new ParameterBizException(CommonErrorEnum.HEADER_AUTH_ERROR);
    }
    String uri = request.getRequestURI();
    Map<String, String> queryMap = MapUtils
        .transformedMap(request.getParameterMap(), null, input -> {
          String[] values = (String[]) input;
          return values[0];
        });

    AuthenticationReqVo reqVo = AuthenticationReqVo.builder()
        .apiKey(appKey)
        .uri(uri)
        .sign(sign)
        .timestamp(Long.valueOf(ts))
        .queryMap(queryMap)
        .build();

    HandlerMethod handlerMethod = (HandlerMethod) handler;
    ApiDefine apiDefine = handlerMethod.getMethodAnnotation(ApiDefine.class);
    ApiDefinesEnum apiDefinesEnum = apiDefine.value();
    if (null != apiDefine && null != apiDefinesEnum.getServerType()) {
      reqVo.setServerType(apiDefinesEnum.getServerType().getValue());
    }

    AuthResponse<AuthenticationRespVo> authentication = authClient.authentication(reqVo);
    if (null != authentication.getData()) {
      AuthenticationRespVo authenticationRespVo = authentication.getData();
      request.setAttribute("tenantId", authenticationRespVo.getTenantId());
      request.setAttribute("appId", authenticationRespVo.getAppId());
      request.setAttribute("companyName", authenticationRespVo.getCompanyName());
      request.setAttribute("companyCode", authenticationRespVo.getCompanyCode());
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {

  }
}
