package com.csci.cloud.core.server.exception.handler;

import com.csci.cloud.auth.client.exception.CloudAuthClientException;
import com.csci.cloud.core.client.mirror.exception.CloudMirrorClientException;
import com.csci.cloud.core.common.ErrorEnums.CommonErrorEnum;
import com.csci.cloud.core.common.exception.BaseCloudException;
import com.csci.cloud.core.server.data.vo.ResponseVo;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

  @Resource
  protected Environment environment;


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  @ExceptionHandler({BaseCloudException.class})
  protected ResponseVo handlerUserException(BaseCloudException e) {
    log.warn("handle biz exception 请求e:{}", e.getMessage(), e);
    ResponseVo response = ResponseVo.error(e.getMessage(), e.getErrorCode());
    return response;
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  @ExceptionHandler({CloudAuthClientException.class})
  protected ResponseVo handlerAuthException(CloudAuthClientException e) {
    log.warn("handle auth biz exception 请求e:{}", e.getMessage(), e);
    ResponseVo response = ResponseVo.error(e.getMessage(), e.getErrorCode());
    return response;
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  @ExceptionHandler({CloudMirrorClientException.class})
  protected ResponseVo handlerMirrorException(CloudMirrorClientException e) {
    log.warn("handle cloud mirror exception 请求e:{}", e.getMessage(), e);
    ResponseVo response = ResponseVo.error(e.getMessage(), e.getErrorCode());
    return response;
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  @ExceptionHandler({Exception.class})
  protected ResponseVo handlerException(Exception e) {
    log.warn("handle exception 请求e:{}", e.getMessage(), e);
    return ResponseVo.error(CommonErrorEnum.INTERNAL_SERVER_ERROR.getDesc(),
        CommonErrorEnum.INTERNAL_SERVER_ERROR.getCode());
  }

}
