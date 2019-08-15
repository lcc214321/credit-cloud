package com.csci.cloud.auth.client.exception;


import com.csci.cloud.core.common.ErrorEnums.ErrorCode;
import com.csci.cloud.core.common.exception.BaseCloudException;

public class CloudAuthClientException extends BaseCloudException {

  public CloudAuthClientException(String message) {
    super(message);
  }

  public CloudAuthClientException(String message, String code) {
    super(message, code);
  }

  public CloudAuthClientException(String code, String message, Object data) {
    super(code, message, data);
  }


  public CloudAuthClientException(ErrorCode errorCode) {
    super(errorCode.getDesc());
    this.errorCode = errorCode.getCode();
    this.errorMessage = errorCode.getDesc();
  }
}
