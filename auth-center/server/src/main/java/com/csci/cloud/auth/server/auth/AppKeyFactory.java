package com.csci.cloud.auth.server.auth;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 */
@Component
public class AppKeyFactory {

    private final static Logger LOG = LoggerFactory.getLogger(AppKeyFactory.class);

    /**
     * 生成长度为 30 位的随机字符串
     */
    public String generateAppKey() {
        String uuid = UUID.randomUUID().toString();
        String key = null;
        for (int i = 0; i < 20; i++) {
            key = Base64.encodeBase64URLSafeString(md5(uuid).getBytes());
            if (key.indexOf('-') == -1) {
                return key.toLowerCase();
            }
        }
        return key.toLowerCase();
    }

    /**
     * 生成长度为 43 位的随机字符串
     */
    public String generateAppSecret() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String key = null;
        for (int i = 0; i < 20; i++) {
            key = Base64.encodeBase64URLSafeString(uuid.getBytes());
            if (key.indexOf('-') == -1) {
                return key.toLowerCase();
            }
        }
        return key.toLowerCase();
    }


    private String md5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            LOG.error("NoSuchAlgorithmException caught!");
        } catch (UnsupportedEncodingException e) {
            LOG.error("UnsupportedEncodingException", e);
        }

        byte[] byteArray = messageDigest.digest();
        return Base64.encodeBase64URLSafeString(byteArray);
    }
}
