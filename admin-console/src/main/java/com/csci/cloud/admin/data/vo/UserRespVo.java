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
public class UserRespVo {

  /**
   * 用户id.
   */

  private Integer id;

  /**
   * 租户id.
   */
  @ApiModelProperty(value = "租户id")
  private Integer tenantId;

  /**
   * 登录名.
   */
  @ApiModelProperty(value = "登录名")
  private String loginName;


  /**
   * 邮件.
   */
  @ApiModelProperty(value = "邮件")
  private String email;

  /**
   * 手机.
   */
  @ApiModelProperty(value = "手机")
  private String mobile;

  /**
   * 邮箱是否验证：0-未验证，1-已验证.
   */
  @ApiModelProperty(value = "邮箱是否验证：0-未验证，1-已验证.")
  private Integer emailVerified;

  @ApiModelProperty(value = "电话验证状态 0-未验证，1-已验证.")
  private Integer mobileVerified;

  /**
   * 用户状态：0-未激活，1-已激活，9-删除.
   */
  @ApiModelProperty(value = "用户状态：0-未激活，1-已激活，9-删除.")
  private Integer status;

  /**
   * 用户组id.
   */
  @ApiModelProperty(value = "用户组id")
  private Integer groupId;

  /**
   * 创建时间.
   */
  @ApiModelProperty(value = "创建时间")
  private Date createdAt;

  /**
   * 更新时间.
   */
  @ApiModelProperty(value = "更新时间")
  private Date updatedAt;

  @ApiModelProperty(value = "租户名称")
  private String tenantName;

  @ApiModelProperty(value = "租户代码")
  private String tenantCode;

  @ApiModelProperty(value = "租户类型：0-个人，1-公司")
  private Integer tenantType;

}
