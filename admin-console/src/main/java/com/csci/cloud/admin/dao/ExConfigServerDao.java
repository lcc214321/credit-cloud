package com.csci.cloud.admin.dao;

import com.csci.cloud.admin.jooq.tables.daos.TConfigServerDao;
import com.csci.cloud.admin.jooq.tables.pojos.TConfigServer;
import com.csci.cloud.admin.utils.ConfigEnums.ServerStatusEnum;
import com.csci.cloud.core.common.enums.ServiceTypeEnum;
import com.google.common.collect.Lists;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ben on 2018/9/18. benkris1@126.com
 */
@Repository
public class ExConfigServerDao extends TConfigServerDao {

  private DSLContext dslContext;

  @Autowired
  public ExConfigServerDao(DSLContext dslContext) {
    super(dslContext.configuration());
    this.dslContext = dslContext;
  }

  /**
   * 初始化服务配置信息.
   * @param tenantId
   */
  public void initConfigServer(int tenantId) {

    List<TConfigServer> configServerList = Lists.newArrayList();
    for(ServiceTypeEnum serviceTypeEnum:ServiceTypeEnum.values()) {
       TConfigServer configServer = new TConfigServer();
       configServer.setServerName(serviceTypeEnum.name());
       configServer.setTenantId(tenantId);
       configServer.setServerType(serviceTypeEnum.getValue());
       configServer.setStatus(ServerStatusEnum.ENABLE.getValue());//启用
       configServerList.add(configServer);
    }
    insert(configServerList);

  }
}
