package com.chat.controller;

import com.chat.model.AppUser;
import com.chat.model.User;
import com.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "chat", method = RequestMethod.GET)
    public String chat(HttpSession session, Model model) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        AppUser user = userService.getUserInfo(user_id);
        List<User> userList = userService.getUserAllFriends(user_id);
        model.addAttribute("user_id", user_id);
        model.addAttribute("user", user);
        model.addAttribute("userList", userList);
        return "chat";
    }
}
