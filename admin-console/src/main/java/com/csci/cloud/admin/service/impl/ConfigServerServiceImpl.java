package com.csci.cloud.admin.service.impl;

import com.csci.cloud.admin.convert.ConfigServerConvert;
import com.csci.cloud.admin.dao.ExConfigServerDao;
import com.csci.cloud.admin.dao.ExUserDao;
import com.csci.cloud.admin.data.vo.ConfigServerVo;
import com.csci.cloud.admin.exception.AppBizException;
import com.csci.cloud.admin.exception.UserBizException;
import com.csci.cloud.admin.jooq.Tables;
import com.csci.cloud.admin.jooq.tables.pojos.TConfigServer;
import com.csci.cloud.admin.service.AbstractService;
import com.csci.cloud.admin.service.ConfigServerService;
import com.csci.cloud.admin.utils.ConfigEnums.ServerStatusEnum;
import com.csci.cloud.admin.utils.ErrorCode;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ben on 2018/9/18. benkris1@126.com
 */
@Service
public class ConfigServerServiceImpl extends AbstractService implements ConfigServerService {

  @Autowired
  private ExConfigServerDao configServerDao;

  @Autowired
  private ExUserDao userDao;

  @Override
  public List<ConfigServerVo> getByTenantId(int tenantId,int userId) {
    boolean exist = userDao.existUser(tenantId, userId);
    if (!exist) {
      throw new UserBizException(ErrorCode.USER_NOT_MAPPING);
    }
    return getConfigServers(tenantId);
  }

  @Override
  public List<ConfigServerVo> enabledServer(int tenantId, int userId, int id, ServerStatusEnum statusEnum) {
    boolean exist = userDao.existUser(tenantId, userId);
    if (!exist) {
      throw new UserBizException(ErrorCode.USER_NOT_MAPPING);
    }
    int result = dslContext.update(Tables.T_CONFIG_SERVER)
        .set(Tables.T_CONFIG_SERVER.STATUS, statusEnum.getValue())
        .where(Tables.T_CONFIG_SERVER.ID.eq(id))
        .execute();
    if (result <= 0) {
      throw new AppBizException(ErrorCode.OPERATOR_ERROR);
    }
    return getConfigServers(tenantId);
  }

  private List<ConfigServerVo> getConfigServers(int tenantId) {
    List<TConfigServer> configServerDos = configServerDao.fetchByTenantId(tenantId);
    return CollectionUtils.emptyIfNull(configServerDos).stream()
        .map(configServer -> ConfigServerConvert.convertConfigServer(configServer))
        .collect(Collectors.toList());
  }

}
