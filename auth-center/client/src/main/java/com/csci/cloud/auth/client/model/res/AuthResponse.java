package com.csci.cloud.auth.client.model.res;

import lombok.Data;

/**
 */
@Data
public class AuthResponse <T>{

  private String code;
  private String errorMessage;
  private T data;
}
