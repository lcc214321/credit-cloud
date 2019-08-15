package com.csci.cloud.auth.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 */
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProps {

    private Integer tokenExpMinutes;

    private String tokenIssuer;

    private String tokenSigningKey;

    private Integer refreshTokenExpHours;

    public Integer getRefreshTokenExpHours() {
        return refreshTokenExpHours;
    }

    public void setRefreshTokenExpHours(Integer refreshTokenExpHours) {
        this.refreshTokenExpHours = refreshTokenExpHours;
    }

    public Integer getTokenExpMinutes() {
        return tokenExpMinutes;
    }

    public void setTokenExpMinutes(Integer tokenExpMinutes) {
        this.tokenExpMinutes = tokenExpMinutes;
    }

    public String getTokenIssuer() {
        return tokenIssuer;
    }

    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }

    public String getTokenSigningKey() {
        return tokenSigningKey;
    }

    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }
}

