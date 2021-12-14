package com.example.plantool.controller;

import com.example.plantool.model.SubProject;
import com.example.plantool.services.SubProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class SubProjectController {
    SubProjectService subProjectService = new SubProjectService();

    @GetMapping("/viewsubproject")
    public String subprojectView(Model model, HttpSession session) {
        int memberLead = (Integer) session.getAttribute("boolean-leader");
        int projectId = (Integer) session.getAttribute("projectId");

        ArrayList<SubProject> subProjectArray = subProjectService.fetchAllSubProjectsFromProject(projectId);

        model.addAttribute("subprojects", subProjectArray);


        if (memberLead == 1) {
            return "viewsubprojectleader";
        } else
            return "viewsubprojectmember";
    }
}