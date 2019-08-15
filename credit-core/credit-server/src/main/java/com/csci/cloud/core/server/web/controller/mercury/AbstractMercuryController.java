package com.csci.cloud.core.server.web.controller.mercury;

import com.csci.cloud.core.client.mercury.CloudMercuryResponse;
import com.csci.cloud.core.client.mirror.exception.CloudMirrorClientException;
import com.csci.cloud.core.common.ErrorEnums.MercuryErrorEnum;

public class AbstractMercuryController {

  protected void checkResponse(CloudMercuryResponse response) {
    if (!response.getCode().equalsIgnoreCase(MercuryErrorEnum.SUCCESS.getSourceCode())) {
      MercuryErrorEnum errorEnum = MercuryErrorEnum.getBySourceCode(response.getCode());
      throw new CloudMirrorClientException(errorEnum);
    }
  }

}
