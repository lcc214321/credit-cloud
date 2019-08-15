package com.csci.cloud.admin.web.controller;

import com.csci.cloud.admin.auth.UserTokenFactory;
import com.csci.cloud.admin.captcha.CaptchaClient;
import com.csci.cloud.admin.captcha.model.req.CaptchaVerifyVo;
import com.csci.cloud.admin.constant.AuthConstant;
import com.csci.cloud.admin.convert.UserConvert;
import com.csci.cloud.admin.data.vo.LoginRespVo;
import com.csci.cloud.admin.data.vo.LoginVo;
import com.csci.cloud.admin.data.vo.LogoutRespVo;
import com.csci.cloud.admin.data.vo.RegisterReqVo;
import com.csci.cloud.admin.data.vo.RestPasswordReqVo;
import com.csci.cloud.admin.data.vo.UserRespVo;
import com.csci.cloud.admin.exception.UserBizException;
import com.csci.cloud.admin.jooq.tables.pojos.TTenant;
import com.csci.cloud.admin.jooq.tables.pojos.TUser;
import com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha;
import com.csci.cloud.admin.jooq.tables.pojos.TUserRefreshToken;
import com.csci.cloud.admin.service.TenantService;
import com.csci.cloud.admin.service.TokenService;
import com.csci.cloud.admin.service.UserService;
import com.csci.cloud.admin.utils.ErrorCode;
import com.csci.cloud.admin.utils.PasswordUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * 用户相关的API.
 */
@RestController
@RequestMapping(value = "/api/users")
@Api(value = "用户相关的api",tags = {"用户管理"})
public class UserController extends AbstractController {

  @Autowired
  private UserService userService;

  @Autowired
  private TenantService tenantService;

  @Autowired
  private UserTokenFactory tokenFactory;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private CaptchaClient captchaClient;

  @ApiOperation(value = "租户注册", notes = "")
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public UserRespVo register(@Valid @RequestBody RegisterReqVo registerView) {

    UserRespVo userRespVo = tenantService.registerTenant(
        registerView.getCompanyName(), registerView.getCompanyCode(),
        registerView.getLoginName(), registerView.getPassword(),
        registerView.getEmail(), registerView.getMobile());
    return userRespVo;
  }

  @ApiOperation(value = "用户登录", notes = "验证码信息。用户连续输入密码错误5次(>=5)则需要验证")
  @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public LoginRespVo login(@Valid @RequestBody LoginVo loginView) {

    Optional<TUserCaptcha> optional = userService.getUserCaptcha(loginView.getLoginName());
    if (optional.isPresent() && null == loginView.getCaptcha()) {
      TUserCaptcha tUserCaptcha = optional.get();
      if (tUserCaptcha.getTimes() >= 5) { // 连续5次失败 需要输入验证码.
        throw new UserBizException(ErrorCode.ILLEGAL_LOGIN_WITH_OUT_CAPTCHA);
      }
    }
    TUser user = userService
        .findUserByLoginName(loginView.getLoginName());
    if (user == null || !PasswordUtils.check(loginView.getPassword(), user.getPassword())) {
      userService.incLoginTime(loginView.getLoginName());
      throw new UserBizException(ErrorCode.USER_PASSWORD_NOT_CORRECT);
    }
    if (null != loginView.getCaptcha()) {
       captchaClient.verify(CaptchaVerifyVo.builder().challenge(loginView.getCaptcha().getChallenge())
       .secret(loginView.getCaptcha().getSecret()).build()
       );
    }

    userService.resetLoginTime(loginView.getLoginName());
    Pair<String, Date> pairOfAccessToken = tokenFactory.createAccessToken(user.getId());
    Pair<String, Date> pairOfRefreshToken = tokenFactory.createRefreshToken(user.getId());

    TTenant tTenant = tenantService.getById(user.getTenantId());
    TUserRefreshToken refreshToken = tokenService
        .create(tTenant.getId(), user.getId(), pairOfRefreshToken.getLeft(),
            pairOfRefreshToken.getValue());

    LoginRespVo loginRespVo = UserConvert.convertUser(user, tTenant,LoginRespVo.class);
    loginRespVo.setAccessToken(pairOfAccessToken.getLeft());
    loginRespVo.setAccessTokenExpireAt(pairOfAccessToken.getRight());
    loginRespVo.setRefreshToken(refreshToken.getToken());
    loginRespVo.setRefreshTokenExpireAt(refreshToken.getExpiredAt());

    return loginRespVo;
  }


  @ApiOperation(value = "重置密码", notes = "")
  @RequestMapping(value = "/reset-password", method = RequestMethod.PUT)
  public UserRespVo  resetPassword(@Valid @RequestBody RestPasswordReqVo reqVo,
      @RequestAttribute("userId") Integer userId) {

    UserRespVo userRespVo = userService.resetPassword(userId, reqVo);
    return userRespVo;

  }

  @ApiOperation(value = "退出登录", notes = "")
  @RequestMapping(value = "/logout", method = RequestMethod.PUT)
  public LogoutRespVo logout(@RequestAttribute("userId") Integer userId) {

    return userService.logout(userId);
  }
}
