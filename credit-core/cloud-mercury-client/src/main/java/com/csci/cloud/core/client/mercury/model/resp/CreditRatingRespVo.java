package com.csci.cloud.core.client.mercury.model.resp;

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
public class CreditRatingRespVo {

  @ApiModelProperty(value = "流水号")
  private  String compyCreditratingSid;


  @ApiModelProperty(value = "公告日期")
  private String noticeDt;

  @ApiModelProperty(value = "机构当事人属性")
  private String itypeCd;

  @ApiModelProperty(value = "机构当事人")
  private String itype;


  @ApiModelProperty(value = "评级时间")
  private String ratingDt;

  @ApiModelProperty(value = "信用评级类型")
  private String rateTypeId;


  @ApiModelProperty(value = "信用评级")
  private String rateType;

  @ApiModelProperty(value = "信用")
  private String rating;

  @ApiModelProperty(value = "评级展望")
  private String rateFwd;

  @ApiModelProperty(value = "评级展望类型")
  private String rateFwdCd;


  @ApiModelProperty(value = "评级机构标识符")
  private String creditOrgId;


  @ApiModelProperty(value = "评级信息来源类别")
  private String dataSrcType;


  @ApiModelProperty(value = "评级公司")
  private String dataSrcTypeVal;


  @ApiModelProperty(value = "资料来源")
  private String dataSrc;

  @ApiModelProperty(value = "备注")
  private String remark;

  @ApiModelProperty(value = "更新时间")
  private String updtTime;
}
