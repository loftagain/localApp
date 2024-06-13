package com.company.localApp.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    List<User> findAll();
}
