package com.example.retrofit.webSocket;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketTest extends WebSocketClient {


    public WebSocketTest(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onOpen(ServerHandshake shake) {
        System.out.println(shake.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String paramString) {
        System.out.println(paramString);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("关闭");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("发生错误");
    }
}
