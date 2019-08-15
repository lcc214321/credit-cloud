package com.csci.cloud.core.server.exception.handler;

import com.csci.cloud.core.common.ErrorEnums.ErrorCode;
import com.csci.cloud.core.common.exception.BaseCloudException;

/**
 * Created by ben on 2018/10/8. benkris1@126.com
 */
public class ParameterBizException extends BaseCloudException {

  public ParameterBizException(String message) {
    super(message);
  }

  public ParameterBizException(String message, String code) {
    super(message, code);
  }

  public ParameterBizException(String code, String message, Object data) {
    super(code, message, data);
  }

  /**
   * 参数错误异常.
   * @param errorCode .
   */
  public ParameterBizException(ErrorCode errorCode) {
    super(errorCode.getDesc());
    this.errorCode = errorCode.getCode();
    this.errorMessage = errorCode.getDesc();
  }
}
