package com.iupv.demo.repositories;

import com.iupv.demo.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserDAO {
    void save(User user);
    User findById(UUID id);
    List<User> findAll();
}
