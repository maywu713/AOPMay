package com.example.aopassignment.controller;

import com.example.aopassignment.dao.UserDAO;
import com.example.aopassignment.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserDAO userDAO;

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = userDAO.findAll();
        return users;
    }

    @RequestMapping("/getuser/{username}")
    public User getOneUser(@PathVariable String username) {
        User user = userDAO.getUserByUserName(username);
        return user;
    }

    @GetMapping("/exception")
    public void execeptionTest() {
        int a = 1 / 0;
    }

    @ExceptionHandler({ArithmeticException.class,ArithmeticException.class})
    public String arithmeticExceptionError() {
        return "ArithmeticException Catched";
    }

}
