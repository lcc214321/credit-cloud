package com.csci.cloud.auth.server.convert;


import com.csci.cloud.auth.common.data.ConfigServerVo;
import com.csci.cloud.auth.server.jooq.tables.pojos.TConfigServer;

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
