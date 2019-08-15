package com.csci.cloud.core.server.config;

import com.csci.cloud.core.common.utils.SnowFlake;
import com.csci.cloud.core.server.web.interceptor.SecurityInterceptor;
import java.util.List;
import javax.servlet.MultipartConfigElement;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 应用配置.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {

  @Autowired
  private MultipartConfigElement multipartConfigElement;

  @Bean
  public SecurityInterceptor securityInterceptor() {
    return new SecurityInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //registry.addInterceptor(securityInterceptor()).addPathPatterns("/api/**");

    super.addInterceptors(registry);
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    super.configureMessageConverters(converters);
    converters.add(0, new MappingJackson2HttpMessageConverter());
  }

  /**
   * 添加API 请求路径.
   *
   * @param dispatcherServlet .
   */
  @Bean
  public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
    dispatcherServlet.setDispatchOptionsRequest(true);
    ServletRegistrationBean reg = new ServletRegistrationBean(dispatcherServlet);
    reg.setMultipartConfig(multipartConfigElement);
    reg.getUrlMappings().clear();

    return reg;
  }


  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    //增加swagger相关访问地址
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }


  @Override
  protected void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
        .allowedOrigins("*")
        .allowedMethods("PUT", "POST, GET, PUT, OPTIONS, DELETE, PATCH")
        .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept")
        .exposedHeaders("access-control-allow-headers",
            "access-control-allow-methods",
            "access-control-allow-origin",
            "access-control-max-age",
            "X-Frame-Options")
        .allowCredentials(false).maxAge(3600);
  }


  @Bean
  public SnowFlake snowFlake() {
    return new SnowFlake(1, RandomUtils.nextInt(31));
  }

}
