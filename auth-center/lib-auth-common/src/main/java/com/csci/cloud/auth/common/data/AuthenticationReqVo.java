package com.csci.cloud.auth.common.data;

import io.swagger.annotations.ApiModelProperty;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ben on 2018/9/27. benkris1@126.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuthenticationReqVo {

  @NotNull
  @ApiModelProperty(value = "接口调用时的时间戳，即当前时间戳（时间戳：当前距离Epoch（1970年1月1日) 以秒计算的时间,即unix-timestamp")
  private Long timestamp;

  @NotBlank
  @ApiModelProperty(value = "开发者key")
  private String apiKey;

  @ApiModelProperty(value = "url{}带问号（如{?status}")
  private Map<String,String> queryMap;

  @NotEmpty
  @ApiModelProperty(value = "请求uri 如/api/ping")
  private String uri;

  @NotEmpty
  @ApiModelProperty(value = "请求签名")
  private String sign;

  @ApiModelProperty(value = "服务类型 0: 区块链 1:企业征信 2:个人征信")
  private Integer serverType;



}
