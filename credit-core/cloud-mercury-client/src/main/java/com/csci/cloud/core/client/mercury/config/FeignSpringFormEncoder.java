package com.csci.cloud.core.client.mercury.config;

import static feign.form.ContentType.MULTIPART;
import static java.util.Collections.singletonMap;

import com.csci.cloud.core.client.mercury.model.req.BaseReqVo;
import com.csci.cloud.core.common.utils.JsonUtils;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringFormEncoder;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;
import lombok.val;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

public class FeignSpringFormEncoder extends SpringFormEncoder {

  private String apiKey;

  private String apiSecret;

  /**
   * Constructor with the default Feign's encoder as a delegate.
   */
  public FeignSpringFormEncoder(String apiKey, String apiSecret) {
    this(new Default());
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
  }


  /**
   * Constructor with specified delegate encoder.
   *
   * @param delegate delegate encoder, if this encoder couldn't encode object.
   */
  public FeignSpringFormEncoder(Encoder delegate) {
    super(delegate);

    val processor = (MultipartFormContentProcessor) getContentProcessor(MULTIPART);
    processor.addWriter(new SpringSingleMultipartFileWriter());
    processor.addWriter(new SpringManyMultipartFilesWriter());
  }

  //只拓展了该方法，使其能够支持 MultipartFile[]
  @Override
  public void encode(Object object, Type bodyType, RequestTemplate template)
      throws EncodeException {

    if (object instanceof BaseReqVo) {
      val data = JsonUtils.objectToMap(object);
      data.put("timestamp", System.currentTimeMillis());
      data.put("accessKey", apiKey);
      String signPre = data.entrySet().stream().sorted(Comparator.comparing(Entry::getKey))
          .map(e -> e.getKey() + e.getValue())
          .reduce(apiSecret, (s1, s2) -> s1 + s2);
      byte[] signHash = Hashing.hmacSha1(apiSecret.getBytes(Charsets.UTF_8))
          .hashString(signPre, Charsets.UTF_8).asBytes();
      String signBase64 = Base64.encodeBase64String(signHash);
      data.put("signature", signBase64);
      super.encode(data, MAP_STRING_WILDCARD, template);
      return;
    }
    super.encode(object, bodyType, template);
  }
}


