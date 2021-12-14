package com.example.plantool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class FrontpageController {

    
    @GetMapping("/index")
    public String index(HttpSession session) throws SQLException {

        int memberLead = (Integer) session.getAttribute("boolean-leader");
        String userId = (String) session.getAttribute("userid");
        if (memberLead == 1 && userId instanceof String) {
            return "index";
        }
        if (memberLead == 0) {
            return "index";
        }
        return "redirect:/login";
    }


}

