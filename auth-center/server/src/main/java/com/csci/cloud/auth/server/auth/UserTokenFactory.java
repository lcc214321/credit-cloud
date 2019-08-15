package com.csci.cloud.auth.server.auth;

import com.csci.cloud.auth.server.config.JwtProps;
import com.csci.cloud.auth.server.config.JwtProps;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 */
@Component
public class UserTokenFactory {

    private final static Logger LOG = LoggerFactory.getLogger(UserTokenFactory.class);

    @Autowired
    private JwtProps settings;

    /**
     * 随机生成长度 169 位的AccessToken
     */
    public String createAccessToken(int userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, settings.getTokenExpMinutes());
        Date issuedAt = new Date();
        Date expiredAt = calendar.getTime();

        String accessToken = Jwts.builder()
            .setSubject(String.valueOf(userId))
            .setIssuer(settings.getTokenIssuer())
            .setIssuedAt(issuedAt)
            .setExpiration(expiredAt)
            .signWith(SignatureAlgorithm.HS256, settings.getTokenSigningKey())
            .compact();

        return accessToken;
    }

    /**
     * 生成长度为 43 位的随机字符串
     */
    public String createRefreshToken(int userId) {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        String identity = String.valueOf(userId);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(identity.getBytes(Charset.forName("UTF8")));
            buffer.put(md.digest());
        } catch (NoSuchAlgorithmException e) {
            LOG.error("NoSuchAlgorithmException", e);
        }
        UUID uuid = UUID.randomUUID();
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());

        String key = Base64.encodeBase64URLSafeString(buffer.array());
        return key.toLowerCase();
    }

    /**
     *
     * @param accessToken
     * @param key
     * @return
     */
    public Jwt parseAccessToken(String accessToken, String key) {
        Jwt jwt = Jwts.parser()
            .setSigningKey(key)
            .parse(accessToken);
        return jwt;
    }

}
