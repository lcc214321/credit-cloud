package com.csci.cloud.admin.captcha.model.req;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: ben.ma
 * @Date: 2019/1/23 12:12 PM benkris1@gmail.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public  class CaptchaVerifyVo {

  private String secret;

  private String  challenge;

}
