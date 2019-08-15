package com.csci.cloud.core.client.mercury.model.resp;

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
public class BondDefaultsRespVo {

  @ApiModelProperty(value = "余额")
  private Number remainingVol;

  @ApiModelProperty(value = "发行期限")
  private String period;

  @ApiModelProperty(value = "债券简称")
  private String bondSNm;

  @ApiModelProperty("到日期")
  private String mrtyDt;

  @ApiModelProperty("债券名称")
  private String bondNm;

  @ApiModelProperty("剩余期限")
  private String bondRemainder;

  @ApiModelProperty("债券代码")
  private String bondCd;

  @ApiModelProperty("还本方式")
  private String paymentType;


  private String occurDt;

  @ApiModelProperty(value = "票面利率")
  private String couponRate;

  @ApiModelProperty("债券评级")
  private String bondRating;

  @ApiModelProperty(value = "公告列表")
  private List<BondViolation> bondViolationList;

  @ApiModelProperty(value = "债券类型")
  private String bondType;

  @ApiModelProperty(value = "主体评级")
  private String compyRating;

  @ApiModelProperty(value = "上市地点")
  private String issuePlace;

  @ApiModelProperty(value = "发行时间")
  private String issueDt;

  @ApiModelProperty(value = "债券规模")
  private String issueVol;


  @Data
  public static class BondViolation {

    private String noticeTitle;

    private String infoCd;

    private String noticeDt;
  }
}
