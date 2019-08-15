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
public class AdminPenaltyRespVo {

  @ApiModelProperty(value = "行政处罚内容")
  @JsonAlias(value = {"admin_penalty"})
  private String adminPenalty;

  @ApiModelProperty(value = "决定文书号")
  @JsonAlias(value = {"decision_num"})
  private String decisionNum;

  @ApiModelProperty(value = "决定日期")
  @JsonAlias(value = {"decision_dt"})
  private String decisionDt;

  @ApiModelProperty(value = "决定机关")
  @JsonAlias(value = {"decision_gov"})
  private String decisionGov;

  @ApiModelProperty(value = "处罚类型")
  @JsonAlias(value = {"illegal_type"})
  private String illegalType;

  @ApiModelProperty(value = "公示日期")
  @JsonAlias(value = {"notice_dt"})
  private  String noticeDt;

  @ApiModelProperty(value = "物理主键")
  @JsonAlias(value = {"record_id"})
  private  String recordId;

  @ApiModelProperty(value = "更新日期")
  @JsonAlias(value = {"updt_dt"})
  private String updtDt;
}
