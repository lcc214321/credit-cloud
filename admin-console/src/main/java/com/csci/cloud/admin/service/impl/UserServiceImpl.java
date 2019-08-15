package com.csci.cloud.admin.service.impl;

import com.csci.cloud.admin.convert.UserConvert;
import com.csci.cloud.admin.dao.ExTenantDao;
import com.csci.cloud.admin.dao.ExUserCaptchaDao;
import com.csci.cloud.admin.dao.ExUserDao;
import com.csci.cloud.admin.dao.ExUserRefreshTokenDao;
import com.csci.cloud.admin.data.vo.LogoutRespVo;
import com.csci.cloud.admin.data.vo.RestPasswordReqVo;
import com.csci.cloud.admin.data.vo.UserRespVo;
import com.csci.cloud.admin.exception.UserBizException;
import com.csci.cloud.admin.jooq.Tables;
import com.csci.cloud.admin.jooq.tables.pojos.TTenant;
import com.csci.cloud.admin.jooq.tables.pojos.TUser;
import com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha;
import com.csci.cloud.admin.service.UserService;
import com.csci.cloud.admin.utils.ErrorCode;
import com.csci.cloud.admin.utils.PasswordUtils;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户管理服务.
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private ExUserDao exUserDao;

  @Autowired
  private DSLContext dslContext;

  @Autowired
  private ExTenantDao tenantDao;

  @Autowired
  private ExUserRefreshTokenDao tokenDao;

  @Autowired
  private ExUserCaptchaDao userCaptchaDao;

  @Override
  public TUser findUserByLoginName(String loginName) {

    TUser userDo = exUserDao.fetchOneByLoginName(loginName);
    if (null == userDo) {
      throw new UserBizException(ErrorCode.USER_NOT_EXIST);
    }
    return userDo;
  }


  @Override
  public UserRespVo resetPassword(int userId,RestPasswordReqVo reqVo) {
    TUser userDo = exUserDao.fetchOptional(Tables.T_USER.ID, userId)
        .orElseThrow(() -> new UserBizException(ErrorCode.USER_NOT_EXIST));
    if (!PasswordUtils.check(reqVo.getOldPassword(),userDo.getPassword())) {
      throw new UserBizException(ErrorCode.USER_PASSWORD_NOT_CORRECT);
    }
    String encrypt = PasswordUtils.encrypt(reqVo.getNewPassword());
    int result = dslContext.update(Tables.T_USER)
        .set(Tables.T_USER.PASSWORD,encrypt)
        .where(Tables.T_USER.ID.eq(userId)).execute();

    userDo.setPassword(encrypt);
    TTenant tenantDo = tenantDao.fetchOneById(userDo.getId());
    if (null == tenantDo) {
      throw new UserBizException(ErrorCode.USER_INFORMATION_NOT_CONCURRENCY);
    }
    UserRespVo userRespVo = UserConvert.convertUser(userDo,tenantDo);
    return userRespVo;
  }

  @Override
  public LogoutRespVo logout(int userId) {

   int result = tokenDao.expireToken(userId);
   if(result <=0) {
     throw new UserBizException(ErrorCode.USER_LOGOUT_ERROR);
   }
   return LogoutRespVo.builder().logoutTime(new Date()).userId(userId).build();
  }

  @Override
  public Optional<TUserCaptcha> getUserCaptcha(String loginName) {

    return Optional
        .ofNullable(userCaptchaDao.fetchOneByLoginName(loginName));
  }

  @Override
  public int incLoginTime(String loginName) {
    return userCaptchaDao.incLoginTimes(loginName);
  }

  @Override
  public int resetLoginTime(String loginName) {
    return userCaptchaDao.resetLoginTime(loginName);
  }

}

