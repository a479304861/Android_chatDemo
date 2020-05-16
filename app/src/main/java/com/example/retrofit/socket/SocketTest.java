package com.example.retrofit.socket;

import android.util.Log;
import android.view.View;

import com.example.retrofit.utile.StaticUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public  class SocketTest {
    private static final String TAG = "TAG";

    private PrintWriter printWriter=null;
    private BufferedReader in=null;
    private ExecutorService mExecutorService =null;
    private String receiveMsg="";
    private Socket socket=null;


    public void connect() {
            mExecutorService=Executors.newCachedThreadPool();
            mExecutorService.execute(new ConnectService());  //在一个新的线程中请求 Socket 连接
    }
    public void send(String string) {
            mExecutorService.execute(new SendService(string));
    }

    public void disconnect() {
            mExecutorService.execute(new SendService("0"));
    }


    private class SendService implements Runnable {
        private String msg;
        SendService(String msg)
        {
            this.msg = msg;
        }
        @Override
        public void run() {
                Log.d(TAG, "send msg--->"+this.msg);
            printWriter.println(this.msg);
        }
    }
    private void receiveMsg(){
        try {
            while (true) {                                      //步骤三
                if ((receiveMsg = in.readLine()) != null) {
                    Log.d(TAG, "receiveMsg---->" + receiveMsg);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();

        }
    }
    private class ConnectService implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("连接服务器中");
//                socket = new Socket(StaticUtils.SOCKET_URL, StaticUtils.PORT);
                socket=new Socket("47.93.232.127",7777);
                socket.setSoTimeout(40000);
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(   //步骤二
                        socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                mExecutorService.execute(new SendService("1"));
                receiveMsg();
            } catch (Exception e) {
                Log.e(TAG, ("connectService:" + e.getMessage()));   //如果Socket对象获取失败，即连接建立失败，会走到这段逻辑
            }
        }
    }
}
