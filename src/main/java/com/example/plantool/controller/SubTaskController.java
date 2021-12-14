package com.example.plantool.controller;


import com.example.plantool.model.SubTask;
import com.example.plantool.model.Task;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.SubTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class SubTaskController {
    MemberService memberService = new MemberService();
    SubTaskService subTaskService = new SubTaskService();

    @GetMapping("/viewsubtask")
    public String viewSubTask (Model model, HttpSession session) throws SQLException {
        int taskId = (Integer) session.getAttribute("taskId");
        int memberLead = (Integer) session.getAttribute("boolean-leader");

        if (memberLead ==1){
            String mapping = memberService.inSession(model, session, "viewsubtaskforleader");
            ArrayList<SubTask> subTasks = subTaskService.fetchAllSubTask(taskId);
            model.addAttribute("subtasks", subTasks);
            return mapping;
        }
        else {
            String mapping = memberService.inSession(model, session, "viewsubtaskformember");
            ArrayList<SubTask> subtasks = subTaskService.fetchAllSubTask(taskId);
            model.addAttribute("subtasks", subtasks);
            return mapping;
        }
    }
}