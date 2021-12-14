package com.example.plantool.controller;

import com.example.plantool.model.Project;
import com.example.plantool.model.SubProject;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.SessionService;
import com.example.plantool.services.SubProjectService;
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
public class SubProjectController {
    SubProjectService subProjectService = new SubProjectService();
    SessionService sessionService = new SessionService();


   @GetMapping("/viewsubproject")
    public String subProjectOverview(Model model, HttpSession session) throws SQLException {
       String mappingLeader = sessionService.inSession(model, session, "viewsubprojectleader");
       String mappingMember = sessionService.inSession(model, session, "viewsubprojectmember");
       int isLeader = sessionService.isLeaderSession(session);

       Project currentProject = (Project) session.getAttribute("currentProject");

       int projectId = currentProject.getId();

       model.addAttribute("currentProject", currentProject);

       ArrayList<SubProject> subProjects = subProjectService.fetchAllSubProjectsFromProject(projectId);
       model.addAttribute("subprojects", subProjects);

       if(isLeader == 1) {
           return mappingLeader;

       }else {
            return mappingMember;
        }
    }

    @PostMapping("/viewsubproject")
    public String postSubProject(WebRequest wr, HttpSession session) throws SQLException{
        int leaderId = Integer.parseInt(session.getAttribute("userid").toString());
        int subProjectId = Integer.parseInt(wr.getParameter("subprojectId"));
        session.setAttribute("subprojectId", subProjectId);

        return "redirect:/viewtask";
    }

    @PostMapping("/viewsubproject/create")
    public String createSubProject(WebRequest wr, HttpSession session){

        int projectId = (Integer) session.getAttribute("projectId");

        String name = wr.getParameter("name");
        LocalDate startDate = LocalDate.parse(wr.getParameter("startDate"));
        LocalDate deadline = LocalDate.parse(wr.getParameter("deadline"));
        int hoursAllocated = Integer.parseInt(wr.getParameter("hoursAllocated"));
        String projectDescription = wr.getParameter("description");

        SubProject newProject = new SubProject(name, startDate, deadline, deadline, hoursAllocated, projectDescription);
        subProjectService.addSubProjectToDb(newProject, projectId);
        return "redirect:/viewsubproject";
    }

}

