package com.csci.cloud.admin.web.controller;

import com.csci.cloud.admin.data.vo.ApplicationInfoRespVo;
import com.csci.cloud.admin.data.vo.ConfigServerVo;
import com.csci.cloud.admin.service.ConfigServerService;
import com.csci.cloud.admin.service.TenantService;
import com.csci.cloud.admin.service.impl.ConfigServerServiceImpl;
import com.csci.cloud.admin.utils.ConfigEnums.ServerStatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 租户相关的API.
 */
@RestController
@RequestMapping(value = "/api/v1/tenants")
@Api(value = "租户相关的api" ,tags = {"租户管理"})
@Slf4j
public class TenantController extends AbstractController {

  @Autowired
  private TenantService tenantService;

  @Autowired
  private ConfigServerService configServerService;


  @ApiOperation(value = "根据租户Id获取租户下的App信息", notes = "")
  @RequestMapping(value = "/{tenantId}/apps", method = RequestMethod.GET)
  public List<ApplicationInfoRespVo> getAppsByTenantId(@PathVariable("tenantId") Integer tenantId,
      @ApiParam(hidden = true) @RequestAttribute("userId") Integer userId){
    return tenantService.getAppsByTenantId(tenantId,userId);
  }

  @ApiOperation(value = "根据租户Id获取服务列表信息", notes = "")
  @RequestMapping(value = "/{tenantId}/server-config", method = RequestMethod.GET)
  public List<ConfigServerVo> getServerConfigs(@PathVariable("tenantId") Integer tenantId,
      @ApiParam(hidden = true) @RequestAttribute("userId") Integer userId){

    return configServerService.getByTenantId(tenantId,userId);
  }

  @ApiOperation(value = "启用/关闭服务", notes = "")
  @RequestMapping(value = "/{tenantId}/server-config/{id}/{status}", method = RequestMethod.PUT)
  public List<ConfigServerVo> updateServerConfig(
      @ApiParam(value = "租户Id", required = true) @PathVariable("tenantId") Integer tenantId,
      @ApiParam(value = "服务Id", required = true) @PathVariable("id") Integer id,
      @ApiParam(value = "更新状态", required = true) @PathVariable("status") Integer status,
      @ApiParam(hidden = true) @RequestAttribute("userId") Integer userId){

    return configServerService.enabledServer(tenantId,userId,id, ServerStatusEnum.fromValue(status));
  }

}
