package com.csci.cloud.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
@ServletComponentScan(basePackages = {"com.csci.cloud.admin"})
@EnableFeignClients(basePackages = {"com.csci.cloud.admin.captcha"})
public class AccountBootstrap {


  public static void main(String[] args) {

    SpringApplication.run(AccountBootstrap.class, args);
  }

}