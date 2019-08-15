package com.csci.cloud.core.server.config.bodyadvice;

import com.csci.cloud.core.client.mirror.FraudMirrorResponse;
import com.csci.cloud.core.server.data.vo.ResponseVo;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by ben on 10/09/2018. 6:14 PM 统一处理返回结果.
 * benkris1@126.com
 */
@ControllerAdvice
public class CustomerResponseBody implements ResponseBodyAdvice {

  @Override
  public boolean supports(MethodParameter methodParameter, Class clazz) {

    return !methodParameter.getParameterType().equals(ResponseEntity.class);
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
      Class clazz, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

    if (body instanceof ResponseVo) {
      return body;
    }
    if (body instanceof FraudMirrorResponse) {
      FraudMirrorResponse cloudMirrorResponse = (FraudMirrorResponse) body;
      return ResponseVo.success(cloudMirrorResponse.getRuleResult());

    }
    return ResponseVo.success(body);
  }

}
