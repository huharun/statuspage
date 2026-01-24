// Location: src/main/java/com/arun/statuspage/controller/LoginController.java
package com.arun.statuspage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}