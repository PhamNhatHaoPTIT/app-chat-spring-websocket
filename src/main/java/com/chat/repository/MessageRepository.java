package com.chat.repository;

import com.chat.model.AppMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<AppMessage, Integer> {

}
