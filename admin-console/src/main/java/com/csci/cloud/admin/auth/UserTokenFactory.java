package com.csci.cloud.admin.auth;

import com.csci.cloud.admin.config.JwtProps;
import com.csci.cloud.admin.exception.AuthenticationException;
import com.csci.cloud.admin.utils.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;
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
     *
     * @return <token,expiredAt>
     */
    public Pair<String, Date> createAccessToken(int userId) {
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

        return Pair.of(accessToken, expiredAt);
    }

    /**
     * 生成长度为 43 位的随机字符串
     */
    public Pair<String, Date> createRefreshToken(int userId) {
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
        return Pair.of(key.toLowerCase(), DateUtils.addHours(new Date(), settings.getRefreshTokenExpHours()));
    }

    /**
     * @param accessToken
     * @return
     */
    public String parseAccessToken(String accessToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(settings.getTokenSigningKey())
                    .parseClaimsJws(accessToken);
            Claims body = claimsJws.getBody();
            String userId = body.getSubject();
            return userId;
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException(ErrorCode.AUTH_EXPIRE_ERROR);
        } catch (SignatureException e) {
            throw new AuthenticationException(ErrorCode.AUTH_SIGNATURE_ERROR);
        } catch (Throwable e) {
            throw new AuthenticationException(ErrorCode.AUTH_ERROR);
        }
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        Date issuedAt = new Date();
        Date expiredAt = calendar.getTime();

        String accessToken = Jwts.builder()
                .setSubject(String.valueOf(123456))
                .setIssuer("opbu.chinacsci.com")
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(SignatureAlgorithm.HS256, "nepkq3d6m2fum1zmvwuwtxzdchowzw")
                .compact();

        System.out.println(accessToken);
    }

}
