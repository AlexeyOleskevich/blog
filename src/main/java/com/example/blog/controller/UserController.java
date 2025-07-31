package com.example.blog.controller;

import com.example.blog.model.User;
import com.example.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "user";
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/user/all";
    }


}
