package com.csci.cloud.auth.server;

import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 */
@SpringBootApplication
@EnableTransactionManagement
public class AuthServerBootstrap {

    public static void main(String[] args) {

        SpringApplication.run(AuthServerBootstrap.class, args);
    }

    /**
     * 设置语言选项.
     * @return 
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }
}