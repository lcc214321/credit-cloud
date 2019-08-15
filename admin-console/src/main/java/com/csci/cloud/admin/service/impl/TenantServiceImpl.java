package com.csci.cloud.admin.service.impl;

import com.csci.cloud.admin.auth.AppKeyFactory;
import com.csci.cloud.admin.convert.TenantConvert;
import com.csci.cloud.admin.convert.UserConvert;
import com.csci.cloud.admin.dao.ExApplicationDao;
import com.csci.cloud.admin.dao.ExApplicationKeyDao;
import com.csci.cloud.admin.dao.ExConfigServerDao;
import com.csci.cloud.admin.dao.ExTenantDao;
import com.csci.cloud.admin.dao.ExUserDao;
import com.csci.cloud.admin.dao.ExUserGroupDao;
import com.csci.cloud.admin.data.vo.ApplicationInfoRespVo;
import com.csci.cloud.admin.data.vo.UserRespVo;
import com.csci.cloud.admin.exception.UserBizException;
import com.csci.cloud.admin.jooq.tables.pojos.TApplication;
import com.csci.cloud.admin.jooq.tables.pojos.TApplicationKey;
import com.csci.cloud.admin.jooq.tables.pojos.TTenant;
import com.csci.cloud.admin.jooq.tables.pojos.TUser;
import com.csci.cloud.admin.jooq.tables.pojos.TUserGroup;
import com.csci.cloud.admin.service.TenantService;
import com.csci.cloud.admin.utils.ConfigEnums.GroupTypeEnum;
import com.csci.cloud.admin.utils.ErrorCode;
import com.csci.cloud.admin.utils.PasswordUtils;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 租户服务管理.
 */
@Service
public class TenantServiceImpl implements TenantService {

  @Autowired
  private AppKeyFactory appKeyFactory;

  @Autowired
  private ExUserGroupDao exUserGroupDao;

  @Autowired
  private ExTenantDao exTenantDao;

  @Autowired
  private ExUserDao exUserDao;

  @Autowired
  private ExApplicationDao exApplicationDao;

  @Autowired
  private ExApplicationKeyDao exApplicationKeyDao;

  @Autowired
  private ExConfigServerDao configServerDao;


  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 2000, rollbackFor = Exception.class)
  public UserRespVo registerTenant(String tenantName, String tenantCode, String userName,
      String password, String email,
      String mobile) {

    boolean isExistsUser = exUserDao
        .isExistsByLoginNameOrEmailOrMobile(userName, email, mobile);
    if (isExistsUser) {
      throw new UserBizException(ErrorCode.USER_ALREADY_EXIST);
    }

    boolean isExistTenant = exTenantDao.isExistsByTenantNameOrCode(tenantName, tenantCode);
    if (isExistTenant) {
      throw new UserBizException(ErrorCode.TENANT_EXIST);
    }

    TTenant tenant = new TTenant();
    tenant.setName(tenantName);
    tenant.setCode(tenantCode);
    TTenant tTenant = exTenantDao.create(tenant);

    TUserGroup group = new TUserGroup();
    group.setType(GroupTypeEnum.ADMIN.getValue()); // admin group as default
    exUserGroupDao.insert(group);

    TUser user = new TUser();
    user.setTenantId(tTenant.getId());
    user.setLoginName(userName);
    user.setEmail(email);
    user.setMobile(mobile);
    user.setPassword(PasswordUtils.encrypt(password));
    user.setGroupId(group.getId());

    TUser tUser = exUserDao.create(user);

    TApplication application = new TApplication();
    application.setName("app");
    application.setTenantId(tTenant.getId());
    exApplicationDao.insert(application);

    TApplicationKey applicationKey = new TApplicationKey();
    applicationKey.setAppId(application.getId());
    applicationKey.setAppKey(appKeyFactory.generateAppKey());
    applicationKey.setAppSecret(appKeyFactory.generateAppSecret());
    exApplicationKeyDao.insert(applicationKey);

    configServerDao.initConfigServer(tTenant.getId());

    return UserConvert.convertUser(tUser, tTenant);
  }

  @Override
  public TTenant getById(int id) {
    TTenant tTenant = exTenantDao.findById(id);
    return tTenant;
  }

  @Override
  public List<ApplicationInfoRespVo> getAppsByTenantId(int tenantId,int userId) {
    boolean exist = exUserDao.existUser(tenantId, userId);
    if (!exist) {
      throw new UserBizException(ErrorCode.USER_NOT_MAPPING);
    }
    List<TApplication> applications = exApplicationDao.fetchByTenantId(tenantId);
    if (CollectionUtils.isNotEmpty(applications)) {
      Map<Integer, TApplication> applicationMap = applications.stream().collect(
          Collectors.toMap(TApplication::getId, v -> v, (k1, k2) -> k1));
      List<TApplicationKey> applicationKeys = exApplicationKeyDao
          .fetchByAppId(applicationMap.keySet().toArray(new Integer[]{}));

      return applicationKeys.stream().map(applicationKey -> TenantConvert
          .convertApplication(applicationMap.get(applicationKey.getAppId()), applicationKey))
          .collect(Collectors.toList());
    }
    return Lists.newArrayList();
  }
}
