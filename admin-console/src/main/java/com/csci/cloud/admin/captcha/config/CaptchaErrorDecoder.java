package com.csci.cloud.admin.captcha.config;

import com.csci.cloud.admin.captcha.CaptchaResponse;
import com.csci.cloud.admin.captcha.exception.CaptchaClientException;
import com.csci.cloud.core.common.utils.JsonUtils;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import java.io.Reader;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ben on 2018/9/25. benkris1@126.com
 */
@Slf4j
public class CaptchaErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    try {
      Reader reader = response.body().asReader();
      String body = Util.toString(reader);
      CaptchaResponse captchaResponse = JsonUtils.toObj(body, CaptchaResponse.class);
      if (response.status() >= 400 && response.status() <= 499 && null != captchaResponse) {

        return new CaptchaClientException(captchaResponse.getCode()+"",captchaResponse.getMsg());
      }
    } catch (Exception e) {
      log.warn("captcha client decode出现异常,e:{}", e);
    }
    return FeignException.errorStatus(methodKey, response);
  }
}
