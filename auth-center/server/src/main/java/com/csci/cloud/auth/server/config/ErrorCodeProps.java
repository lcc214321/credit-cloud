package com.csci.cloud.auth.server.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 */
@Component
@PropertySource(value = {"classpath:error-code.properties"}, encoding = "UTF-8")
public class ErrorCodeProps {

}
