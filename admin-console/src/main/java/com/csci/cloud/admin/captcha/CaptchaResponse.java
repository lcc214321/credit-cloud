package com.csci.cloud.admin.captcha;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ben on 2018/9/25. benkris1@126.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaResponse {

  public static CaptchaResponse HYSTRIX_RESPONSE = new CaptchaResponse(0,"Success.");


  private Integer code;

  private String msg;


}
