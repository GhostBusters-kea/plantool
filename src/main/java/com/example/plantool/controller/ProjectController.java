package com.example.plantool.controller;

import com.example.plantool.model.Member;
import com.example.plantool.model.Project;
import com.example.plantool.model.Skill;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.ProjectService;
import com.example.plantool.services.SkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Controller
public class ProjectController {
    MemberService memberService = new MemberService();
    ProjectService projectService = new ProjectService();
    SkillService skillService = new SkillService();


    @GetMapping("/createproject")
    public String createProjectGet(HttpSession session, Model model) throws SQLException {
        String mapping = memberService.inSession(model, session, "createproject");
        int isLeader = memberService.isLeaderSession(session);

        if(isLeader == 1){

            ArrayList<Skill> skillList = skillService.fetchAllSkills();
            model.addAttribute("skills", skillList);

            ArrayList<Member> memberList = memberService.getAllMembers();
            model.addAttribute("members", memberList);

            return mapping;
        }
        else {
            return mapping; // TODO: Create "must be leader" message page
        }

    }

    @PostMapping("/createproject")
    public String createProjectPost(HttpSession session, WebRequest wr) throws SQLException, InterruptedException {
        int leaderId = Integer.parseInt(session.getAttribute("userid").toString());
        String name = wr.getParameter("name");
        LocalDate startDate = LocalDate.parse(wr.getParameter("startDate"));
        LocalDate deadline = LocalDate.parse(wr.getParameter("deadline"));
        int hoursAllocated = Integer.parseInt(wr.getParameter("hoursAllocated"));
        String projectDescription = wr.getParameter("description");

        Project newProject = new Project(name, startDate, deadline, deadline, leaderId, hoursAllocated, projectDescription);
        projectService.addProjectToDb(newProject);
        newProject.setId(projectService.fetchSingelProjectId(newProject.getName()));


        String[] skillsAllocated = wr.getParameterValues("skill");


        for(int i = 0; i < skillsAllocated.length; i++){
            skillService.assignSkillToProject(newProject.getId(), skillService.fetchSkillByName(skillsAllocated[i]).getSkillId());
        }

        String[] assignees = wr.getParameterValues("member");

        for(int i = 0; i < assignees.length; i++){
            projectService.assignMemberToProject(newProject.getId(), memberService.memberByName(assignees[i]).getMemberId());
        }

        return "redirect:/viewproject";
    }

    @GetMapping("/viewproject")
    public String projectOverview(Model model, HttpSession session) throws SQLException {
        String mapping = memberService.inSession(model, session, "viewproject");

        ArrayList<Project> projects = projectService.fetchAllProjects();

        for(int i = 0; i < projects.size(); i++){

            projects.get(i).setSkillsAllocated(skillService.skillsInProject(projects.get(i).getId()));
            projects.get(i).setTotalDays(projectService.countBusinessDays(projects.get(i)));
            projects.get(i).setDaysUntilDeadline(projectService.daysUntilDeadline(projects.get(i)));
            projects.get(i).setHoursADay(projectService.calculateHoursPrDay(projects.get(i), projects.get(i).getAssignees().size()));
        }


        model.addAttribute("projects", projects);

        return mapping;
    }

    @PostMapping("/viewproject")
    public String postSubProject(WebRequest wr, HttpSession session) throws SQLException{
        int leaderId = Integer.parseInt(session.getAttribute("userid").toString());
        int projectId = Integer.parseInt(wr.getParameter("projectId"));
        session.setAttribute("projectId", projectId);
        return "redirect:/viewsubproject";
    }
}
