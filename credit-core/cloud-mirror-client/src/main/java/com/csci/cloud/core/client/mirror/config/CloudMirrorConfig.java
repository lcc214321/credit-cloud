package com.csci.cloud.core.client.mirror.config;

import static com.csci.cloud.core.client.mirror.config.CommonConfig.defaultHttpMessageConverters;

import com.csci.cloud.core.client.mirror.CloudMirrorFallBackFactory;
import com.csci.cloud.core.client.mirror.interceptor.SignInterceptor;
import feign.Client;
import feign.Logger;
import feign.Logger.Level;
import feign.Request;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.hystrix.FallbackFactory;
import feign.okhttp.OkHttpClient;
import feign.optionals.OptionalDecoder;
import feign.slf4j.Slf4jLogger;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.DefaultFeignLoggerFactory;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
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
public class CloudMirrorConfig {

  private static final int CONNECT_TIMEOUT_MILLIS = 10 * 1000;
  private static final int READ_TIMEOUT_MILLIS = 30 * 1000;

  private static final long PERIOD = 5 * 1000;
  private static final long MAX_PERIOD = 5 * 1000;
  private static final int MAX_ATTEMPTS = 3;

  @Value("${client.cloud-mirror.apiKey}")
  private String apiKey;

  @Value("${client.cloud-mirror.secretKey}")
  private String secretKey;

  @Value("${client.cloud-mirror.appId}")
  private String appId;

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
    return new CloudMirrorErrorDecoder();
  }

  @Bean
  @ConditionalOnMissingBean
  public Decoder feignDecoder() {
    return new OptionalDecoder(
        new ResponseEntityDecoder(new SpringDecoder(defaultHttpMessageConverters)));
  }

  @Bean
  @ConditionalOnMissingBean
  public Encoder feignEncoder() {
    return new SpringEncoder(defaultHttpMessageConverters);
  }

  @Bean
  @ConditionalOnMissingBean
  public FallbackFactory fallbackFactory() {
    return new CloudMirrorFallBackFactory();
  }

  @Bean
  public RequestInterceptor signInterceptor() {
    return new SignInterceptor(apiKey, secretKey, appId);
  }

  /**
   * 云镜自定义http client .
   * @param cachingFactory .
   * @param clientFactory .
   * @return http client .
   * @throws NoSuchAlgorithmException .
   * @throws KeyManagementException .
   * @throws NoSuchProviderException .
   */
  @Bean("cloud-mirror")
  @ConditionalOnMissingBean(name = "cloud-mirror")
  public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
      SpringClientFactory clientFactory)
      throws NoSuchAlgorithmException, KeyManagementException, NoSuchProviderException {

    SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
    X509TrustManager tm = new X509TrustManager() {
      @Override
      public void checkClientTrusted(X509Certificate[] chain,
          String authType) throws CertificateException {
      }

      @Override
      public void checkServerTrusted(X509Certificate[] chain,
          String authType) throws CertificateException {
      }

      @Override
      public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[]{};
      }
    };
    sslContext.init(null, new TrustManager[]{tm}, new java.security.SecureRandom());
    return new LoadBalancerFeignClient(new OkHttpClient(new okhttp3.OkHttpClient.Builder()
        .hostnameVerifier((s, sslSession) -> true)
        .sslSocketFactory(sslContext.getSocketFactory(), tm)
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()),
        cachingFactory, clientFactory);
  }
}
