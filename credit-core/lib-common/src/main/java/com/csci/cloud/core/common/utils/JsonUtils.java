package com.csci.cloud.core.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Json工具类.
 *
 * @author leon
 */
@Slf4j
public class JsonUtils {

  private static final String EMPTY_JSON = "{}";
  private static final String EMPTY_JSONS = "[]";

  private static final ObjectMapper MAPPER;


  static {
    MAPPER = new ObjectMapper();
    MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
  }

  /**
   * java实体对象转换成json字符串 默认对象中出现值为null时 隐藏json中的key.
   */
  public static String toJson(Object obj) {
    return JsonUtils.toJson(obj, MAPPER);
  }

  /**
   * java实体对象转换成json字符串.
   */
  private static String toJson(Object obj, ObjectMapper mapper) {
    if (obj == null) {
      return StringUtils.EMPTY;
    }
    try {
      StringWriter writer = new StringWriter();
      mapper.writeValue(writer, obj);
      return writer.toString();
    } catch (IOException e) {

      log.error("JsonUtils transformation obj to string has error!", e);
    }
    return StringUtils.EMPTY;
  }


  /**
   * json字符串转换为对应的java实体对象.
   */
  public static <T> T toObj(String jsonStr, Class<T> objClass) {
    if (isEmpty(jsonStr)) {
      return null;
    }

    try {
      return MAPPER.readValue(jsonStr, objClass);
    } catch (IOException e) {
      if (log.isDebugEnabled()) {
        log.debug("JsonUtils transformation string to obj has Eroor!", e);
      }
    }
    return null;
  }

  private static <T> T newInstance(Class<T> objClass) {
    try {
      return objClass.newInstance();
    } catch (Exception e) {
      log.error("failed to create new instance", e);
      throw new RuntimeException("cannot create new instance", e);
    }
  }

  /**
   * string -> 集合.
   * @param jsonStr .
   * @param objClass .
   * @param <T> .
   * @return .
   */
  public static <T> List<T> toObjList(String jsonStr, Class<T> objClass) {
    if (isEmpty(jsonStr)) {
      return Collections.emptyList();
    }

    try {
      return MAPPER.readValue(jsonStr, getCollectionType(ArrayList.class, objClass));
    } catch (IOException e) {
      log.error("JsonUtils transformation string to objList has Eroor!", e);
    }
    return Collections.emptyList();
  }

  public static boolean isEmpty(String content) {
    return StringUtils.isBlank(content) || EMPTY_JSON.equals(content) || EMPTY_JSONS
        .equals(content);
  }

  public static boolean isNotEmpty(String content) {
    return !isEmpty(content);
  }


  private static JavaType getCollectionType(Class<?> paramClass, Class<?>... elementClasses) {
    return MAPPER.getTypeFactory().constructParametricType(paramClass, elementClasses);
  }

  /**
   * String -> map .
   * @param jsonStr .
   * @return .
   */
  public static Map<String, Object> jsonToMap(String jsonStr) {
    Map<String, Object> tempMap = null;
    try {
      tempMap = MAPPER.readValue(jsonStr, new TypeReference<Map<String, Object>>() {
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
    return tempMap;
  }

  public static Map<String, Object> objectToMap(Object obj) {
    return (Map) MAPPER.convertValue(obj, Map.class);
  }

  public static <T> T mapToObject(Map map, Class<T> clazz) {
    return MAPPER.convertValue(map, clazz);
  }

}
