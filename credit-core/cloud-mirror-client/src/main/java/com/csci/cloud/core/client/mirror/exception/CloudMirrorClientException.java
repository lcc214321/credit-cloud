package com.csci.cloud.core.client.mirror.exception;

import com.csci.cloud.core.common.ErrorEnums.ErrorCode;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.Data;

@Data
public class CloudMirrorClientException extends HystrixBadRequestException {

  protected String errorCode;
  protected String errorMessage;
  protected Object data;

  /**
   * 云镜错误异常.
   * @param code .
   * @param errorMessage .
   * @param data .
   */
  public CloudMirrorClientException(String code, String errorMessage, Object data) {
    super(errorMessage);
    this.errorCode = code;
    this.errorMessage = errorMessage;
    this.data = data;
  }

  /**
   * 云镜错误异常.
   * @param errorCode 。
。   */
  public CloudMirrorClientException(ErrorCode errorCode) {
    super(errorCode.getDesc());
    this.errorCode = errorCode.getCode();
    this.errorMessage = errorCode.getDesc();
  }
}
