package com.csci.cloud.admin.data.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 重置密码.
 * Created by ben on 2018/9/11. benkris1@126.com
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel
@ToString
public class RestPasswordReqVo {

  /**
   * 原始密码.
   */
  @ApiModelProperty(value = "原始密码", required = true)
  @NotEmpty
  private String oldPassword;

  /**
   * 新密码.
   */
  @ApiModelProperty(value = "新密码", required = true)
  @NotEmpty
  @Pattern(regexp = "(^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$)",message = "6-16位数字和字母的组合")
  private String newPassword;


}
