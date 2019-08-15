package com.csci.cloud.admin.data.vo;

import com.csci.cloud.admin.data.vo.RegisterReqVo.RegisterSequenceProvider;
import com.csci.cloud.admin.exception.AppBizException;
import com.csci.cloud.admin.utils.ErrorCode;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

/**
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel
@ToString
@GroupSequenceProvider(RegisterSequenceProvider.class)
public class RegisterReqVo {

  @NotBlank
  @ApiModelProperty(value = "登录名", required = true)
  @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,24}$)",message = "6到24位（字母，数字，下划线，减号)")
  private String loginName;

  @NotBlank
  @ApiModelProperty(value = "登录密码", required = true)
  @Pattern(regexp = "(^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$)",message = "6-16位数字和字母的组合")
  private String password;

  @ApiModelProperty(value = "手机.手机和邮箱二选一")
  @NotBlank(groups = EmailGroup.class )
  private String mobile;

  @ApiModelProperty(value = "邮箱.手机和邮箱二选一")
  @Email
  @NotBlank(groups = EmailGroup.class)
  private String email;

  @NotBlank
  @ApiModelProperty(value = "公司名称")
  private String companyName;

  @NotBlank
  @ApiModelProperty(value = "公司标识")
  private String companyCode;

  /**
   * PERSONAL("个人",0),
   *  COMPANY("公司",1).
   */
  @ApiModelProperty(value = "账号类型")
  private Integer type;

  public interface PhoneGroup {

  }

  public interface EmailGroup {

  }

  public  static class RegisterSequenceProvider
      implements DefaultGroupSequenceProvider<RegisterReqVo> {

    @Override
    public List<Class<?>> getValidationGroups(RegisterReqVo registerReqVo) {
      if (null == registerReqVo) {
        return Lists.newArrayList(RegisterReqVo.class);
      }
      List<Class<?>> sequence = new ArrayList<>();

      // Apply all validation rules from ConditionalValidation group
      // only if someField has given value

      if (StringUtils.isNotBlank(registerReqVo.getEmail())) {
        sequence.add(EmailGroup.class);
      }else if (StringUtils.isNotBlank(registerReqVo.getMobile())) {
        sequence.add(PhoneGroup.class);
      }else {
        throw new AppBizException(ErrorCode.INVALID_REQ_PARAMETER);
      }
      // Apply all validation rules from default group
      sequence.add(RegisterReqVo.class);

      return sequence;
    }
  }

}
