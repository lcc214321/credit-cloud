package com.csci.cloud.admin.web.swagger;

import javax.servlet.ServletContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 */
@Configuration
public class Swagger2 {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        //为当前包路径
        .apis(RequestHandlerSelectors.basePackage("com.csci.cloud.admin.web.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        //页面标题
        .title("Spring Boot 测试使用 Swagger2 构建RESTful API")
        //创建人
        .contact(new Contact("OPBU", "http://www.chinacsci.com", ""))
        //版本号
        .version("1.0")
        //描述
        .description("API 描述")
        .build();
  }
}
