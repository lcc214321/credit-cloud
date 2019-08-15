package com.csci.cloud.admin.exception;

import com.csci.cloud.admin.utils.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ben on 2018/9/19. benkris1@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InternalBizException extends BizException{
  private static final long serialVersionUID = 1L;
  private String errorCode;
  private String reason;

  /**
   * 构造异常.
   * @param errorCode 错误代码.
   * @param reason 错误信息.
   */
  public InternalBizException(String errorCode, String reason) {
    super(reason);
    this.errorCode = errorCode;
    this.reason = reason;
  }

  /**
   * 构造异常.
   * @param errorCode 错误代码。
   */
  public InternalBizException(ErrorCode errorCode) {
    super(errorCode.getDesc());
    this.errorCode = errorCode.getCode();
    this.reason = errorCode.getDesc();
  }
}
