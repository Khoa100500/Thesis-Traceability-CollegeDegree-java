package com.iupv.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);


    User findByRoles_RoleName(String roleName);

    @NonNull
    @Override
    Optional<User> findById(@NonNull Integer integer);
}