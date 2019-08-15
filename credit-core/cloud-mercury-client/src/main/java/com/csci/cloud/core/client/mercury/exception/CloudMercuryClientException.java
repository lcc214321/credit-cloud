package com.csci.cloud.core.client.mercury.exception;

import com.csci.cloud.core.common.ErrorEnums.ErrorCode;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.Data;

@Data
public class CloudMercuryClientException extends HystrixBadRequestException {

  protected String errorCode;
  protected String errorMessage;
  protected Object data;

  /**
   * 企业征信异常.
   * @param code .
   * @param errorMessage .
   * @param data .
   */
  public CloudMercuryClientException(String code, String errorMessage, Object data) {
    super(errorMessage);
    this.errorCode = code;
    this.errorMessage = errorMessage;
    this.data = data;
  }

  /**
   * 企业征信异常.
   * @param errorCode .
   */
  public CloudMercuryClientException(ErrorCode errorCode) {
    super(errorCode.getDesc());
    this.errorCode = errorCode.getCode();
    this.errorMessage = errorCode.getDesc();
  }
}
