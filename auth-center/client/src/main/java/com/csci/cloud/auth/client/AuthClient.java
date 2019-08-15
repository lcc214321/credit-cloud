package com.csci.cloud.auth.client;

import com.csci.cloud.auth.client.config.CloudAuthConfig;
import com.csci.cloud.auth.client.model.res.AuthResponse;
import com.csci.cloud.auth.common.data.AuthenticationReqVo;
import com.csci.cloud.auth.common.data.AuthenticationRespVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 */
@FeignClient(name = "auth-client", url = "${client.auth.url}",configuration = CloudAuthConfig.class)
public interface AuthClient {

  /**
   * 验证权限.
   * @param authenticationReqVo
   * @return
   */
  @RequestMapping(value = "/api/authentication", method = RequestMethod.POST)
  AuthResponse<AuthenticationRespVo> authentication(
          @RequestBody AuthenticationReqVo authenticationReqVo);

}
