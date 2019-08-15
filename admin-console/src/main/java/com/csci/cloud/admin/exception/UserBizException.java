package com.csci.cloud.admin.exception;
import com.csci.cloud.admin.utils.ErrorCode;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserBizException extends BizException implements Serializable {


  private static final long serialVersionUID = 1L;
  private String errorCode;
  private String reason;

  public UserBizException(String reason) {
    super(reason);
    this.reason = reason;
  }

  /**
   * 构造异常.
   * @param errorCode 错误代码.
   * @param reason 错误信息.
   */
  public UserBizException(String errorCode, String reason) {
    super(reason);
    this.errorCode = errorCode;
    this.reason = reason;
  }

  /**
   * 构造异常.
   * @param errorCode 错误代码。
   */
  public UserBizException(ErrorCode errorCode) {
    super(errorCode.getDesc());
    this.errorCode = errorCode.getCode();
    this.reason = errorCode.getDesc();
  }
}
