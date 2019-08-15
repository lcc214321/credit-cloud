package com.csci.cloud.auth.server.web.controller;

import javax.annotation.Resource;
import org.springframework.core.env.Environment;

/**
 */
public class AbstractController {
    @Resource
    protected Environment environment;
}
