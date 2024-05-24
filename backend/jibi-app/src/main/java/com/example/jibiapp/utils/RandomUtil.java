package com.example.jibiapp.utils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomUtil {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int USERNAME_LENGTH = 8;
    private static final int PASSWORD_LENGTH = 12;
    private static final Random RANDOM = new SecureRandom();

    public static String generateRandomUsername() {
        return generateRandomString(USERNAME_LENGTH);
    }

    public static String generateRandomPassword() {
        return generateRandomString(PASSWORD_LENGTH);
    }

    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
