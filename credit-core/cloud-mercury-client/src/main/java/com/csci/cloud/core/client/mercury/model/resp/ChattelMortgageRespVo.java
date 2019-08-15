package com.csci.cloud.core.client.mercury.model.resp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
public class ChattelMortgageRespVo {

  @ApiModelProperty("企业Id")
  @JsonAlias(value = {"company_id"})
  private String companyId;

  @ApiModelProperty("被担保债权数额")
  @JsonAlias(value = {"debt_amt"})
  private String debtAmt;


  @ApiModelProperty("被担保主债权种类")
  @JsonAlias(value = {"debt_type"})
  private String debtType;

  @ApiModelProperty("核准日期")
  @JsonAlias(value = {"reg_dt"})
  private String regDt;

  @ApiModelProperty("抵押物信息列表")
  @JsonAlias(value = {"pawn_info_list"})
  private List<PawnInfo> pawnInfoList;

  @ApiModelProperty("证件/证照号码")
  @JsonAlias(value = {"mortgagor_cert_num"})
  private String mortgagorCertNum;


  @ApiModelProperty("抵押人")
  private String mortgagee;

  @ApiModelProperty("抵押权人")
  private String mortgagor;

  @ApiModelProperty("登记编号")
  @JsonAlias(value = {"reg_nm"})
  private String regNm;

  @ApiModelProperty("担保范围")
  private String scope;

  @ApiModelProperty("债务人履行债务期限")
  private String term;

  @ApiModelProperty("状态")
  private String state;

  @ApiModelProperty("登记机关")
  @JsonAlias(value = {"reg_gov"})
  private String regGov;


  @ApiModelProperty("抵押权人证照／证件类型")
  @JsonAlias(value = {"mortgagee_type"})
  private String mortgageeType;

  @ApiModelProperty("更新时间")
  @JsonAlias(value = {"updt_dt"})
  private String updtDt;

  @Data
  public static class PawnInfo {

    @ApiModelProperty("抵押物信息备注")
    private String remark;

    @ApiModelProperty("抵押物详情")
    private String detail;

    @ApiModelProperty("抵押物所有人")
    private String ownership;

    @ApiModelProperty("抵押物名称")
    @JsonAlias(value = {"pawn_nm"})
    private String pawnNm;

  }
}
