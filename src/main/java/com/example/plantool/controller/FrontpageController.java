package com.example.plantool.controller;

import com.example.plantool.services.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class FrontpageController {
    SessionService sessionService = new SessionService();


    @GetMapping("/")
    public String index(HttpSession session) throws SQLException {
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        sessionService.logOut(session);
        return "index";
    }

}

