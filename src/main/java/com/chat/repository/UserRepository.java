package com.chat.repository;

import com.chat.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findAppUserById(Integer id);
    AppUser findAppUserByUserNameAndPassword(String username, String password);
    AppUser findAppUserByUserName(String username);
    List<AppUser> findAll();
}
