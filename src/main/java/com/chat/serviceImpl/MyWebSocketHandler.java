package com.chat.serviceImpl;

import com.chat.model.AppFriend;
import com.chat.model.AppUser;
import com.chat.model.Message;
import com.chat.service.UserService;
import com.chat.tools.Constant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private static final Logger logger = Logger.getLogger(MyWebSocketHandler.class);
    private static final Map<Integer, WebSocketSession> users = new HashMap<>();
    private static final String CLIENT_ID = "user_id";
    @Autowired
    private UserService userService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        logger.info("MyWebSocketHandler, connect websocket success.......");
        Integer user_id = getClientId(session);
        logger.info("user connected with user_id:" + user_id);
        if (user_id != null) {
            users.put(user_id, session);
            logger.info("MyWebSocketHandler, user_id:" + user_id + ", " + session);
        }
        logger.info("total users in server：" + users.size());
        sentUserStatus(user_id, Constant.ONLINE_MESSAGE);
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

    private void sentUserStatus(int user_id, int status) throws IOException {
        AppUser user = userService.getUserInfo(user_id);
        Message message = new Message();
        message.setMessage_type(status);
        message.setContent("user close connect");
        message.setFrom_user_id(user_id);
        message.setTo_user_id(0);
        message.setTo_avatar("H");
        message.setFrom_avatar("H");
        message.setId(1l);
        message.setSend_time(new Timestamp(System.currentTimeMillis()));
        message.setSend("");
        for(AppFriend x : user.getFriends()) {
            if(x.getB().getStatus() == 1) {
                sendMessageToUser(x.getB().getId(), new TextMessage(message.toString()));
            }
        }
        for(AppFriend x : user.getSelf()) {
            if(x.getA().getStatus() == 1) {
                sendMessageToUser(x.getA().getId(), new TextMessage(message.toString()));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws IOException {
        logger.info("MyWebSocketHandler, connect websocket closed.......");
        int id = getClientId(session);
        userService.updateUserStatus(id, 0);
        users.remove(id);
        logger.info("total users in server：" + users.size());
        sentUserStatus(id, Constant.OFFLINE_MESSAGE);
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
