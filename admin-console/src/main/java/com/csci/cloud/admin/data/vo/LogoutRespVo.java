package com.csci.cloud.admin.data.vo;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ben on 2018/9/18. benkris1@126.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LogoutRespVo {

  @ApiModelProperty(value = "用户ID")
  private Integer userId;

  @ApiModelProperty(value = "退出时间")
  private Date logoutTime;
}
