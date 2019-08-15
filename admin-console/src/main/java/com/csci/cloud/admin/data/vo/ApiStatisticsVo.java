package com.csci.cloud.admin.data.vo;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ben on 2018/9/12. benkris1@126.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApiStatisticsVo {



  @ApiModelProperty(value = "分组.如:daily")
  private String internal;

  @ApiModelProperty(value = "统计结果内容")
  private List<ApiStatisticsItem> items;

  @ApiModelProperty(value = "统计日期点 如 2018-09-10")
  private String timePoint;

  @ApiModelProperty(value = "总的条数")
  private Long total;

  @Setter
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  @Builder
  public static class ApiStatisticsItem {



    @ApiModelProperty(value = "服务类型 0: 区块链 1:企业征信 2:个人征信")
    private Integer serverType;

    @ApiModelProperty(value = "调用数量")
    private Long count;
  }

}
