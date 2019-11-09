package com.chat.serviceImpl;

import com.chat.service.ToolsService;
import com.chat.tools.Constant;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class ToolsServiceImpl implements ToolsService {

    private static final Logger logger = Logger.getLogger(ToolsServiceImpl.class);

    @Override
    public void makeCaptcha(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        logger.info("get captcha started!");
        final int width = 180;
        final int height = 40;
        final String imgType = "jpeg";
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            logger.info("get captcha done！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String code = Constant.createCaptcha(width, height, imgType, output);
        session.setAttribute("captcha", code);
    }
}
