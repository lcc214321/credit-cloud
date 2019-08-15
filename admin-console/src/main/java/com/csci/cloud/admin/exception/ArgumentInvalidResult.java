package com.csci.cloud.admin.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArgumentInvalidResult {

  private String field;
  private Object rejectedValue;
  private String defaultMessage;


}
