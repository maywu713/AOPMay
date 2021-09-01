package com.example.aopassignment.dao;

import com.example.aopassignment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    User getUserByUserName(String username);
}
