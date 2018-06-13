package com.wombat.blw.Service.impl;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.util.concurrent.CopyOnWriteArraySet;

//@Component
//@ServerEndpoint("/websocket")
@Slf4j
public class WebSocket {

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen

    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("[WebSocket message] Connected! total: {}", webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("[WebSocket message] Disconnected! total: {}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("[WebSocket message] Message received from the client: {}", message);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            log.info("[WebSocket message] Broadcast message: {}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
