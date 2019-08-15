package com.csci.cloud.admin.config.bodyadvice;

import com.csci.cloud.admin.data.vo.ResponseVo;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by ben on 10/09/2018. 6:14 PM 统一处理返回结果
 * <p>
 *
 * benkris1@126.com
 */
@ControllerAdvice
public class CustomerResponseBody implements ResponseBodyAdvice {

  @Override
  public boolean supports(MethodParameter methodParameter, Class aClass) {

    return !methodParameter.getParameterType().equals(ResponseEntity.class);
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
      Class clazz, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

    if(body instanceof ResponseVo) {
      return body;
    }
    return  ResponseVo.success(body);
  }

}
