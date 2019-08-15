package com.csci.cloud.core.client.mirror.config;

import com.csci.cloud.core.client.mirror.FraudMirrorResponse;
import com.csci.cloud.core.client.mirror.MirrorResponse;
import com.csci.cloud.core.client.mirror.exception.CloudMirrorClientException;
import com.csci.cloud.core.common.ErrorEnums.MirrorErrorEnum;
import com.csci.cloud.core.common.utils.JsonUtils;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import java.io.Reader;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ben on 2018/9/25. benkris1@126.com 接口状态返回码 0: 正常 1003: 参数不正确 10003: 签名不合法 10004:签名过期
 * 10005: 无权访问该服务
 */
@Slf4j
public class CloudMirrorErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    try {

      Reader reader = response.body().asReader();
      String body = Util.toString(reader);
      MirrorResponse mirrorResponse = JsonUtils.toObj(body, FraudMirrorResponse.class);
      if (response.status() >= 400 && response.status() <= 499 && null != mirrorResponse) {
        MirrorErrorEnum mirrorErrorEnum = MirrorErrorEnum
            .getBySourceCode(mirrorResponse.getRetCode() + "");
        if (mirrorErrorEnum == MirrorErrorEnum.UNKNOWN_ERROR) {
          log.warn("cloud mirror error code mapping error,please fix it later.retCode:{},retMsg:{}",
              mirrorResponse.getRetCode(), mirrorResponse.getRetMsg());
        }
        return new CloudMirrorClientException(mirrorErrorEnum.getCode(),
            mirrorErrorEnum.getDesc(), null);
      }
    } catch (Exception e) {
      log.warn("cloud mirror client decode出现异常,e:{}", e);
    }
    return FeignException.errorStatus(methodKey, response);
  }
}
