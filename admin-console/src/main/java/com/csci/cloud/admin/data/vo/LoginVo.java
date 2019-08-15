package com.csci.cloud.admin.data.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 */
@ApiModel
@Setter
@Getter
@NoArgsConstructor
@ToString
public class LoginVo {

  @NotBlank
  @ApiModelProperty(value = "登录名", required = true)
  String loginName;
  @NotBlank
  @ApiModelProperty(value = "登录密码", required = true)
  String password;

  /**
   * 验证码信息.
   */
  @Valid
  @ApiModelProperty(value = "验证码信息。用户连续输入密码错误5次(>=5)则需要验证", required = false)
  private Captcha captcha;



  @Setter
  @Getter
  @NoArgsConstructor
  @ToString
  public static class Captcha {

    @NotBlank
    @ApiModelProperty(value = "秘钥信息",required = true)
    private String secret;

    @NotBlank
    @ApiModelProperty(value = "验证码信息.",required = true)
    private String  challenge;

    }
}
