package com.example.plantool.controller;

import com.example.plantool.model.Project;
import com.example.plantool.model.SubProject;
import com.example.plantool.model.Task;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.SessionService;
import com.example.plantool.services.SubProjectService;
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
 * Author: Michael Dyvad
 *
 * Skill model class
 */

@Controller
public class TaskController {
    MemberService memberService = new MemberService();
    TaskService taskService = new TaskService();
    SessionService sessionService = new SessionService();
    SubProjectService subProjectService = new SubProjectService();

    //Endpoint to view a task
    @GetMapping("/viewtask")
    public String viewTask (Model model, HttpSession session) throws SQLException{
        String mappingLeader = sessionService.inSession(model, session, "viewtaskforleader");
        String mappingMember = sessionService.inSession(model, session, "viewtaskformember");
        int isLeader = sessionService.isLeaderSession(session);

        int subProjectId = (Integer) session.getAttribute("subprojectId");
        ArrayList<Task> tasks = taskService.fetchAllTasks(subProjectId);
        model.addAttribute("tasks", tasks);


        SubProject currentSubproject = (SubProject) session.getAttribute("currentSubproject");
        model.addAttribute("currentSubproject", currentSubproject);
        session.setAttribute("tasks", tasks);

        if (isLeader ==1){
            return mappingLeader;
        }
        else {
            return mappingMember;
        }
    }

    //Post mapping for view task
    @PostMapping("/viewtask")
    public String postTask(WebRequest wr, HttpSession session) throws SQLException{
        int leaderId = Integer.parseInt(session.getAttribute("userid").toString());
        int taskId = Integer.parseInt(wr.getParameter("taskId"));
        session.setAttribute("taskId", taskId);

        ArrayList<Task> tasks = (ArrayList<Task>) session.getAttribute("tasks");
        Task currentTask = new Task();

        for(Task task : tasks){
            if(task.getId() == taskId){
                currentTask = task;
            }
        }
        session.setAttribute("currentTask", currentTask);

        return "redirect:/viewsubtask";
    }


    //view endpoint for create task
    @GetMapping("/createtask")
    public String createTaskGet(HttpSession session){
        return "createtask";
    }

    // Postmapping  for create task
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

    //Postmapping for modify subproject
    @PostMapping("/viewtask/modify")
    public String modifySubproject(WebRequest wr, HttpSession session){

        int subProjectId = ((SubProject) session.getAttribute("currentSubproject")).getId();

        if(wr.getParameter("newname") != ""){
            subProjectService.updatesubProjectName(subProjectId, wr.getParameter("newsubprojectname"));
        }
        if(wr.getParameter("newstartDate") != ""){
            subProjectService.updateProjectStartDate(subProjectId, LocalDate.parse(wr.getParameter("newsubprojectStartDate")));
        }
        if(wr.getParameter("newdeadline") != ""){
            subProjectService.updateSubDeadline(subProjectId, LocalDate.parse(wr.getParameter("newsubprojectdeadline")));
        }
        if(wr.getParameter("newhoursAllocated") != ""){
            subProjectService.updateHoursAllocated(subProjectId, Integer.parseInt(wr.getParameter("newsubprojecthoursAllocated")));
        }
        if(wr.getParameter("newdescription") != ""){
            subProjectService.updatesubProjectDescription(subProjectId, wr.getParameter("newsubprojectdescription"));
        }

        return "redirect:/viewproject";
    }

    //Deletes a subproject
    @PostMapping("/viewtask/delete")
    public String deleteSubproject(WebRequest wr, HttpSession session) throws SQLException {

        if(session.getAttribute("currentSubproject")  != null){
            int subprojectDeleteId = Integer.parseInt(wr.getParameter("deleteSubprojectId")); //todo id passer ikke
            subProjectService.deletesubProject(subprojectDeleteId);
            return "redirect:/viewsubproject";

        }else{
            return "redirect:/viewtask";
        }
    }
}

