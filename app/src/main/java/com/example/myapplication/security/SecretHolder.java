package com.example.myapplication.security;

public class SecretHolder {

    private static final String PART_ONE = "my-api-";
    private static final String PART_TWO = "secret-123";

    public static String getSecret() {
        return PART_ONE + PART_TWO;
    }
}
