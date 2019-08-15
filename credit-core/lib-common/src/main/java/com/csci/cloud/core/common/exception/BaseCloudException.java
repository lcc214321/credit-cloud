package com.csci.cloud.core.common.exception;

import lombok.Data;

/**
 * Created by ben on 2018/9/26. benkris1@126.com
 */
@Data
public abstract class BaseCloudException extends RuntimeException {

  protected String errorCode;
  protected String errorMessage;
  protected Object data;

  public BaseCloudException(String errorMessage) {
    super(errorMessage);
  }


  /**
   * 业务异常基类.
   * @param errorMessage .
   * @param code .
   */
  public BaseCloudException(String errorMessage, String code) {
    super(errorMessage);
    this.errorMessage = errorMessage;
    this.errorCode = code;
  }

  /**
   * 业务异常基类.
   * @param errorMessage .
   * @param code .
   * @param  data .
   */
  public BaseCloudException(String code, String errorMessage, Object data) {
    super(errorMessage);
    this.errorCode = code;
    this.errorMessage = errorMessage;
    this.data = data;
  }


}
