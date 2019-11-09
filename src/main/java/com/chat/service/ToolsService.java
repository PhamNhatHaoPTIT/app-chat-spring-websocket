package com.chat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ToolsService {
    void makeCaptcha(HttpServletRequest request, HttpServletResponse response);
}
