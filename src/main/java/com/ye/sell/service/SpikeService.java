package com.ye.sell.service;

import org.springframework.stereotype.Service;

@Service
public class SpikeService {

    private static Integer stock;

    private static Integer buyer;

    static {
        stock = 1000;

        buyer = 0;
    }

    public String query() {

        return "The remaining quantity of goods " + stock + " Buying " + buyer;
    }

    public synchronized String go() {
        if (stock <= 0)
            return "Sold out";

        stock --;
        buyer ++;

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "The remaining quantity of goods " + stock + " Buying " + buyer;
    }
}
