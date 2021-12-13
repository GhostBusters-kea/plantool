package com.example.plantool.controller;

import com.example.plantool.model.Task;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class TaskController {
    MemberService memberService = new MemberService();
    TaskService taskService = new TaskService();

    @GetMapping("/viewtask")
    public String taskView(Model model, HttpSession session, WebRequest wr){
        int memberLead = (Integer) session.getAttribute("boolean-leader");
        String userId = (String) session.getAttribute("userid");

        //int subprojectId = Integer.parseInt(wr.getParameter("subproject-id"));
        int subprojectId = (Integer) model.getAttribute("subproject-id");

        ArrayList<Task> taskArray = taskService.fetchAllTasks(subprojectId);

        model.addAttribute("tasks", taskArray);

        if (memberLead == 1) {
            return "viewtaskforleader";
        }else
            return "viewtaskformember";

    }

    @GetMapping("/createtask")
    public String createTaskGet(HttpSession session){
        return "createtask";
    }

    @PostMapping("/createtask")
    public String createTaskPost(HttpSession session, WebRequest wr){
        String name = wr.getParameter("taskName");
        LocalDate startDate = LocalDate.parse(wr.getParameter("taskStartDate"));
        LocalDate deadline = LocalDate.parse(wr.getParameter("taskDeadline"));
        int hoursAllocated = Integer.parseInt(wr.getParameter("taskHoursAllocated"));
        String projectDescription = wr.getParameter("taskDescription");

        Task task = new Task(name, startDate, deadline, deadline, hoursAllocated, projectDescription);
        taskService.addTaskToDb(task, 1); //TODO subproject skal fixes


        return "redirect:/viewtask";
    }



}
