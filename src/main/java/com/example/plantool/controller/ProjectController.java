package com.example.plantool.controller;

import com.example.plantool.model.Member;
import com.example.plantool.model.Project;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.ProjectService;
import com.mysql.cj.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class ProjectController {
    MemberService memberService = new MemberService();
    ProjectService projectService = new ProjectService();


    @GetMapping("/createproject")
    public String createProjectGet(HttpSession session, Model model) throws SQLException {
        memberService.navbarName(model, session);
        int isLeader = (Integer) session.getAttribute("boolean-leader");

        if(isLeader == 1){

            ArrayList<Member> memberList = memberService.getAllMembers();
            model.addAttribute("members", memberList);


            return "createproject";
        }
        else {
            return "redirect:/"; // TODO: Create "must be leader" message page
        }

    }

    @PostMapping("/createproject")
    public String createProjectPost(HttpSession session, WebRequest wr) throws SQLException {
        int leaderId = Integer.parseInt(session.getAttribute("userid").toString());

        String name = wr.getParameter("name");
        LocalDate startDate = LocalDate.parse(wr.getParameter("startDate"));
        LocalDate deadline = LocalDate.parse(wr.getParameter("startDate"));

        int hoursAllocated = Integer.parseInt(wr.getParameter("hoursAllocated"));
        String projectDescription = wr.getParameter("description");

        Project newProject = new Project(name, startDate, deadline, deadline, leaderId, hoursAllocated, projectDescription);
        //String[] skillsAllocated = wr.getParameterValues("skills");
        String[] assignees = wr.getParameterValues("members");

//        for(int i = 0; i < skillsAllocated.length; i++){
//            newProject.getSkillsAllocated().add(skillsAllocated[i]);
//        }

        ArrayList<Member> tempAss = new ArrayList<>();
        for(int i = 0; i < assignees.length; i++){
            tempAss.add(memberService.memberByName(assignees[i]));
        }
        newProject.setAssignees(tempAss);

        projectService.addProjectToDb(newProject);

        return "redirect:/viewproject";
    }

    @GetMapping("/viewproject")
    public String projectOverview(Model model, HttpSession session) throws SQLException {
        memberService.navbarName(model, session);

        ArrayList<Project> projects = projectService.fetchAllProjects();
        ArrayList<Member> members = memberService.getAllMembers();
        model.addAttribute("projects", projects);
        model.addAttribute("members", members);

        return "viewproject";
    }
}
