package com.csci.cloud.auth.client.config;

import static com.csci.cloud.auth.client.config.CommonConfig.defaultHttpMessageConverters;

import feign.Logger;
import feign.Logger.Level;
import feign.Request;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.optionals.OptionalDecoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.DefaultFeignLoggerFactory;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ben on 2018/9/25. benkris1@126.com
 */
@Configuration()
@ImportAutoConfiguration(classes = CommonConfig.class)
public class CloudAuthConfig {

  private static final int CONNECT_TIMEOUT_MILLIS = 10 * 1000;
  private static final int READ_TIMEOUT_MILLIS = 30 * 1000;

  private static final long PERIOD = 5 * 1000;
  private static final long MAX_PERIOD = 5 * 1000;
  private static final int MAX_ATTEMPTS = 3;

  @Bean
  public Request.Options options() {
    return new Request.Options(CONNECT_TIMEOUT_MILLIS, READ_TIMEOUT_MILLIS);
  }

  @Bean
  @ConditionalOnMissingBean
  public Logger logger() {
    return new Slf4jLogger();
  }

  @Bean
  @ConditionalOnMissingBean
  public Level feignLogger() {
    return Level.FULL;
  }

  @Bean
  public FeignLoggerFactory feignLoggerFactory() {
    return new DefaultFeignLoggerFactory(logger());
  }


  @Bean
  public ErrorDecoder errorDecoder() {
    return new CloudAuthErrorDecoder();
  }

  @Bean
  @ConditionalOnMissingBean
  public Decoder feignDecoder() {
    return new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(defaultHttpMessageConverters)));
  }

  @Bean
  @ConditionalOnMissingBean
  public Encoder feignEncoder() {
    return new SpringEncoder(defaultHttpMessageConverters);
  }


}
