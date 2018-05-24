package com.ye.sell.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class WebSocketTest {

    private WebSocket webSocket = new WebSocket();

    @Test
    public void sendMessage() {
        webSocket.sendMessage("123");
    }
}