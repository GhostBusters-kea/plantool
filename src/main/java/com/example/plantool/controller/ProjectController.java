package com.example.plantool.controller;

import com.example.plantool.model.Member;
import com.example.plantool.model.Project;
import com.example.plantool.model.Skill;
import com.example.plantool.services.MemberService;
import com.example.plantool.services.ProjectService;
import com.example.plantool.services.SessionService;
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


@Controller
public class ProjectController {
    MemberService memberService = new MemberService();
    ProjectService projectService = new ProjectService();
    SkillService skillService = new SkillService();
    SessionService sessionService = new SessionService();


    @GetMapping("/createproject")
    public String createProjectGet(HttpSession session, Model model) throws SQLException {
        String mapping = sessionService.inSession(model, session, "createproject");
        int isLeader = sessionService.isLeaderSession(session);

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


        for (String s : skillsAllocated) {
            skillService.assignSkillToProject(newProject.getId(), skillService.fetchSkillByName(s).getSkillId());
        }

        String[] assignees = wr.getParameterValues("member");

        for (String assignee : assignees) {
            projectService.assignMemberToProject(newProject.getId(), memberService.memberByName(assignee).getMemberId());
        }

        return "redirect:/viewproject";
    }

    @GetMapping("/viewproject")
    public String projectOverview(Model model, HttpSession session) throws SQLException {
        String mapping = sessionService.inSession(model, session, "viewproject");


        ArrayList<Project> projects = projectService.fetchAllProjects();

        for (Project project : projects) {

            project.setSkillsAllocated(skillService.skillsInProject(project.getId()));
            project.setTotalDays(projectService.countBusinessDays(project));
            project.setDaysUntilDeadline(projectService.daysUntilDeadline(project));
            project.setHoursADay(projectService.calculateHoursPrDay(project, project.getAssignees().size()));
        }

        model.addAttribute("projects", projects);

        return mapping;
    }

    @PostMapping("/viewproject")
    public String postSubProject(WebRequest wr, HttpSession session) throws SQLException{
        int leaderId = Integer.parseInt(session.getAttribute("userid").toString());
        int projectId = Integer.parseInt(wr.getParameter("projectId"));

        Project project = projectService.fetchSingleProject(projectId);

        session.setAttribute("project", project);

        return "redirect:/viewsubproject";
    }
}
