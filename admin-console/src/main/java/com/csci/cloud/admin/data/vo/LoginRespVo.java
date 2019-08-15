package com.csci.cloud.admin.data.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ben on 2018/9/11. benkris1@126.com
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"password"})
public class LoginRespVo extends UserRespVo{

  @ApiModelProperty(value = "访问token")
  private String accessToken;

  @ApiModelProperty(value = "访问token过期时间")
  private Date accessTokenExpireAt;

  @ApiModelProperty(value = "刷新token")
  private String refreshToken;

  @ApiModelProperty(value = "刷新token过期时间")
  private Date refreshTokenExpireAt;
}
