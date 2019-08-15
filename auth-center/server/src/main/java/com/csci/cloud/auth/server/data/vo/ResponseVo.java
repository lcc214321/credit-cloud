package com.csci.cloud.auth.server.data.vo;

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
  @JsonIgnore
  private static final ResponseVo SUCCESS = new ResponseVo(SUCCESS_RESULT);

  @ApiModelProperty(value = "成功：SUCCESS，失败：ERROR", required = true, example = SUCCESS_RESULT)
  private String code;
  @ApiModelProperty(value = "错误信息")
  private String errorMessage;
  @ApiModelProperty(value = "返回业务数据")
  private T data;



  public ResponseVo(String result) {
    this.code = result;
  }

  public static ResponseVo success() {
    return SUCCESS;
  }

  /**
   * 组装返回成功的结果.
   * @param content .
   * @param <T> .
   * @return
   */
  public static <T> ResponseVo success(T content) {
    ResponseVo<T> responseVo = new ResponseVo();
    responseVo.setCode(CommonErrorEnum.SUCCESS.getCode());
    responseVo.setData(content);
    return responseVo;
  }

  public static ResponseVo error(String errorMsg,String code) {
    return error(errorMsg, code, null);
  }

  /**
   * 组装返回失败的结果.
   * @param errorMsg .
   * @param content .
   * @param <T> .
   * @return
   */
  public static <T> ResponseVo error(String errorMsg, String code,T content) {
    ResponseVo<T> responseVo = new ResponseVo<>();
    responseVo.setErrorMessage(errorMsg);
    responseVo.setCode(code);
    responseVo.setData(content);
    return responseVo;
  }

}
