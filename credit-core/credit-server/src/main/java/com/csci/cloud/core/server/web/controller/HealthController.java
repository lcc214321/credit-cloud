package com.csci.cloud.core.server.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/health", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "健康检查", tags = {"健康检查"})
public class HealthController extends AbstractController {

  @ApiOperation(value = "健康检查", notes = "")
  @RequestMapping(value = "/ping", method = RequestMethod.GET)
  public String ping(
      HttpServletResponse response) {
    return "pong";
  }
}