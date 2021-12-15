package com.example.plantool.controller;


import com.example.plantool.model.SubTask;
import com.example.plantool.model.Task;
import com.example.plantool.services.SessionService;
import com.example.plantool.services.SubTaskService;
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

/**
 * Author: Lars Brogaard
 *
 * Controller for subtask
 */

@Controller
public class SubTaskController {
    SubTaskService subTaskService = new SubTaskService();
    SessionService sessionService = new SessionService();
    TaskService taskService = new TaskService();

    //Endpoint to view a subtask
    @GetMapping("/viewsubtask")
    public String viewSubTask (Model model, HttpSession session) throws SQLException {
        String mappingLeader = sessionService.inSession(model, session, "viewsubtaskforleader");
        String mappingMember = sessionService.inSession(model, session, "viewsubtaskformember");
        int isLeader = sessionService.isLeaderSession(session);

        int taskId = (Integer) session.getAttribute("taskId");
        ArrayList<SubTask> subTasks = subTaskService.fetchAllSubTask(taskId);
        model.addAttribute("subtasks", subTasks);

        Task currentTask = (Task) session.getAttribute("currentTask");
        model.addAttribute("currentTask", currentTask);

        if (isLeader ==1){
            return mappingLeader;
        }
        else {
            return mappingMember;
        }
    }

    //Postmapping for viewsubtask
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

    //Postmapping to modify task
    @PostMapping("/viewsubtask/modify")
    public String modifyTask(WebRequest wr, HttpSession session){

        int taskId = ((Task) session.getAttribute("currentTask")).getId();

        if(wr.getParameter("newname") != ""){
            taskService.updateTaskName(taskId, wr.getParameter("newtaskname"));
        }
        if(wr.getParameter("newstartDate") != ""){
            taskService.updateTaskStartDate(taskId, LocalDate.parse(wr.getParameter("newtaskStartDate")));
        }
        if(wr.getParameter("newdeadline") != ""){
            taskService.updateDeadline(taskId, LocalDate.parse(wr.getParameter("newtaskdeadline")));
        }
        if(wr.getParameter("newhoursAllocated") != ""){
            taskService.updateHoursAllocated(taskId, Integer.parseInt(wr.getParameter("newtaskhoursAllocated")));
        }
        if(wr.getParameter("newdescription") != ""){
            taskService.updateTaskDescription(taskId, wr.getParameter("newtaskdescription"));
        }

        return "redirect:/viewproject";
    }

    //Delete task
    @PostMapping("/viewsubtask/delete")
    public String deleteProject(WebRequest wr, HttpSession session) throws SQLException {

        if(session.getAttribute("currentTask")  != null){
            int taskDeleteId = Integer.parseInt(wr.getParameter("deleteTaskId")); //todo id passer ikke
            taskService.deleteTask(taskDeleteId);
            return "redirect:/viewtask";

        }else{
            return "redirect:/viewsubtask";
        }
    }
}