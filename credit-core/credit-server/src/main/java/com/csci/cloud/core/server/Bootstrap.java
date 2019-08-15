package com.csci.cloud.core.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by ben on 2018/9/25. benkris1@126.com
 */
@SpringBootApplication()
@EnableSwagger2
@EnableFeignClients(basePackages = {"com.csci.cloud.auth.client", "com.csci.cloud.core.client"})
@EnableZuulProxy
public class Bootstrap {

  public static void main(String[] args) {

    SpringApplication.run(Bootstrap.class, args);
  }

}
