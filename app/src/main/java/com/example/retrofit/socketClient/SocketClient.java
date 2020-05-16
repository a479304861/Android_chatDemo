package com.example.retrofit.socketClient;



import com.example.retrofit.Interface.DataManagerObserve;
import com.example.retrofit.Interface.UpdateListener;
import com.example.retrofit.UI.viewmodel.UserViewModel;
import com.example.retrofit.utile.StaticUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketClient {
    private static Socket socket;
    public static Socket getSocket(){
        return socket;
    }
        public static void main() throws URISyntaxException {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            options.reconnectionAttempts = 2;
            options.reconnectionDelay = 1000;
            options.timeout = 20000;
            options.forceNew = true;
            socket = IO.socket(StaticUtils.SOCKETIO_URL, options);


            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    socket.emit("login", UserViewModel.getName().getValue());
                }
            });
            socket.on("reLogin", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject obj = (JSONObject)args[0];
                    try {
                        System.out.println("reLogin-------------->"+obj.get("code"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (obj.getString("code").equals("1")){
                            DataManagerObserve instance = DataManagerObserve.getInstance();
                            instance.setHavingUpdate(true);
                            instance.addUpdateListener(new UpdateListener() {
                                @Override
                                public void update(boolean b) {
                                }
                            });
                            instance.operation();
                    }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            socket.on("reDisconnect", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject obj = (JSONObject)args[0];
                    try {
                        System.out.println("reDisconnect-------------->"+obj.get("code"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            socket.on("reNewMessage", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject obj = (JSONObject)args[0];
                    System.out.println("reNewMessage-------------->");
                    try {
                        if (obj.getString("code").equals("3")){
                            DataManagerObserve instance = DataManagerObserve.getInstance();
                            instance.setHavingMessage(true);
                            instance.setHavingUpdate(true);
                            instance.addUpdateListener(new UpdateListener() {
                                @Override
                                public void update(boolean b) {
                                }
                            });
                            instance.operation();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            //心跳检测
            socket.on("broadcast", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject obj = (JSONObject)args[0];
                    try {
//                        System.out.println(obj.getString("code"));
                        if (obj.getString("code").equals("100")){
                            socket.emit("answer","1000");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

            socket.connect();

//        IO.Options options = new IO.Options();
//        options.transports = new String[]{"websocket"};
//        options.reconnectionAttempts = 2;     // 重连尝试次数
//        options.reconnectionDelay = 1000;     // 失败重连的时间间隔(ms)
//        options.timeout = 20000;              // 连接超时时间(ms)
//        options.forceNew = true;
//        options.query = "username=test1&password=test1&appid=com.xncoding.apay2";
//        socket = IO.socket("http://10.0.2.2:9092/", options);
//        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void call(Object... args) {
//                // 客户端一旦连接成功，开始发起登录请求
//                Params params = new Params();
//                socket.emit("login", params, (Ack) args1 -> {
//                    logger.info("回执消息=" + Arrays.stream(args1).map(Object::toString).collect(Collectors.joining(",")));
//                });
//            }
//        }).on("login", new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                logger.info("接受到服务器房间广播的登录消息：" + Arrays.toString(args));
//            }
//        }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                logger.info("Socket.EVENT_CONNECT_ERROR");
//                socket.disconnect();
//            }
//        }).on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                logger.info("Socket.EVENT_CONNECT_TIMEOUT");
//                socket.disconnect();
//            }
//        }).on(Socket.EVENT_PING, new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                logger.info("Socket.EVENT_PING");
//            }
//        }).on(Socket.EVENT_PONG, new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                logger.info("Socket.EVENT_PONG");
//            }
//        }).on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                logger.info("-----------接受到消息啦--------" + Arrays.toString(args));
//            }
//        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                logger.info("客户端断开连接啦。。。");
//                socket.disconnect();
//            }
//        });
//        socket.connect();
    }
}
