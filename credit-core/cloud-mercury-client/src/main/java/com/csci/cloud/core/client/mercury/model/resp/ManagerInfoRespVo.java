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
 * 企业高管信息.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder
public class ManagerInfoRespVo {

  @ApiModelProperty(value = "企业Id")
  @JsonAlias(value = {"company_id"})
  private String companyId;

  @ApiModelProperty(value = " 任职企业名称")
  @JsonAlias(value = {"company_nm"})
  private String companyNm;


  @ApiModelProperty(value = "自然人Id")
  @JsonAlias(value = {"person_id"})
  private String personId;

  @ApiModelProperty(value = "性别")
  private String sex;

  @ApiModelProperty(value = "出生年份")
  @JsonAlias(value = {"birth_year"})
  private String birthYear;

  @ApiModelProperty(value = "职务")
  private String position;

  @ApiModelProperty(value = "姓名")
  @JsonAlias(value = {"person_nm"})
  private String personNm;

  @ApiModelProperty(value = "学历")
  private String education;


  @ApiModelProperty(value = "国籍")
  private String country;

  @ApiModelProperty(value = "更新日期")
  @JsonAlias(value = {"updt_dt"})
  private String updtDt;
}
