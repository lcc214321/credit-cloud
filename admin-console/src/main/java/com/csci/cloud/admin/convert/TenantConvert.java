package com.csci.cloud.admin.convert;

import com.csci.cloud.admin.data.vo.ApplicationInfoRespVo;
import com.csci.cloud.admin.jooq.tables.pojos.TApplication;
import com.csci.cloud.admin.jooq.tables.pojos.TApplicationKey;

/**
 * Created by ben on 2018/9/18. benkris1@126.com
 */
public class TenantConvert {

  /**
   * 根据application和applicationKey转换vo.
   * @param application
   * @param applicationKey
   * @return
   */
  public static ApplicationInfoRespVo convertApplication(TApplication application,
      TApplicationKey applicationKey) {

    ApplicationInfoRespVo respVo = ApplicationInfoRespVo.builder()
        .appId(application.getId())
        .appKey(applicationKey.getAppKey())
        .appSecret(applicationKey.getAppSecret())
        .keyStatus(applicationKey.getStatus())
        .name(application.getName())
        .tenantId(application.getTenantId())
        .build();

    return respVo;

  }
}
