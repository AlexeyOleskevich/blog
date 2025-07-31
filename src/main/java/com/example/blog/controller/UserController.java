package com.example.blog.controller;

import com.example.blog.model.User;
import com.example.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "home";
    }

    @GetMapping("/new")
    public String addUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        User user = new User(name, email, password);
        userService.save(user);
        return "redirect:/user/all";
    }


}
