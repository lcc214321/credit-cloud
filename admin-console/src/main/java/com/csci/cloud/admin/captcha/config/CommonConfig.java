package com.csci.cloud.admin.captcha.config;

import java.util.List;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * Created by ben on 2018/9/25. benkris1@126.com
 */
public class CommonConfig {

  public static final ObjectFactory<HttpMessageConverters>
      defaultHttpMessageConverters = defaultHttpMessageConverters();

  private static ObjectFactory<HttpMessageConverters> defaultHttpMessageConverters() {
    List<HttpMessageConverter<?>> converters = new HttpMessageConverters().getConverters();
    return () -> new HttpMessageConverters(false, converters);
  }

}
