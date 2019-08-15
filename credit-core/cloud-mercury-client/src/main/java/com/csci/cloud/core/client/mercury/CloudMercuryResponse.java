package com.csci.cloud.core.client.mercury;

import com.csci.cloud.core.common.ErrorEnums.MercuryErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ben on 2018/9/25. benkris1@126.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CloudMercuryResponse<T> {

  public static CloudMercuryResponse HYSTRIX_RESPONSE = new CloudMercuryResponse<>(
      MercuryErrorEnum.SERVER_CRASH.getCode(),
      MercuryErrorEnum.SERVER_CRASH.getDesc(), null, null);


  private String code;

  private String msg;

  private String seq;

  private T data;
}
