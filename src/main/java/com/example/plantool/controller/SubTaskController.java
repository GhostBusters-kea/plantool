package com.example.plantool.controller;


import com.example.plantool.model.SubTask;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.SubTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class SubTaskController {
    MemberService memberService = new MemberService();
    SubTaskService subTaskService = new SubTaskService();

    @GetMapping("/viewsubtask")
    public String subTaskView(Model model, HttpSession session){
        int memberlead = (Integer) session.getAttribute("boolean-leader");
        String userId = (String) session.getAttribute("userid");

        ArrayList<SubTask> subTaskArrayList = subTaskService.fetchAllSubTask(1);
        model.addAttribute("subtasks", subTaskArrayList);

        if (memberlead == 1){
            return "viewsubtaskforleader";
        }
        else{
            return "viewsubtaskformember";
        }
    }
}