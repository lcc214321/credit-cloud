package com.csci.cloud.admin.convert;

import com.csci.cloud.admin.data.vo.UserRespVo;
import com.csci.cloud.admin.jooq.tables.pojos.TTenant;
import com.csci.cloud.admin.jooq.tables.pojos.TUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

/**
 * Created by ben on 2018/9/11. benkris1@126.com
 */
@Slf4j
public final class UserConvert {

  public static final BeanCopier USER_COPIER = BeanCopier
      .create(TUser.class, UserRespVo.class, false);


  public static <T extends UserRespVo> T convertUser(TUser tUser, TTenant tTenant,Class<T> clazz) {
    UserRespVo userRespVo = null;
    try {
      userRespVo = clazz.newInstance();
      userRespVo.setTenantType(tTenant.getType());
      userRespVo.setTenantName(tTenant.getName());
      userRespVo.setTenantCode(tTenant.getCode());
      userRespVo.setTenantId(tUser.getTenantId());
      userRespVo.setStatus(tTenant.getStatus());
      userRespVo.setUpdatedAt(tUser.getUpdatedAt());
      userRespVo.setMobileVerified(tUser.getMobileVerified());
      userRespVo.setLoginName(tUser.getLoginName());
      userRespVo.setId(tUser.getId());
      userRespVo.setGroupId(tUser.getGroupId());
      userRespVo.setEmailVerified(tUser.getEmailVerified());
      userRespVo.setMobileVerified(tUser.getMobileVerified());
      userRespVo.setCreatedAt(tUser.getCreatedAt());
      return (T) userRespVo;
    } catch (Throwable e) {
      log.error(ExceptionUtils.getMessage(e));
    }
    return null;
  }

  public static  UserRespVo convertUser(TUser tUser, TTenant tTenant) {

    return convertUser(tUser,tTenant,UserRespVo.class);
  }

}
