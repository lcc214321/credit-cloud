package com.csci.cloud.admin.web.controller;

import com.csci.cloud.admin.service.AppKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RequestMapping(value = "/api/v1/apps")
public class AppController extends AbstractController {

  @Autowired
  AppKeyService appKeyService;


}
