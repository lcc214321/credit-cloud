package com.csci.cloud.admin.captcha;

import com.csci.cloud.admin.captcha.model.req.CaptchaVerifyVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 熔断处理.
 */
@Slf4j
public class CaptchaFallBackFactory implements FallbackFactory<CaptchaClient> {

  @Override
  public CaptchaClient create(Throwable cause) {
    if (log.isDebugEnabled()) {
      log.debug(ExceptionUtils.getStackTrace(cause));
    }
    return new CaptchaClient() {

      @Override
      public CaptchaResponse verify(CaptchaVerifyVo captchaVerifyVo) {
        return CaptchaResponse.HYSTRIX_RESPONSE;
      }
    };
  }
}
