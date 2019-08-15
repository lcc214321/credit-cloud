package com.csci.cloud.admin.exception;

import com.csci.cloud.admin.utils.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppBizException extends BizException {


  private static final long serialVersionUID = 1L;
  private String errorCode;
  private String reason;

  public AppBizException(String reason) {
    super(reason);
    this.reason = reason;
  }

  /**
   * 构造异常.
   * @param errorCode 错误代码.
   * @param reason 错误信息.
   */
  public AppBizException(String errorCode, String reason) {
    super(reason);
    this.errorCode = errorCode;
    this.reason = reason;
  }

  /**
   * 构造异常.
   * @param errorCode 错误代码。
   */
  public AppBizException(ErrorCode errorCode) {
    super(errorCode.getDesc());
    this.errorCode = errorCode.getCode();
    this.reason = errorCode.getDesc();
  }
}
