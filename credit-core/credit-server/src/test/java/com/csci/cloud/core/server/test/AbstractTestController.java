package com.csci.cloud.core.server.test;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;

public class AbstractTestController {

  public static final String API_KEY = "ntjhb0v6thrwaujqttytbzayow5ozw";
  public static final String SECRET = "m2i5oddjmgzhmgi0ndk2m2jhytjkmznjmzdhymfkmwq";


  public static String calSign(String apiKey, String secret, Long timestamp, String uri,
      Map<String, String> queryMap) {
    queryMap.put("apiKey", apiKey);
    queryMap.put("timestamp", timestamp + "");
    StringBuffer result = new StringBuffer(uri).append("?");
    String reduce = queryMap.entrySet().stream().sorted(Comparator.comparing(Entry::getKey))
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .reduce(StringUtils.EMPTY, (a, b) -> a + "&" + b);

    result.append(reduce.substring(1)).append(secret);
    return Hashing.md5().hashString(result.toString(), Charsets.UTF_8).toString();
  }

}
