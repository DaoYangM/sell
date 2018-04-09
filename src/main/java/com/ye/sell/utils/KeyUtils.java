package com.ye.sell.utils;

import java.util.Random;

public class KeyUtils {
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + number.toString();
    }

    public static void main(String[] args) {
        System.out.println(getUniqueKey());
    }
}
