package com.iupv.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User doSomething() {
        User role = userRepository.findByRoles_RoleName("USER");
        User temp = User.builder()

                .userFullname("Tran Thanh Tung")
                .email("test@gmailcom")
                .password("123")
                .username("user2").build();
        return userRepository.save(temp);
    }

}
