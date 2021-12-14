package com.example.plantool.controller;

import com.example.plantool.model.Task;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.SessionService;
import com.example.plantool.services.TaskService;
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
public class TaskController {
    MemberService memberService = new MemberService();
    TaskService taskService = new TaskService();
    SessionService sessionService = new SessionService();

    @GetMapping("/viewtask")
    public String viewTask (Model model, HttpSession session) throws SQLException{
        int subProjectId = (Integer) session.getAttribute("subprojectId");
        int memberLead = (Integer) session.getAttribute("boolean-leader");

        if (memberLead ==1){
            String mapping = sessionService.inSession(model, session, "viewtaskforleader");
            ArrayList<Task> tasks = taskService.fetchAllTasks(subProjectId);
            model.addAttribute("tasks", tasks);
            return mapping;
        }
        else {
            String mapping = sessionService.inSession(model, session, "viewtaskformember");
            ArrayList<Task> tasks = taskService.fetchAllTasks(subProjectId);
            model.addAttribute("tasks", tasks);
            return mapping;
        }
    }
    @PostMapping("/viewtask")
    public String postTask(WebRequest wr, HttpSession session) throws SQLException{
        int leaderId = Integer.parseInt(session.getAttribute("userid").toString());
        int taskId = Integer.parseInt(wr.getParameter("taskId"));
        session.setAttribute("taskId", taskId);
        return "redirect:/viewsubtask";
    }


    @GetMapping("/createtask")
    public String createTaskGet(HttpSession session){
        return "createtask";
    }


    @PostMapping("/viewtask/create")
    public String createTaskPost(HttpSession session, WebRequest wr){
        int subprojectId = (Integer) session.getAttribute("subprojectId");
        String name = wr.getParameter("taskName");
        LocalDate startDate = LocalDate.parse(wr.getParameter("taskStartDate"));
        LocalDate deadline = LocalDate.parse(wr.getParameter("taskDeadline"));
        int hoursAllocated = Integer.parseInt(wr.getParameter("taskHoursAllocated"));
        String projectDescription = wr.getParameter("taskDescription");

        Task task = new Task(name, startDate, deadline, deadline, hoursAllocated, projectDescription);
        taskService.addTaskToDb(task, subprojectId);

        return "redirect:/viewtask";
    }



}

