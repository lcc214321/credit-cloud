package com.csci.cloud.auth.server.service.impl;

import com.csci.cloud.auth.common.data.AuthenticationReqVo;
import com.csci.cloud.auth.common.data.AuthenticationRespVo;
import com.csci.cloud.auth.common.data.ConfigServerVo;
import com.csci.cloud.auth.server.convert.ConfigServerConvert;
import com.csci.cloud.auth.server.dao.ExApplicationDao;
import com.csci.cloud.auth.server.dao.ExApplicationKeyDao;
import com.csci.cloud.auth.server.dao.ExConfigServerDao;
import com.csci.cloud.auth.server.dao.ExTenantDao;
import com.csci.cloud.auth.server.exception.AuthBizException;
import com.csci.cloud.auth.server.jooq.tables.pojos.TApplication;
import com.csci.cloud.auth.server.jooq.tables.pojos.TApplicationKey;
import com.csci.cloud.auth.server.jooq.tables.pojos.TTenant;
import com.csci.cloud.auth.server.service.AppKeyService;
import com.csci.cloud.core.common.ErrorEnums.AuthErrorEnum;
import com.csci.cloud.core.common.ErrorEnums.CommonErrorEnum;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
@Slf4j
public class AppKeyServiceImpl implements AppKeyService {
  @Autowired
  private ExApplicationKeyDao applicationKeyDao;

  @Autowired
  private ExApplicationDao applicationDao;

  @Autowired
  private ExTenantDao tenantDao;

  @Autowired
  private ExConfigServerDao configServerDao;


  @Override
  public AuthenticationRespVo authentication(AuthenticationReqVo reqVo) {

    /**
     * 验证接口是否过期.
     */
    long timestamp = reqVo.getTimestamp()*1000;
    if(timestamp < DateUtils.addMinutes(new Date(),-10).getTime()) {
      throw new AuthBizException(AuthErrorEnum.REQUEST_TIME_OUT);
    }
    TApplicationKey appKeyDo = applicationKeyDao.fetchOneByAppKey(reqVo.getApiKey());
    if (null == appKeyDo) {
      throw new AuthBizException(AuthErrorEnum.AUTH_SIGNATURE_ERROR);
    }

    /**
     * 验证签名.
     */
    boolean valid = checkSign(reqVo, appKeyDo.getAppSecret());
    if (!valid) {
      throw new AuthBizException(AuthErrorEnum.AUTH_SIGNATURE_ERROR);
    }

    TApplication applicationDo = applicationDao.fetchOneById(appKeyDo.getAppId());
    if (null == applicationDo) {
      log.error("{} apiKey application record not exist in db",appKeyDo.getAppKey());
      throw new AuthBizException(CommonErrorEnum.INTERNAL_SERVER_ERROR);
    }

    TTenant tenantDo = tenantDao.fetchOneById(applicationDo.getTenantId());

    if (null == tenantDo) {
      throw new AuthBizException(CommonErrorEnum.INTERNAL_SERVER_ERROR);
    }

    List<ConfigServerVo> configServerVos = CollectionUtils
        .emptyIfNull(configServerDao.fetchByTenantId(tenantDo.getId()))
        .stream().map(configServerDo -> {
          if (null != reqVo.getServerType()) {
            if(configServerDo.getServerType().equals(reqVo.getServerType()) && configServerDo.getStatus() == 0) {
              throw new AuthBizException(AuthErrorEnum.PERMISSION_DENY);
            }
          }
            return  ConfigServerConvert.convertConfigServer(configServerDo);
        })
        .collect(Collectors.toList());

    AuthenticationRespVo respVo = AuthenticationRespVo.builder()
        .appId(appKeyDo.getAppId())
        .apiKey(appKeyDo.getAppKey())
        .companyCode(tenantDo.getCode())
        .companyName(tenantDo.getName())
        .tenantId(tenantDo.getId())
        .configServers(configServerVos)
        .build();

    return respVo;
  }


  /**
   * 检查签名是否正确.
   * @param authenticationReqVo
   * @param secret
   * @return
   */
  private static boolean checkSign(AuthenticationReqVo authenticationReqVo, String secret) {

    Map<String, String> queryMap = authenticationReqVo.getQueryMap();
    queryMap.put("apiKey", authenticationReqVo.getApiKey());
    queryMap.put("timestamp", authenticationReqVo.getTimestamp() + "");

    StringBuffer result = new StringBuffer(authenticationReqVo.getUri()).append("?");
    String reduce = queryMap.entrySet().stream().sorted(Comparator.comparing(Entry::getKey))
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .reduce(StringUtils.EMPTY, (a, b) -> a + "&" + b);

    result.append(reduce.substring(1)).append(secret);
    return Hashing.md5().hashString(result.toString(), Charsets.UTF_8).toString()
        .equals(authenticationReqVo.getSign());
  }

}
