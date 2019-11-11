package com.chat.service;

import com.chat.model.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

public interface UserService {
    AppUser getUserInfo(int user_id);
    List<User> getUserAllFriends(int user_id);
    AppUser getUserByUserNameAndPassword(AppUser user);
    long checkUserNameIfExist(String userName);
    List<Message> getMessageRecord(Message message);
    void saveRegisterUser(AppUser user);
    Pager<User> findUserByUserName(int current_page, int page_size, String userName, int user_id);
    void saveFriendRequest(Message message);
    void processUserRequest(Friend friend);
    List<MessageProcessResult<User>> getUserRequestByUserId(HttpSession session, Locale locale);
    void updateUserStatus(int userId, int status);
}
