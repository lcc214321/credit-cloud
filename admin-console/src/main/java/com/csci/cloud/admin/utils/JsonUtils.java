package com.csci.cloud.admin.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;


public class JsonUtils {

  private static ObjectMapper objectMapper = new ObjectMapper();
  private static final TypeFactory typeFactory = TypeFactory.defaultInstance();

  static {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
  }


  public static String toJsonString(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static <T> T asObject(String source, Class<T> clazz) {
    try {
      return objectMapper.readValue(source, typeFactory.uncheckedSimpleType(clazz));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static <T> T asObject(String source, JavaType type) {
    try {
      return objectMapper.readValue(source, type);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
