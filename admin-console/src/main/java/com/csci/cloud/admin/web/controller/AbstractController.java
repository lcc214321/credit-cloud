package com.csci.cloud.admin.web.controller;

import javax.annotation.Resource;
import org.springframework.core.env.Environment;

/**
 */
public class AbstractController {

  @Resource
  protected Environment environment;
}
