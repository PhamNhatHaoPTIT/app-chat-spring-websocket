package com.chat.serviceImpl;

import com.chat.model.AppMessage;
import com.chat.model.Message;
import com.chat.model.AppUser;
import com.chat.repository.UserRepository;
import com.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void saveChatRecord(Message message) {
        AppUser from = userRepository.findAppUserById(message.getFrom_user_id());
        AppUser to = userRepository.findAppUserById(message.getTo_user_id());
        AppMessage appMessage = new AppMessage();
        appMessage.setFrom(from);
        appMessage.setTo(to);
        appMessage.setContent(message.getContent());
        List<AppMessage> froms = from.getFromMessages();
        froms.add(appMessage);                             // add message to list from message
        userRepository.save(from);
    }
}
