package com.chat.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private static final Logger logger = Logger.getLogger(MyWebSocketHandler.class);

    private static final Map<Integer, WebSocketSession> users = new HashMap<>();

    private static final String CLIENT_ID = "user_id";

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("MyWebSocketHandler, connect websocket success.......");
        Integer user_id = getClientId(session);
        logger.info("user connected with user_id:" + user_id);
        if (user_id != null) {
            users.put(user_id, session);
            logger.info("MyWebSocketHandler, user_id:" + user_id + ", " + session);
        }
        logger.info("total users in server：" + users.size());
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> message) throws Exception {
        Gson gson = new Gson();
        Map<String, Object> msg = gson.fromJson(message.getPayload().toString(),
                                                new TypeToken<Map<String, Object>>(){}.getType());
        logger.info("MyWebSocketHandler, handleMessage......." + message.getPayload() + "..........." + msg);
        webSocketSession.sendMessage(message);
    }

    public void sendMessageToUser(int clientId, WebSocketMessage<?> message) throws IOException {
        logger.info("message content：" + message.toString());
        logger.info("list user online：" + users.toString());
        if (users.get(clientId) == null) {
            logger.info("MyWebSocketHandler, user offline");
            return;
        }
        WebSocketSession session = users.get(clientId);
        if (!session.isOpen()) {
            return;
        }
        session.sendMessage(message);
    }

    public void sendMsgToAllUsers(WebSocketMessage<?> message) throws IOException {
        Set<Integer> client_ids = users.keySet();
        WebSocketSession session;
        for (Integer client_id : client_ids) {
            session = users.get(client_id);
            if (session.isOpen()) {
                session.sendMessage(message);
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        users.remove(getClientId(webSocketSession));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        logger.info("MyWebSocketHandler, connect websocket closed.......");
        users.remove(getClientId(session));
        logger.info("total users in server：" + users.size());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private Integer getClientId(WebSocketSession session) {
        try {
            Integer clientId = (Integer) session.getAttributes().get(CLIENT_ID);
            return clientId;
        } catch (Exception e) {
            logger.info("MyWebSocketHandler, getClientId():" + e.getMessage());
        }
        return null;
    }

    public boolean checkUserIfOnline(int user_id) {
        boolean if_online = true;
        if (users.get(user_id) == null) {
            if_online = false;
        }
        return if_online;
    }
}
