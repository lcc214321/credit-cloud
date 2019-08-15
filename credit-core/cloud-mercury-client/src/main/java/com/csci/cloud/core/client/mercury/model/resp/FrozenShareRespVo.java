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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder
public class FrozenShareRespVo {

  @ApiModelProperty(value = "企业ID")
  @JsonAlias(value = {"company_id"})
  private String companyId;

  @ApiModelProperty(value = "执行冻结结构")
  @JsonAlias(value = {"frozen_inst"})
  private String frozenInst;

  @ApiModelProperty(value = "冻结起始日")
  @JsonAlias(value = {"frozen_dt"})
  private String frozenDt;

  @ApiModelProperty(value = "提前解冻日期")
  @JsonAlias(value = {"pre_unfrozen_dt"})
  private String preUnfrozenDt;

  @ApiModelProperty(value = "解冻日期")
  @JsonAlias(value = {"unfrozen_dt"})
  private String unfrozenDt;

  @ApiModelProperty(value = "公告时间")
  @JsonAlias(value = {"notice_dt"})
  private String noticeDt;

  @ApiModelProperty(value = "股东名称")
  @JsonAlias(value = {"sharehd_nm"})
  private String sharehdNm;

  @ApiModelProperty(value = "冻结事由")
  @JsonAlias(value = {"frozen_reason"})
  private String frozenReason;

  @ApiModelProperty(value = "冻结股数")
  @JsonAlias(value = {"frozen_num"})
  private String frozenNum;
}
