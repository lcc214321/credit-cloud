package com.csci.cloud.admin.data.vo;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ben on 2018/8/31. benkris1@126.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class PagingRespVo<T> {

  @ApiModelProperty(value = "从当前位置开始读取")
  private Long skip;

  @ApiModelProperty(value = "请求返回最大数量")
  private Long limit;

  @ApiModelProperty(value = "符合条件的总的条数")
  private Long totalCount;


  @ApiModelProperty(value = "返回内容.")
  private List<T> content;

}
