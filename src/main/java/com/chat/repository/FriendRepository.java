package com.chat.repository;

import com.chat.model.AppFriend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<AppFriend, Integer> {

}
