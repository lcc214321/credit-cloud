package com.csci.cloud.core.server.data.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
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
public class ApiUsageRecordDto {

  @ApiModelProperty(value = "调用编号")
  private String seqNum;

  @ApiModelProperty(value = "接口编号")
  private String apiId;

  @ApiModelProperty(value = "接口名称")
  private String apiName;

  @ApiModelProperty(value = "接口类型.0: 区块链 1:企业征信 2:个人征信")
  private Integer type;

  @ApiModelProperty(value = "调用时间")
  private Date createdAt;

  @ApiModelProperty(value = "调用结果返回码")
  private String resultCode;

  @ApiModelProperty(value = "调用结果返回信息")
  private String resultMessage;

  @ApiModelProperty(value = "公司代码")
  private String companyCode;

  @ApiModelProperty(value = "公司名称")
  private String companyName;

  @ApiModelProperty(value = "租户ID")
  private Integer tenantId;

  @ApiModelProperty(value = "应用ID")
  private Integer appId;

  @ApiModelProperty(value = "耗时")
  private Long duration;


}
