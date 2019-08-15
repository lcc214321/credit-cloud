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
 * 工商基本信息.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder
public class IndustryBasicInfoRespVo {

  @ApiModelProperty(value = "企业ID")
  @JsonAlias(value = {"company_id"})
  private String companyId;

  @ApiModelProperty(value = "企业全称")
  @JsonAlias(value = {"company_nm"})
  private String companyName;


  @ApiModelProperty(value = "企业简称")
  @JsonAlias(value = {"company_snm"})
  private String companySnm;

  @ApiModelProperty(value = "企业英文全称")
  @JsonAlias(value = {"fen_nm"})
  private String fenNm;

  @ApiModelProperty("曾用名")
  @JsonAlias(value = {"other_nm"})
  private String otherNm;

  @ApiModelProperty("法定代表人")
  @JsonAlias(value = {"leg_represent"})
  private String legRepresent;

  @ApiModelProperty("组织形式")
  @JsonAlias(value = {"org_form"})
  private String orgForm;

  @ApiModelProperty("成立日期")
  @JsonAlias(value = {"found_dt"})
  private String foundDt;


  @ApiModelProperty("注册资本")
  @JsonAlias(value = {"reg_capital"})
  private String regCapital;

  @ApiModelProperty("注册地址")
  @JsonAlias(value = {"reg_addr"})
  private String regAddr;

  @ApiModelProperty("联系电话")
  @JsonAlias(value = {"company_ph"})
  private String companyPh;

  @ApiModelProperty(value = "公司网址")
  @JsonAlias(value = {"company_web"})
  private String companyWeb;

  @ApiModelProperty(value = "经营范围")
  @JsonAlias(value = {"busin_scope"})
  private String businScope;

  @ApiModelProperty("员工总数")
  @JsonAlias(value = {"employ_num"})
  private String employNum;


  @ApiModelProperty("营业执照号码")
  private String  blnumb;

  @ApiModelProperty("国税登记号码")
  private String  ntrnum;

  @ApiModelProperty("组织机构代码")
  private String  orgnum;

  @ApiModelProperty("所属行业")
  private String industry;

  @ApiModelProperty("实收注册资本")
  @JsonAlias(value = {"actual_capital"})
  private String actualCapital;

  @ApiModelProperty("登记机关")
  @JsonAlias(value = {"reg_gov"})
  private String  regGov;

  @ApiModelProperty("营业期限")
  @JsonAlias(value = {"management_period"})
  private String  managementPeriod;


  @ApiModelProperty("公司简介")
  @JsonAlias(value = {"company_profile"})
  private String  companyProfile;

  @ApiModelProperty("经营状态")
  @JsonAlias(value = {"management_st"})
  private String managementSt;

  @ApiModelProperty("核准日期")
  @JsonAlias(value = {"approved_time"})
  private String approvedTime;


  @ApiModelProperty("统一社会信用代码")
  @JsonAlias(value = {"credit_cd"})
  private String creditCd;


}
