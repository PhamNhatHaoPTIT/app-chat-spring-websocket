package com.chat.controller;

import com.chat.model.Message;
import com.chat.model.Result;
import com.chat.service.MessageService;
import com.chat.serviceImpl.MyWebSocketHandler;
import com.chat.service.UserService;
import com.chat.tools.Constant;
import com.chat.tools.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class MessageController {

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;
    private static final Logger logger = Logger.getLogger(MessageController.class);

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public @ResponseBody Result<Message> sendMessage(@RequestBody Message message) {
        try {
            logger.info(message.getFrom_user_id() + "send message to: " + message.getTo_user_id());
            if (myWebSocketHandler.checkUserIfOnline(message.getTo_user_id())) {
                myWebSocketHandler.sendMessageToUser(message.getTo_user_id(), new TextMessage(message.toString()));
                logger.info("save message in db (receiver online): " + message.toString());
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        messageService.saveChatRecord(message);
                    }
                }, 1000);
            } else {
                logger.info("save message in db (receiver offline): " + message.toString());
                messageService.saveChatRecord(message);
            }
            return ResultUtil.success(message);
        } catch (IOException e) {
            logger.info("LoginController, sendMessage() " + e.getMessage());
            e.printStackTrace();
            return ResultUtil.error(1, e.getMessage());
        }
    }

    @RequestMapping(value = "getMessageRecord", method = RequestMethod.GET)
    public @ResponseBody List<Message> getMessageRecord(HttpSession session, @RequestParam("to_user_id") Integer to_user_id) {
        Integer from_user_id = (Integer) session.getAttribute("user_id");
        Message message = new Message();
        message.setTo_user_id(to_user_id);
        message.setFrom_user_id(from_user_id);
        message.setMessage_type(Constant.NORMAL_MESSAGE);
        List<Message> messageList = userService.getMessageRecord(message);
        return messageList;
    }

}