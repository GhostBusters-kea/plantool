package com.example.plantool.controller;

import com.example.plantool.services.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class MemberController {
    MemberService memberService = new MemberService();

    @GetMapping("/createmember")
    public String createMemberGetMap() {
        return "createmember";
    }
    @PostMapping("/createmember")
    public String createMemberPostMap(WebRequest wr) {

        String checkForBoolean = wr.getParameter("check");

        memberService.createMember(wr.getParameter("input-name"),
                wr.getParameter("input-email"),
                wr.getParameter("input-password"));
        memberService.isLeaderBoolean(Boolean.parseBoolean(checkForBoolean), wr.getParameter("input-email"));

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

        boolean validPass = memberService.login(email, password);

        if (validPass) {

            String userId = String.valueOf(memberService.findMember(email).getUserId());
            int memberLeader = Integer.valueOf(memberService.findMember(email).getIsLeader());
            session.setAttribute("userid", userId);
            session.setAttribute("boolean-leader", memberLeader);

            return "redirect:/index";
        } else {
            return "loginpage";
        }
    }

    // Måde at håndtere om man er member eller leader.
    // Vi returnere en html side til projektlederen og en anden html til member. Html siderne er ikke lavet.
    @GetMapping("/index")
    public String index(HttpSession session) {
        int memberLead = (Integer) session.getAttribute("boolean-leader");
        String userId = (String) session.getAttribute("userid");
        if (memberLead == 1 && userId instanceof String) {
            return "index";
        }
        if (memberLead == 0) {
            return "index2";
        }
        return "redirect:/login";
    }
}



