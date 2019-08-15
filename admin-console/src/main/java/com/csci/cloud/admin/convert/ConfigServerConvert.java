package com.csci.cloud.admin.convert;

import com.csci.cloud.admin.data.vo.ConfigServerVo;
import com.csci.cloud.admin.jooq.tables.daos.TConfigServerDao;
import com.csci.cloud.admin.jooq.tables.pojos.TConfigServer;

/**
 * Created by ben on 2018/9/18. benkris1@126.com
 */
public class ConfigServerConvert {

  public static ConfigServerVo convertConfigServer(TConfigServer configServer) {
    return ConfigServerVo.builder().serverName(configServer.getServerName())
        .serverType(configServer.getServerType())
        .status(configServer.getStatus())
        .id(configServer.getId())
        .build();
  }
}
