package com.example.retrofit.utile;

public  class StaticUtils {
                                               //10.0.2.2
                                                //localhost:8080
                                               //132.70.132.131
                                               // 192.168.0.112
    public static final String SOCKET_URL="192.168.0.112";
    public static final String SOCKETIO_URL="http://"+SOCKET_URL+":9091/";
    public static final String BASE_URL="http://"+SOCKET_URL+":8080";
    public static final String WEB_SOCKET_URL="ws://"+SOCKET_URL+":8080/websocket/server";
    public  static  final int REQUEST_TIMEOUT=10000;
    public static final int PORT = 8000;
    public static final int PERMISSION_REQUEST_CORD=1;
}
