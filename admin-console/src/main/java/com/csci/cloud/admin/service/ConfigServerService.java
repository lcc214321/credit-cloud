package com.csci.cloud.admin.service;

import com.csci.cloud.admin.data.vo.ConfigServerVo;
import com.csci.cloud.admin.utils.ConfigEnums.ServerStatusEnum;
import java.util.List;

/**
 * Created by ben on 2018/9/18. benkris1@126.com
 */
public interface ConfigServerService {

  /**
   * 根据租户id获取所有服务信息.
   * @param tenantId
   * @return
   */
   List<ConfigServerVo> getByTenantId(int tenantId, int userId) ;

  /**
   * 启用/关闭服务.
   * @param tenantId 租户id
   * @param userId 用户id
   * @param id 服务id
   * @param status 服务状态
   * @return
   */
   List<ConfigServerVo> enabledServer(int tenantId, int userId, int id, ServerStatusEnum status);
}
