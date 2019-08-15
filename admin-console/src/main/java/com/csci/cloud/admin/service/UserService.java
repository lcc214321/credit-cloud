package com.csci.cloud.admin.service;

import com.csci.cloud.admin.data.vo.LogoutRespVo;
import com.csci.cloud.admin.data.vo.RestPasswordReqVo;
import com.csci.cloud.admin.data.vo.UserRespVo;
import com.csci.cloud.admin.jooq.tables.pojos.TUser;
import com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha;
import java.util.List;
import java.util.Optional;

/**
 */
public interface UserService {


  /**
   * 根据登录名找到用户.
   * @param loginName
   * @return
   */
  TUser findUserByLoginName(String loginName);

  /**
   * 重置用户密码.
   * @param userId 用户的Id.
   * @param reqVo
   * @return
   */
  UserRespVo resetPassword(int userId, RestPasswordReqVo reqVo);


  /**
   * 退出登录.
   * @param userId 用户Id
   * @return >0成功. =0 失败.
   */
  LogoutRespVo logout(int userId);

  Optional<TUserCaptcha> getUserCaptcha(String loginName);

  /**
   * 登陆失败次数累加.
   * @param loginName
   * @return
   */
  int incLoginTime(String loginName);

  int resetLoginTime(String loginName);
}
