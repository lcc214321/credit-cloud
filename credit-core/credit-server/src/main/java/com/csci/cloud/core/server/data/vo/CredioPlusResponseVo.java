package com.csci.cloud.core.server.data.vo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ApiModel
@ToString
public class CredioPlusResponseVo<T> {

  private String result;
  private String errorCode;
  private String errorMsg;
  private T content;

}
