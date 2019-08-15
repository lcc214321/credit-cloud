package com.csci.cloud.admin.data.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
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
@ToString
public class RefreshTokenReqVo {

  @ApiModelProperty(value = "refreshToken")
  @NotBlank
  private String refreshToken;
}
