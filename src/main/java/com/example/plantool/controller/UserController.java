package com.example.plantool.controller;

import com.example.plantool.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class UserController {
    UserService userService = new UserService();

    @GetMapping("/createuser")
    public String createUserGetMap() {
        return "createuser";
    }

    @PostMapping("/createuser")
    public String createUserPostMap(WebRequest wr) {

        String checkForBoolean = wr.getParameter("check");

        userService.createUser(wr.getParameter("input-name"),
                wr.getParameter("input-email"),
                wr.getParameter("input-password"));
        userService.isLeaderBoolean(Boolean.parseBoolean(checkForBoolean), wr.getParameter("input-email"));

        return "redirect:/index";
    }

    @GetMapping("/login")
    public String login() {
        return "loginpage";
    }

    @PostMapping("/login")
    public String login(WebRequest wr, HttpSession session) throws SQLException {

        String email = wr.getParameter("input-email");
        String password = wr.getParameter("input-password");

        boolean validPass = userService.login(email, password);

        if (validPass) {

            String userId = String.valueOf(userService.fetchUser(email).getUserId());
            int userLeader = Integer.valueOf(userService.fetchUser(email).getIsLeader());
            session.setAttribute("userid", userId);
            session.setAttribute("boolean-leader", userLeader);

            return "redirect:/index";
        } else {
            return "loginpage";
        }
    }

    // Måde at håndtere om man er member eller leader.
    // Vi returnere en html side til projektlederen og en anden html til member. Html siderne er ikke lavet.
    @GetMapping("/forside")
    public String index(HttpSession session) {
        int userLead = (Integer) session.getAttribute("boolean-leader");
        String userId = (String) session.getAttribute("userid");
        if (userLead == 1 && userId instanceof String) {
            return "project-leader";
        }
        if (userLead == 0) {
            return "project-member";
        }
        return "redirect:/login";
    }
}



