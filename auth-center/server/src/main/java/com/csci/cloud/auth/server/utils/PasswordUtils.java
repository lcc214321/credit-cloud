package com.csci.cloud.auth.server.utils;

import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 */
public class PasswordUtils {

    private static BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();

    public static String encrypt(final String password) {
        return encryptor.encryptPassword(password);
    }

    public static boolean check(final String plainPassword,
        final String encryptedPassword) {
        return encryptor.checkPassword(plainPassword, encryptedPassword);
    }

    public static void main(String[] args) {
        System.out.println(PasswordUtils.encrypt("test"));
    }
}
