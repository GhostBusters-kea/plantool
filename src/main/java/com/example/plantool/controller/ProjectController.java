package com.example.plantool.controller;

import com.example.plantool.model.Member;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.ProjectService;
import com.mysql.cj.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class ProjectController {
    MemberService memberService = new MemberService();


    @GetMapping("/createproject")
    public String createProjectGetMap(HttpSession session, Model model) {
        int isLeader = (Integer) session.getAttribute("boolean-leader");

        if(isLeader == 1){

            ArrayList<Member> memberList = memberService.getAllMembers();

            for(int i = 0; i < memberList.size(); i ++){
                System.out.println(memberList.get(i));
            }

            model.addAttribute("members", memberList);

            return "createproject";
        }
        else {
            return "redirect:/index"; // TODO: Create "must be leader" message page
        }

    }

    @PostMapping("/createproject")
    public String createProjectPostMap(){

        return "redirect:/manage-project";
    }
}
