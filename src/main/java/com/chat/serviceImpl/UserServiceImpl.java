package com.chat.serviceImpl;

import com.chat.model.*;
import com.chat.repository.UserRepository;
import com.chat.service.UserService;
import com.chat.tools.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public AppUser getUserInfo(int user_id) {
        AppUser user = userRepository.findAppUserById(user_id);
        return user;
    }

    @Override
    public List<User> getUserAllFriends(int user_id) {
        AppUser user = getUserInfo(user_id);
        List<User> userList = new ArrayList<>();
        List<AppFriend> friends = user.getFriends();
        User temp;
        for(AppFriend x : friends) {
            if(x.getStatus() == 0) {
                temp = new User();
                temp.setUserName(x.getB().getUserName());
                temp.setId(x.getB().getId());
                temp.setStatus(x.getB().getStatus());
                userList.add(temp);
            }
        }
        List<AppFriend> self = user.getSelf();
        for(AppFriend x : self) {
            if(x.getStatus() == 0) {
                temp = new User();
                temp.setUserName(x.getA().getUserName());
                temp.setId(x.getA().getId());
                temp.setStatus(x.getA().getStatus());
                userList.add(temp);
            }
        }
        return userList;
    }

    @Override
    public AppUser getUserByUserNameAndPassword(AppUser user) {
        return userRepository.findAppUserByUserNameAndPassword(user.getUserName(), user.getPassword());
    }

    @Override
    public long checkUserNameIfExist(String userName) {
        AppUser user = userRepository.findAppUserByUserName(userName);
        if(user != null) return 1;
        return 0;
    }

    @Override
    public List<Message> getMessageRecord(Message message) {
        List<Message> messageList = new ArrayList<>();
        AppUser from = userRepository.findAppUserById(message.getFrom_user_id());
        Message temp;
        for(AppMessage x : from.getFromMessages()) {
            temp = new Message();
            temp.setContent(x.getContent());
            temp.setFrom_user_id(x.getFrom().getId());
            temp.setTo_user_id(x.getTo().getId());
            temp.setSend_time(x.getSend_time());
            temp.setId(x.getId());
            if( x.getTo().getId() == message.getTo_user_id() &&
                x.getFrom().getId() == message.getFrom_user_id() ) {
                messageList.add(temp);
            } else if( x.getFrom().getId() == message.getTo_user_id() &&
                       x.getTo().getId() == message.getFrom_user_id() ) {
                messageList.add(temp);
            }
        }
        for(AppMessage x : from.getToMessages()) {
            temp = new Message();
            temp.setContent(x.getContent());
            temp.setFrom_user_id(x.getFrom().getId());
            temp.setTo_user_id(x.getTo().getId());
            temp.setSend_time(x.getSend_time());
            temp.setId(x.getId());
            if( x.getFrom().getId() == message.getTo_user_id() ) {
                messageList.add(temp);
            }
        }
        Collections.sort(messageList);
        return messageList;
    }

    @Override
    public void saveRegisterUser(AppUser user) {
        userRepository.save(user);
    }

    @Override
    public Pager<User> findUserByUserName(int current_page, int page_size, String userName, int user_id) {
        int __current_page = current_page;
        if (current_page < 1) {
            __current_page = 1;
        }
        AppUser userRequest = userRepository.findAppUserById(user_id);
        List<AppUser> allUser = userRepository.findAll();
        allUser.remove(userRepository.findAppUserById(user_id));
        for(AppFriend x : userRequest.getFriends()) {
            allUser.remove(userRepository.findAppUserById(x.getB().getId()));
        }
        for(AppFriend x : userRequest.getSelf()) {
            allUser.remove(userRepository.findAppUserById(x.getA().getId()));
        }
        List<User> matchUser = new ArrayList<>();
        User temp;
        for(AppUser x : allUser) {
            temp = new User();
            if(x.getUserName().contains(userName)) {
                temp.setId(x.getId());
                temp.setUserName(x.getUserName());
                matchUser.add(temp);
            }
        }

        int start_page = page_size * (__current_page - 1);
        int total_record = matchUser.size();
        int total_page = total_record / page_size + 1;
        if (total_record % page_size == 0 && total_record != 0) {
            total_page = total_record / page_size;
        }

        if (total_record == 0) {
            total_page = 1;
        }

        if (current_page > total_page) {
            __current_page = total_page;
        }
        List<User> userList = new ArrayList<>();
        for(int i = start_page; i < start_page + 10; i++) {
            try {
                userList.add(matchUser.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Pager<User> userPager = new Pager<>(__current_page, total_record, total_page, page_size, userList);
        return userPager;
    }

    @Override
    public void saveFriendRequest(Message message) {
        AppUser a = userRepository.findAppUserById(message.getFrom_user_id());
        AppUser b = userRepository.findAppUserById(message.getTo_user_id());
        AppFriend appFriend = new AppFriend();
        appFriend.setA(a);
        appFriend.setB(b);
        appFriend.setStatus(2);
        List<AppFriend> aList = a.getFriends();
        aList.add(appFriend);
        userRepository.save(a);
    }

    @Override
    public void processUserRequest(Friend friend) {
        AppUser a = userRepository.findAppUserById(friend.getA_id());
        List<AppFriend> friends = a.getFriends();
        for(AppFriend x : friends) {
            if(x.getId() == friend.getId()) {
                x.setStatus(friend.getStatus());
            }
        }
        userRepository.save(a);
    }

    @Override
    public List<MessageProcessResult<User>> getUserRequestByUserId(HttpSession session, Locale locale) {
        int user_id = (int) session.getAttribute("user_id");
        logger.info("friend request from: " + user_id);
        AppUser user = userRepository.findAppUserById(user_id);
        List<Friend> friendList = new ArrayList<>();
        Friend temp;
        for(AppFriend x : user.getFriends()) {
            temp = new Friend();
            temp.setA_id(x.getA().getId());
            temp.setB_id(x.getB().getId());
            temp.setStatus(x.getStatus());
            friendList.add(temp);
        }
        for(AppFriend x : user.getSelf()) {
            temp = new Friend();
            temp.setA_id(x.getA().getId());
            temp.setB_id(x.getB().getId());
            temp.setStatus(x.getStatus());
            friendList.add(temp);
        }
        String processResult = "";
        List<MessageProcessResult<User>> messageProcessResultList = new ArrayList<>();
        MessageProcessResult<User> messageProcessResult;
        User fromUser;
        User toUser;
        AppUser fromTemp;
        AppUser toTemp;
        for (Friend friend : friendList) {
            fromTemp = userRepository.findAppUserById(friend.getA_id());
            toTemp = userRepository.findAppUserById(friend.getB_id());
            fromUser = new User();
            fromUser.setId(fromTemp.getId());
            fromUser.setUserName(fromTemp.getUserName());
            toUser = new User();
            toUser.setId(toTemp.getId());
            toUser.setUserName(toTemp.getUserName());
            logger.info("fromUser:" + fromUser.toString() + ", getA_id() = " + friend.getA_id()
                        + ", toUser" + toUser.toString() + ", getB_id() = " + friend.getB_id());
            if (friend.getA_id() == user_id) {
                if (friend.getStatus() == Constant.ACCESS) {
                    if(locale.toString().equals("vi")) {
                        processResult = "<b>" + toUser.getUserName() + "</b> đã chấp nhận yêu cầu kết bạn";
                    } else {
                        processResult = "<b>" + toUser.getUserName() + "</b> accepted the friend request";
                    }
                } else if (friend.getStatus() == Constant.DENY) {
                    if(locale.toString().equals("vi")) {
                        processResult = "<b>" + toUser.getUserName() + "</b> đã từ chối yêu cầu kết bạn";
                    } else {
                        processResult = "<b>" + toUser.getUserName() + "</b> declined the friend request";
                    }
                } else if (friend.getStatus() == Constant.UNPROCESSED) {
                    if(locale.toString().equals("vi")) {
                        processResult = "Bạn đã yêu cầu thêm <b>" + toUser.getUserName() + "</b> làm bạn bè";
                    } else {
                        processResult = "You have requested <b>" + toUser.getUserName() + "</b> be friend";
                    }
                }
                logger.info("processResult: " + processResult);
            } else if (friend.getA_id() != user_id) {
                if (friend.getStatus() == Constant.ACCESS) {
                    if(locale.toString().equals("vi")) {
                        processResult = "Bạn đã chấp nhận <b>" + fromUser.getUserName() + "</b> làm bạn bè";
                    } else {
                        processResult = "You have accepted <b>" + fromUser.getUserName() + "</b>";
                    }
                } else if (friend.getStatus() == Constant.DENY) {
                    if(locale.toString().equals("vi")) {
                        processResult = "Bạn đã từ chối <b>" + fromUser.getUserName() + "</b>";
                    } else {
                        processResult = "You have declined <b>" + fromUser.getUserName() + "</b>";
                    }
                } else if (friend.getStatus() == Constant.UNPROCESSED) {
                    if(locale.toString().equals("vi")) {
                        processResult = "<b>" + fromUser.getUserName() + "</b> yêu cầu thêm bạn làm bạn";
                    } else {
                        processResult = "<b>" + fromUser.getUserName() + "</b> ask to be your friend";
                    }
                }
                logger.info("processResult: " + processResult);
            }
            messageProcessResult = new MessageProcessResult<>(fromUser, toUser, friend.getStatus(), processResult);
            messageProcessResultList.add(messageProcessResult);
            logger.info("messageProcessResult" + messageProcessResult.toString() + ", "
                        + messageProcessResultList.toString());
        }
        return messageProcessResultList;
    }

    @Override
    public void updateUserStatus(int userId, int status) {
        AppUser user = userRepository.findAppUserById(userId);
        user.setStatus(status);
        userRepository.save(user);
    }

}