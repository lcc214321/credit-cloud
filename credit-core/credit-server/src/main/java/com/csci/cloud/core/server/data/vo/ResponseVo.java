package com.csci.cloud.core.server.data.vo;

import com.csci.cloud.core.common.ErrorEnums.CommonErrorEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ApiModel
@ToString
public class ResponseVo<T> {

  @JsonIgnore
  public static final String SUCCESS_RESULT = "SUCCESS";
  @JsonIgnore
  public static final String ERROR_RESULT = "ERROR";

  @ApiModelProperty(value = "成功：SUCCESS，失败：ERROR", required = true, example = SUCCESS_RESULT)
  private String code;

  @ApiModelProperty(value = "错误信息")
  private String errorMessage;

  @ApiModelProperty(value = "返回业务数据")
  private T data;

  @ApiModelProperty(value = "是否成功")
  private Boolean success;

  /**
   * 组装返回成功的结果.
   *
   * @param content .
   * @param <T> .
   */
  public static <T> ResponseVo success(T content) {
    ResponseVo<T> responseVo = new ResponseVo<>();
    responseVo.setCode(CommonErrorEnum.SUCCESS.getCode());
    responseVo.setData(content);
    responseVo.setSuccess(true);
    return responseVo;
  }

  public static ResponseVo error(String errorMsg, String code) {
    return error(errorMsg, code, null);
  }

  /**
   * 组装返回失败的结果.
   *
   * @param errorMsg .
   * @param content .
   * @param <T> .
   */
  public static <T> ResponseVo error(String errorMsg, String code, T content) {
    ResponseVo<T> responseVo = new ResponseVo<>();
    responseVo.setErrorMessage(errorMsg);
    responseVo.setCode(code);
    responseVo.setData(content);
    responseVo.setSuccess(false);
    return responseVo;
  }

}
