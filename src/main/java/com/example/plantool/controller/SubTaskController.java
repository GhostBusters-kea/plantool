package com.example.plantool.controller;


import com.example.plantool.model.SubTask;
import com.example.plantool.model.Task;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.SessionService;
import com.example.plantool.services.SubTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class SubTaskController {
    MemberService memberService = new MemberService();
    SubTaskService subTaskService = new SubTaskService();
    SessionService sessionService = new SessionService();

    @GetMapping("/viewsubtask")
    public String viewSubTask (Model model, HttpSession session) throws SQLException {
        int taskId = (Integer) session.getAttribute("taskId");
        int memberLead = (Integer) session.getAttribute("boolean-leader");

        if (memberLead ==1){
            String mapping = sessionService.inSession(model, session, "viewsubtaskforleader");
            ArrayList<SubTask> subTasks = subTaskService.fetchAllSubTask(taskId);
            model.addAttribute("subtasks", subTasks);
            return mapping;
        }
        else {
            String mapping = sessionService.inSession(model, session, "viewsubtaskformember");
            ArrayList<SubTask> subtasks = subTaskService.fetchAllSubTask(taskId);
            model.addAttribute("subtasks", subtasks);
            return mapping;
        }
    }

    @PostMapping("/viewsubtask")
    public String createTaskPost(HttpSession session, WebRequest wr){
        int taskId = (Integer) session.getAttribute("taskId");
        String name = wr.getParameter("subtaskName");
        LocalDate startDate = LocalDate.parse(wr.getParameter("subtaskStartDate"));
        LocalDate deadline = LocalDate.parse(wr.getParameter("subtaskDeadline"));
        int hoursAllocated = Integer.parseInt(wr.getParameter("subtaskHoursAllocated"));
        String projectDescription = wr.getParameter("subtaskDescription");

        SubTask subTask = new SubTask(name, startDate, deadline, deadline, hoursAllocated, projectDescription);
        subTaskService.addSubTaskToDB(subTask, taskId);

        return "redirect:/viewsubtask";
    }
}