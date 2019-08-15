package com.csci.cloud.core.server.web.annotation;

import com.csci.cloud.core.common.enums.PermissionEnum;
import com.csci.cloud.core.common.enums.ServiceTypeEnum;
import com.csci.cloud.core.server.utils.ApiDefinesEnum;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ben on 2018/9/27. benkris1@126.com 接口定义信息,包含接口编号,接口名称,接口类型,权限.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiDefine {


  /**
   * 接口权限列表.
   * @return
   */
  PermissionEnum[] permissions() default {};

  /**
   * 接口定义.
   * @return
   */
  ApiDefinesEnum value();


}
