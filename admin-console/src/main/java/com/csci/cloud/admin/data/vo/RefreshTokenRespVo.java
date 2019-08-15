package com.csci.cloud.admin.data.vo;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ben on 2018/9/17. benkris1@126.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RefreshTokenRespVo {

  @ApiModelProperty(value = "访问token")
  private String accessToken;

  @ApiModelProperty(value = "访问token过期时间")
  private Date accessTokenExpireAt;

  @ApiModelProperty(value = "刷新token")
  private String refreshToken;

  @ApiModelProperty(value = "刷新token过期时间")
  private Date refreshTokenExpireAt;
}
