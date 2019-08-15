package com.csci.cloud.auth.common.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ben on 2018/9/12. benkris1@126.com
 * 服务配置信息.
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel
@ToString
@AllArgsConstructor
@Builder
public class ConfigServerVo {

  @ApiModelProperty(value = "id")
  private Integer id;

  @ApiModelProperty(value = "服务名称")
  private String serverName;

  @ApiModelProperty(value = "服务类型 0: 区块链 1:企业征信 2:个人征信")
  private Integer serverType;

  @ApiModelProperty(value = "服务状态.1:开启 0：关闭")
  private Integer status ;
}
