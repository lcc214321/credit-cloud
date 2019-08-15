package com.csci.cloud.admin.web.controller;

import com.csci.cloud.admin.auth.UserTokenFactory;
import com.csci.cloud.admin.data.vo.RefreshTokenReqVo;
import com.csci.cloud.admin.data.vo.RefreshTokenRespVo;
import com.csci.cloud.admin.data.vo.UserRespVo;
import com.csci.cloud.admin.jooq.tables.pojos.TUserRefreshToken;
import com.csci.cloud.admin.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RequestMapping(value = "/api/v1/token",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Token更新管理", tags = {"Token更新管理"})
public class UserSessionTokenController extends AbstractController {

  @Autowired
  TokenService tokenService;

  @Autowired
  private UserTokenFactory tokenFactory;

  @ApiOperation(value = "刷新Token", notes = "accessToken有过期时间,需要使用refreshToken定时去刷新accessToken")
  @RequestMapping(value = "/refresh", method = RequestMethod.PUT)
  public RefreshTokenRespVo refreshToken(@RequestAttribute("userId") Integer userId,@RequestBody
       @Validated RefreshTokenReqVo refreshTokenReqVo) {

    RefreshTokenRespVo respVo = tokenService.refreshToken(userId, refreshTokenReqVo);

    return respVo;
  }
}
