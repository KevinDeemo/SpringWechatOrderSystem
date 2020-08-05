package com.kevindeemo.service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.WebSocket;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class Websocket {

    private Session session;

    private static CopyOnWriteArraySet<Websocket> websockets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        websockets.add(this);
        log.info("【websocket消息】有新的连接，总数：{}", websockets.size());
    }

    @OnClose
    public void onClose(){
        websockets.remove(this);
        log.info("【websocket消息】连接断开, 总数：{}", websockets.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("【websocket消息】收到客户端发来的消息：{}", message);
    }

    public void sendMessage(String message){
        for (Websocket webSocket : websockets){
            log.info("【websocket消息】广播消息，message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
