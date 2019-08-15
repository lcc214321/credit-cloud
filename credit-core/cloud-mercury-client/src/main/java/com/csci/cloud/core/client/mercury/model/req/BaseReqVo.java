package com.csci.cloud.core.client.mercury.model.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BaseReqVo {

  @ApiModelProperty(value = "企业ID")
  private String companyId;

  @ApiModelProperty(value = "统一社会信用代码")
  private String creditCode;

  @ApiModelProperty(value = "注册号")
  private String regNo;

  @ApiModelProperty(value = "组织机构代码")
  private String orgCode;

  @ApiModelProperty(value = "公司名称")
  private String companyName;
}
