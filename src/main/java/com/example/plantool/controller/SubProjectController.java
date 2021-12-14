package com.example.plantool.controller;

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
import java.util.ArrayList;

@Controller
public class SubProjectController {
    SubProjectService subProjectService = new SubProjectService();
    SessionService sessionService = new SessionService();


   @GetMapping("/viewsubproject")
    public String subProjectOverview(Model model, HttpSession session) throws SQLException {

       int projectId = (Integer) session.getAttribute("projectId");
       int memberLead = (Integer) session.getAttribute("boolean-leader");

        if(memberLead == 1){
            String mapping = sessionService.inSession(model, session, "viewsubprojectleader");
            ArrayList<SubProject> subProjects = subProjectService.fetchAllSubProjectsFromProject(projectId);
            model.addAttribute("subprojects", subProjects);
            return mapping;
        }

        else {
            String mapping = sessionService.inSession(model, session, "viewsubprojectmember");
            ArrayList<SubProject> subProjects = subProjectService.fetchAllSubProjectsFromProject(projectId);
            model.addAttribute("subprojects", subProjects);
            return mapping;
        }

    }
    @PostMapping("/viewsubproject")
    public String postSubProject(WebRequest wr, HttpSession session) throws SQLException{
        int leaderId = Integer.parseInt(session.getAttribute("userid").toString());
        int subProjectId = Integer.parseInt(wr.getParameter("subprojectId"));
        session.setAttribute("subprojectId", subProjectId);
        return "redirect:/viewtask";
    }

}

