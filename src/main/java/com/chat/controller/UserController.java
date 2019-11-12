package com.chat.controller;

import com.chat.model.*;
import com.chat.service.UserService;
import com.chat.serviceImpl.MyWebSocketHandler;
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
import java.util.Locale;

@Controller
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @RequestMapping(value = "/findUserByUserName", method = RequestMethod.POST)
    public @ResponseBody Pager<User> findUserByUserName(HttpSession session,
                                                        @RequestParam("current_page") Integer current_page,
                                                        @RequestParam("page_size") Integer page_size,
                                                        @RequestParam("userName") String userName) {
        logger.info("search user with username: " + userName);
        int user_id = (int) session.getAttribute("user_id");
        logger.info("current_page = " + current_page + ", page_size = " + page_size);
        Pager<User> userPager = userService.findUserByUserName(current_page, page_size, userName, user_id);
        logger.info("search user with username: " + userPager.toString());
        return userPager;
    }

    @RequestMapping(value = "sendFriendRequest", method = RequestMethod.POST)
    public @ResponseBody Result sendFriendRequest(@RequestBody Message message, Locale locale) {
        String msg = "";
        logger.info("send friend request：" + message.toString());
        message.setMessage_type(Constant.VERIFY_MESSAGE);
        try {
            if (myWebSocketHandler.checkUserIfOnline(message.getTo_user_id())) {
                logger.info("friend online, send request to friend");
                myWebSocketHandler.sendMessageToUser(message.getTo_user_id(), new TextMessage(message.toString()));
                userService.saveFriendRequest(message);
            } else {
                logger.info("friend offline");
                userService.saveFriendRequest(message);
            }
            if(locale.toString().equals("vi")) {
                msg = "Gửi yêu cầu kết bạn thành công";
            } else {
                msg = "Sending successful friend request";
            }
            return ResultUtil.success(0, msg);
        } catch (IOException e) {
            logger.info("LoginController, sendMessage() " + e.getMessage());
            e.printStackTrace();
            return ResultUtil.error(1, e.getMessage());
        }
    }

    @RequestMapping(value = "processUserRequest", method = RequestMethod.POST)
    public @ResponseBody Result<Friend> processUserRequest(@RequestBody Friend friend, Locale locale) {
        userService.processUserRequest(friend);
        String msg = "";
        AppUser user = userService.getUserInfo(friend.getA_id());
        if (friend.getStatus() == Constant.ACCESS) {
            if(locale.toString().equals("vi")) {
                msg = "Bạn đã chấp nhận yêu cầu kết bạn của: <b>" + user.getUserName() + "</b>";
            } else {
                msg = "You have accepted your friend request of: <b>" + user.getUserName() + "</b>";
            }
            friend.setStatus(0);
            return ResultUtil.success(friend, msg);
        } else if (friend.getStatus() == Constant.DENY) {
            if(locale.toString().equals("vi")) {
                msg = "Bạn đã từ chối yêu cầu kết bạn của: <b>" + user.getUserName() + "</b>";
            } else {
                msg = "You have declined your friend request of: <b>" + user.getUserName() + "</b>";
            }
            friend.setStatus(1);
            return ResultUtil.success(friend, msg);
        }
        return null;
    }

    @RequestMapping(value = "getVerificationResult", method = RequestMethod.GET)
    public @ResponseBody List<MessageProcessResult<User>> getVerificationResult(HttpSession session, Locale locale) {
        List<MessageProcessResult<User>> messageProcessResultList = userService.getUserRequestByUserId(session, locale);
        return messageProcessResultList;
    }

    @RequestMapping(value = "getUserFriendList", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getUserFriend(HttpSession session) {
        logger.info("get id of current user");
        int user_id = (int) session.getAttribute("user_id");
        logger.info("get all friend of current user");
        List<User> userList = userService.getUserAllFriends(user_id);
        return userList;
    }
}