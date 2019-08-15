package com.csci.cloud.core.server.web.controller;

import javax.annotation.Resource;
import org.springframework.core.env.Environment;

/**
 * 抽象controller .
 */
public class AbstractController {

  @Resource
  protected Environment environment;
}
