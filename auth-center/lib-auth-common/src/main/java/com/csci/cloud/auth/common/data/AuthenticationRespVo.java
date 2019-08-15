package com.csci.cloud.auth.common.data;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ben on 2018/9/27. benkris1@126.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuthenticationRespVo {

  @ApiModelProperty(value = "租户ID")
  private Integer tenantId;

  @ApiModelProperty(value = "应用ID")
  private Integer appId;

  @ApiModelProperty(value = "公司代码")
  private String companyCode;

  @ApiModelProperty(value = "公司名称")
  private String companyName;

  @ApiModelProperty(value = "服务配置信息")
  private List<ConfigServerVo> configServers;

  @ApiModelProperty(value = "apiKey信息")
  private String apiKey;


}
