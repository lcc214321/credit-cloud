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
 * 股权出质.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder
public class EquityPledgeRespVo {

  @ApiModelProperty("企业Id")
  @JsonAlias(value = {"company_id"})
  private String companyId;


  @ApiModelProperty("质权人")

  private String mortgagee;

  @ApiModelProperty("质权人类别")
  @JsonAlias(value = {"mortgagee_type"})
  private String mortgageeType;

  @ApiModelProperty("出质股权数额(万元)")
  private String pledgenum;

  @ApiModelProperty("股权出质截止日期")
  @JsonAlias(value = {"pledge_dt"})
  private String pledgeDt;

  @ApiModelProperty("出质人")
  private String pledgor;

  @ApiModelProperty("登记编号")
  @JsonAlias(value = {"reg_nm"})
  private String regNm;

  @ApiModelProperty("状态")
  private String status;

  @ApiModelProperty("更新时间")
  @JsonAlias(value = {"updt_dt"})
  private String updtDt;
}
