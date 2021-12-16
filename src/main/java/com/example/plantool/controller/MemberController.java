package com.example.plantool.controller;

import com.example.plantool.model.Skill;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.SkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Author: Kevin Funch
 *
 * Controller for members
 */

@Controller
public class MemberController {
    MemberService memberService = new MemberService();
    SkillService skillService = new SkillService();

    //Endpoint for create member
    @GetMapping("/createmember")
    public String createMemberGetMap(Model model) throws SQLException {

        ArrayList<Skill> skilllist = skillService.fetchAllSkills();
        model.addAttribute("skills", skilllist);
        return "createmember";
    }

    // Postmapping for create member end point
    @PostMapping("/createmember")
    public String createMemberPostMap(WebRequest wr) throws SQLException, InterruptedException {

        String checkForBoolean = wr.getParameter("check");
        memberService.createMember(wr.getParameter("input-name"),
                wr.getParameter("input-email"),
                wr.getParameter("input-password"));
        memberService.isLeaderBoolean(Boolean.parseBoolean(checkForBoolean), wr.getParameter("input-email"));

        String[] skillsAllocated = wr.getParameterValues("skill");

        for(int i = 0; i < skillsAllocated.length; i++){
            skillService.assignSkillToMember(memberService.findMember(wr.getParameter("input-email"))
                    .getMemberId(), skillService.fetchSkillByName(skillsAllocated[i]).getSkillId());
        }
        return "redirect:/createmembersucces";
    }

    //Used for redirection from the postmapping createmember
    @GetMapping("/createmembersucces")
    public String memberSuccess(){
        return "createmembersucces";
    }

    // Endpoint for login
    @GetMapping("/login")
    public String login() throws SQLException {

        return "login";
    }

    //Post mapping for login
    @PostMapping("/login")
    public String login(WebRequest wr, HttpSession session) throws SQLException {

        String email = wr.getParameter("input-email");
        String password = wr.getParameter("input-password");

        boolean validPass = memberService.login(email, password);

        if (validPass) {
            String userId = String.valueOf(memberService.findMember(email).getMemberId());
            int memberLeader = Integer.valueOf(memberService.findMember(email).getIsLeader());
            session.setAttribute("userid", userId);
            session.setAttribute("boolean-leader", memberLeader);

            return "redirect:/frontpage";
        } else {
            return "login";
        }
    }
}
