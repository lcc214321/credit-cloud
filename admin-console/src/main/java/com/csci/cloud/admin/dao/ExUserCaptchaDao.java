package com.csci.cloud.admin.dao;

import com.csci.cloud.admin.jooq.Tables;
import com.csci.cloud.admin.jooq.tables.TUserCaptcha;
import com.csci.cloud.admin.jooq.tables.daos.TUserCaptchaDao;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: ben.ma
 * @Date: 2019/1/23 10:55 AM benkris1@gmail.com
 */
@Repository
public class ExUserCaptchaDao extends TUserCaptchaDao {

  private DSLContext dslContext ;


  @Autowired
  public ExUserCaptchaDao(DSLContext dslContext) {
    super(dslContext.configuration());
    this.dslContext =dslContext;
  }

  public int incLoginTimes(String loginName) {
    return dslContext.insertInto(Tables.T_USER_CAPTCHA)
        .set(TUserCaptcha.T_USER_CAPTCHA.LOGIN_NAME, loginName)
        .set(TUserCaptcha.T_USER_CAPTCHA.TIMES, 1)
        .onDuplicateKeyUpdate()
        .set(TUserCaptcha.T_USER_CAPTCHA.TIMES, TUserCaptcha.T_USER_CAPTCHA.TIMES.plus(1))
        .execute();
  }

  public int resetLoginTime(String loginName) {
    return dslContext.update(Tables.T_USER_CAPTCHA)
        .set(TUserCaptcha.T_USER_CAPTCHA.TIMES, 0)
        .where(TUserCaptcha.T_USER_CAPTCHA.LOGIN_NAME.eq(loginName))
        .execute();
  }
}
