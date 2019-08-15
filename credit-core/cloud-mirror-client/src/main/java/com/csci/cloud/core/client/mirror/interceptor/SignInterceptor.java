package com.csci.cloud.core.client.mirror.interceptor;

import com.csci.cloud.core.client.mirror.exception.CloudMirrorClientException;
import com.csci.cloud.core.common.ErrorEnums.CommonErrorEnum;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by ben on 2018/9/25. benkris1@126.com
 */
@Slf4j
public class SignInterceptor implements RequestInterceptor {

  private String apiKey;

  private String secretKey;

  private String appId;

  /**
   * 云镜http client 拦截器,主要是添加签名.
   * @param apiKey .
   * @param secretKey .
   * @param appId .
   */
  public SignInterceptor(String apiKey, String secretKey, String appId) {
    this.apiKey = apiKey;
    this.secretKey = secretKey;
    this.appId = appId;
  }

  @Override
  public void apply(RequestTemplate template) {

    template.query("apikey", apiKey);
    template.query("ts", System.currentTimeMillis() + "");
    template.query("appId", appId);
    String sign = calSign(template.method(), template.url(), template.queries(),
        null != template.body() ? new String(template.body(), Charsets.UTF_8) : StringUtils.EMPTY,
        secretKey);
    template.query("sign", sign);
  }

  private static String calSign(String method, String serviceId,
      Map<String, Collection<String>> queryMap, String body, String secretKey) {
    StringBuffer result = new StringBuffer();
    result.append(method)
        .append(serviceId)
        .append(createOrderedUrlParamFromMap(queryMap))
        .append(body)
        .append(secretKey);
    try {
      String encodeResult = URLEncoder.encode(result.toString(), Charsets.UTF_8.name());
      return Hashing.md5().hashString(encodeResult, Charsets.UTF_8).toString();
    } catch (Throwable e) {
      log.error(e.getMessage(), e);
      throw new CloudMirrorClientException(CommonErrorEnum.INTERNAL_SERVER_ERROR);
    }
  }


  private static String createOrderedUrlParamFromMap(Map<String, Collection<String>> queryMap) {
    if (queryMap == null || queryMap.isEmpty()) {
      return "";
    }
    StringBuffer params = new StringBuffer();
    queryMap.entrySet().stream()
        .filter(entry -> null != entry)
        .sorted(Comparator.comparing(Entry::getKey))
        .forEach(entry -> params.append(entry.getKey().trim()).append("=")
            .append(Lists.newArrayList(entry.getValue()).get(0)));
    return params.toString();
  }
}
