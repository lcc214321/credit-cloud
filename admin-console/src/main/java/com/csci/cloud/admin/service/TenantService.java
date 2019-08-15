package com.csci.cloud.admin.service;

import com.csci.cloud.admin.data.vo.ApplicationInfoRespVo;
import com.csci.cloud.admin.data.vo.UserRespVo;
import com.csci.cloud.admin.jooq.tables.pojos.TTenant;
import java.util.List;

/**
 */
public interface TenantService {

  /**
   * 注册用户.
   * @param tenantName
   * @param tenantCode
   * @param userName 登录名
   * @param password 密码
   * @param email 邮箱
   * @param mobile 电话
   * @return
   */
  UserRespVo registerTenant(String tenantName, String tenantCode, String userName, String password,
                            String email, String mobile);


  TTenant getById(int id);

  /**
   * 根据用户Id和tenantId 获取APP信息.
   * @param tenantId
   * @return
   */
  List<ApplicationInfoRespVo> getAppsByTenantId(int tenantId, int userId);
}
