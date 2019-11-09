package com.chat.controller;

import com.chat.model.*;
import com.chat.service.ToolsService;
import com.chat.service.UserService;
import com.chat.tools.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ToolsService toolsService;

    @RequestMapping(value = "/checkCaptcha", method = RequestMethod.POST)
    public @ResponseBody
    Result checkCaptcha(HttpSession session, @RequestParam("captcha_client") String captcha_client) {
        String captcha_server = (String) session.getAttribute("captcha");
        if (captcha_client != null && captcha_client.equalsIgnoreCase(captcha_server)) {
            return ResultUtil.success(0, "Mã xác minh hợp lệ");
        } else {
            return ResultUtil.error(1, "Mã xác minh không hợp lệ");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody Result login(HttpSession session, @RequestBody AppUser user) {
        AppUser user2 = userService.getUserByUserNameAndPassword(user);
        User temp = new User();
        temp.setId(user2.getId());
        temp.setUserName(user2.getUserName());
        temp.setPassword(user2.getPassword());
        if (user2 != null) {
            session.setAttribute("user_id", user2.getId());
            return ResultUtil.success(temp);
        } else {
            return ResultUtil.error(1, "Sai thông tin đăng nhập");
        }
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String loginModel(HttpSession session) {
        Object user_id = session.getAttribute("user_id");
        if (user_id != null) {
            return "redirect:chat";
        }
        return "login";
    }

    @RequestMapping(value = "loginOut", method = RequestMethod.GET)
    public @ResponseBody Result loginOut(HttpSession session) {
        int user_id = (int) session.getAttribute("user_id");
        session.removeAttribute("user_id");
        return ResultUtil.success(0, "Đăng xuất thành công");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(HttpSession session) {
        Object user_id = session.getAttribute("user_id");
        if (user_id != null) {
            return "redirect:chat";
        }
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody Result saveRegisterUser(@RequestBody AppUser user) {
        userService.saveRegisterUser(user);
        return ResultUtil.success(0, "Đăng ký người dùng thành công");
    }

    @RequestMapping(value = "/checkUserNameIfExist", method = RequestMethod.POST)
    public @ResponseBody Result checkUserNameIfExist(@RequestParam("userName") String userName) {
        long if_user_name_exist = userService.checkUserNameIfExist(userName);
        if (if_user_name_exist == 1) {
            return ResultUtil.error(1, "Username đã tồn tại!");
        } else if (if_user_name_exist == 0) {
            return ResultUtil.error(0, "Username hợp lệ!");
        }
        return null;
    }

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        toolsService.makeCaptcha(request, response);
    }
}
