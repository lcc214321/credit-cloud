package com.csci.cloud.admin.exception;

import com.csci.cloud.admin.utils.ErrorCode;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ben on 2018/9/17. benkris1@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthenticationException extends BizException implements Serializable {

  private String errorCode;
  private String reason;

  public AuthenticationException(String message) {
    super(message);
  }

  /**
   * 构造异常.
   * @param errorCode 错误代码。
   */
  public AuthenticationException(ErrorCode errorCode) {
    super(errorCode.getDesc());
    this.errorCode = errorCode.getCode();
    this.reason = errorCode.getDesc();
  }
}
