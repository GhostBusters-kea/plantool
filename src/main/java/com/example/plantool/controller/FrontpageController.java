package com.example.plantool.controller;

import com.example.plantool.services.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Author: Michael Dyvad
 *
 * Controller for frontpage
 */

@Controller
public class FrontpageController {
    SessionService sessionService = new SessionService();

    //View frontpage for leader or member
    @GetMapping("/frontpage")
    public String index(HttpSession session, Model model) throws SQLException {
        String mappingLeader = sessionService.inSession(model, session, "indexleader");
        String mappingMember = sessionService.inSession(model, session, "indexmember");
        int isLeader = sessionService.isLeaderSession(session);
        if(isLeader == 1){
            return mappingLeader;
        }else{
            return mappingMember;
        }
    }

    //General / endpoint for anyone also non members/leaders
    @GetMapping("/")
    public String frontpage(){
        return "frontpage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        sessionService.logOut(session);
        return "indexLeader";
    }
}

