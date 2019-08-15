package com.csci.cloud.core.client.mercury.config;

import com.csci.cloud.core.client.mercury.CloudMercuryResponse;
import com.csci.cloud.core.client.mercury.exception.CloudMercuryClientException;
import com.csci.cloud.core.common.ErrorEnums.MirrorErrorEnum;
import com.csci.cloud.core.common.utils.JsonUtils;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import java.io.Reader;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ben on 2018/9/25. benkris1@126.com
 */
@Slf4j
public class CloudMercuryErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    try {
      Reader reader = response.body().asReader();
      String body = Util.toString(reader);
      CloudMercuryResponse cloudMirrorResponse = JsonUtils.toObj(body, CloudMercuryResponse.class);
      if (response.status() >= 400 && response.status() <= 499 && null != cloudMirrorResponse) {
        MirrorErrorEnum mirrorErrorEnum = MirrorErrorEnum
            .getBySourceCode(cloudMirrorResponse.getCode() + "");
        if (mirrorErrorEnum == MirrorErrorEnum.UNKNOWN_ERROR) {
          log.warn("cloud mirror error code mapping error,please fix it later.retCode:{},retMsg:{}",
              cloudMirrorResponse.getCode(), cloudMirrorResponse.getMsg());
        }
        return new CloudMercuryClientException(mirrorErrorEnum.getCode(),
            mirrorErrorEnum.getDesc(), cloudMirrorResponse.getData());
      }
    } catch (Exception e) {
      log.warn("cloud mirror client decode出现异常,e:{}", e);
    }
    return FeignException.errorStatus(methodKey, response);
  }
}
