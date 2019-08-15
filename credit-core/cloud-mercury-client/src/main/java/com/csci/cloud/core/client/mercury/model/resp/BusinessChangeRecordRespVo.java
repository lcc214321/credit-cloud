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
 * 企业工商信息变更.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder
public class BusinessChangeRecordRespVo {


  @ApiModelProperty(value = "企业Id")
  @JsonAlias(value = {"company_id"})
  private String companyId;

  @ApiModelProperty(value = "变更事项")
  @JsonAlias(value = {"change_item"})
  private String changeItem;

  @ApiModelProperty(value = "更改时间")
  @JsonAlias(value = {"change_dt"})
  private String changeDt;

  @ApiModelProperty(value = "变更前")
  @JsonAlias(value = {"content_before"})
  private String contentBefore;

  @ApiModelProperty(value = "变更后")
  @JsonAlias(value = {"content_after"})
  private String contentAfter;

  @ApiModelProperty(value = "更新时间")
  @JsonAlias(value = {"updt_dt"})
  private String updtDt;
}
