package com.example.plantool.controller;

import com.example.plantool.model.Task;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class TaskController {
    MemberService memberService = new MemberService();
    TaskService taskService = new TaskService();

    @GetMapping("/viewtask")
    public String taskView(Model model, HttpSession session){
        int memberLead = (Integer) session.getAttribute("boolean-leader");
        String userId = (String) session.getAttribute("userid");


        ArrayList<Task> taskArray = taskService.fetchAllTasks(1);

        model.addAttribute("tasks", taskArray);

        if (memberLead == 1) {
            return "viewtaskforleader";
        }else
            return "viewtaskformember";

    }

}
