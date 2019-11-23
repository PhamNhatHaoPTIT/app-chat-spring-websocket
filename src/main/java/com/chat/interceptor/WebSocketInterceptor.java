package com.chat.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest) {
            System.out.println("++++++++++++++++ WebSocketInterceptor: beforeHandshake  ++++++++++++++" + attributes);
            HttpSession session = ((ServletServerHttpRequest) request).getServletRequest().getSession();
            if (session != null) {
                attributes.put("user_id", session.getAttribute("user_id"));
                System.out.println("WebSocketInterceptor" + session.getAttribute("user_id"));
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        System.out.println("++++++++++++++++ WebSocketInterceptor: afterHandshake  ++++++++++++++");
    }
}
