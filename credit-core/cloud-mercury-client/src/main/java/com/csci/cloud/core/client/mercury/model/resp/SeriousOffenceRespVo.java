package com.csci.cloud.core.client.mercury.model.resp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 企业严重违法.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder
public class SeriousOffenceRespVo {

  @ApiModelProperty("企业Id")
  @JsonAlias(value = {"company_id"})
  private String companyId;

  @ApiModelProperty(value = "违法事实")
  @JsonAlias(value = {"seriviolat_fact"})
  private String seriviolatFact;

  @ApiModelProperty("作出决定机关")
  @JsonAlias(value = {"liston_gov"})
  private String listonGov;


  @ApiModelProperty("移出严重违法名单原因")
  @JsonAlias(value = {"listout_reason"})
  private String listoutReason;

  @ApiModelProperty("移出日期")
  @JsonAlias(value = {"listout_dt"})
  private String listoutDt;

  @ApiModelProperty("列入日期")
  @JsonAlias(value = {"liston_dt"})
  private String listonDt;

  @ApiModelProperty("列入严重违法失信企业名单原因")
  @JsonAlias(value = {"liston_reason"})
  private String listonReason;

  @ApiModelProperty("类别")
  @JsonAlias(value = {"seriviolat_type"})
  private String seriviolatType;

  @ApiModelProperty("更新时间")
  @JsonAlias(value = {"updt_dt"})
  private String updtDt;
}
