package com.csci.cloud.admin.data.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ben on 2018/9/12. benkris1@126.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApplicationInfoRespVo {

  /**
   * 开发者Id.
   */
  @ApiModelProperty(value = "开发者Id")
  private String appKey;

  /**
   *开发者秘钥.
   */
  @ApiModelProperty(value = "开发者密钥")
  private String appSecret;

  /**
   * 应用id.
   */
  @ApiModelProperty(value = "应用id")
  private Integer appId;

  /**
   * Key的状态：0-正常，9-删除.
   */
  @ApiModelProperty(value = "Key的状态：0-正常，9-删除")
  private Integer keyStatus;


  /**
   * 应用名称.
   */
  @ApiModelProperty(value = "应用名称")
  private String name;

  /**
   * 租户id.
   */
  @ApiModelProperty(value = "租户id")
  private Integer tenantId;

}
