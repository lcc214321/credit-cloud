package com.csci.cloud.admin.captcha.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.Data;

@Data
public class CaptchaClientException extends HystrixBadRequestException {

  protected String errorCode;
  protected String errorMessage;
  protected Object data;

  /**
   * 验证码异常.
   * @param code .
   * @param errorMessage .
   */
  public CaptchaClientException(String code, String errorMessage) {
    super(errorMessage);
    this.errorCode = code;
    this.errorMessage = errorMessage;
    this.data = data;
  }

}
