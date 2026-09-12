package com.example.LoginPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "Login";
    }
    @GetMapping("/register")
    public String register() {
        return "Register";
    }
    @GetMapping("/recoverpassword")
    public String recoverpassword() {
        return "Recover Password";
    }
}
