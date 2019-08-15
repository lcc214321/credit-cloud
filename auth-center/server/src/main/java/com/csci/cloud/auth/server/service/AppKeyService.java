package com.csci.cloud.auth.server.service;


import com.csci.cloud.auth.common.data.AuthenticationReqVo;
import com.csci.cloud.auth.common.data.AuthenticationRespVo;

/**
 */
public interface AppKeyService {


  /**
   * 验证接口访问权限.
   * @param reqVo
   * @return
   */
  AuthenticationRespVo authentication(AuthenticationReqVo reqVo) ;
}
