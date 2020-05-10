package com.example.retrofit.webSocket;

import com.example.retrofit.Interface.DataManager;
import com.example.retrofit.Interface.UpdateListener;
import com.example.retrofit.UI.viewmodel.FriendViewModel;
import com.example.retrofit.domain.ReceiveMessage;
import com.google.gson.Gson;

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
    public void onMessage(String message) {
        System.out.println(message);
        Gson gson = new Gson();
        ReceiveMessage message1=gson.fromJson(message,ReceiveMessage.class);
        System.out.println(message1.toString());
        if (message1.getCode()==100) {
            System.out.println("receiveBreast!!!!!!!!!!!!");
            DataManager instance = DataManager.getInstance();
            instance.setHavingUpdate(true);
            instance.addUpdateListener(new UpdateListener() {
                @Override
                public void update(boolean b) {
                }
            });
            instance.operation();
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("");
    }
}
