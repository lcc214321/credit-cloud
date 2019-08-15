package com.csci.cloud.auth.server.web.controller;

import com.csci.cloud.auth.common.data.AuthenticationReqVo;
import com.csci.cloud.auth.common.data.AuthenticationRespVo;
import com.csci.cloud.auth.server.service.AppKeyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 检验权限逻辑.
 */
@RestController
@RequestMapping(value = "/api/authentication")
@Api(value = "Auth授权相关的api",tags = "权限校验")
public class AuthController extends AbstractController {

  @Autowired
  AppKeyService appKeyService;


  @ApiOperation(value = "验证权限", notes = "")
  @RequestMapping(method = RequestMethod.POST)
  public AuthenticationRespVo authentication(@RequestBody @Validated AuthenticationReqVo reqVo) {

    return appKeyService.authentication(reqVo);
  }

}
