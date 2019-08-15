package com.csci.cloud.auth.client.config;

import com.csci.cloud.auth.client.exception.CloudAuthClientException;
import com.csci.cloud.auth.client.model.res.AuthResponse;
import com.csci.cloud.core.common.ErrorEnums.CommonErrorEnum;
import com.csci.cloud.core.common.utils.JsonUtils;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import java.io.Reader;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ben on 2018/9/25. benkris1@126.com
 */
@Slf4j
public class CloudAuthErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    try {
      Reader reader = response.body().asReader();
      String body = Util.toString(reader);
      AuthResponse authResponse = JsonUtils.toObj(body, AuthResponse.class);
      if (null != authResponse && authResponse.getCode() != "") {
        return new CloudAuthClientException(authResponse.getCode() + "",
            authResponse.getErrorMessage(), authResponse.getData());
      }
    } catch (Exception e) {
      log.warn("cloud author client decode出现异常,e:{}", e);
    }
    return new CloudAuthClientException(CommonErrorEnum.INTERNAL_SERVER_ERROR);
  }
}
