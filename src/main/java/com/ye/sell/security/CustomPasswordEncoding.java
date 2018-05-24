package com.ye.sell.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomPasswordEncoding {
    public static String MD5EncodeUtf8(String origin) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String finalPassword = bCryptPasswordEncoder.encode(origin);

        System.out.println(bCryptPasswordEncoder.matches("yedeyang", "$2a$10$mBw20WdYfC/imdpZKX4kQeoETrRk9rQ1DWKW7FiGsxLAIbYSH6L7a"));

        return finalPassword;
    }

    public static void main(String[] args) {
        System.out.println(MD5EncodeUtf8("yedeyang"));
    }
}
