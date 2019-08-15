package com.csci.cloud.auth.server.exception;

import com.csci.cloud.core.common.ErrorEnums.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ben on 2018/9/27. benkris1@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthBizException extends BizException {

  protected String errorCode;
  protected String errorMessage;

  public AuthBizException(String errorMessage) {
    super(errorMessage);
    this.errorMessage = errorMessage;
  }

  /**
   * 构造异常.
   * @param errorCode 错误代码.
   * @param reason 错误信息.
   */
  public AuthBizException(String errorCode, String reason) {
    super(reason);
    this.errorCode = errorCode;
    this.errorMessage = reason;
  }
  /**
   * 构造异常.
   * @param errorCode 错误代码.
   */
  public AuthBizException(ErrorCode errorCode) {
    super(errorCode.getDesc());
    this.errorCode = errorCode.getCode();
    this.errorMessage = errorCode.getDesc();
  }

}
