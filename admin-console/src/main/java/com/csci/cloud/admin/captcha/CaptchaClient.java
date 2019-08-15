package com.csci.cloud.admin.captcha;

import com.csci.cloud.admin.captcha.config.CaptchaConfig;
import com.csci.cloud.admin.captcha.model.req.CaptchaVerifyVo;
import javax.ws.rs.core.MediaType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "captcha", url = "${client.captcha.url}",
    configuration = CaptchaConfig.class,
    fallbackFactory = CaptchaFallBackFactory.class)
public interface CaptchaClient {



  @RequestMapping(value = "/captcha/verify", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON)
  CaptchaResponse verify(CaptchaVerifyVo captchaVerifyVo);

}
