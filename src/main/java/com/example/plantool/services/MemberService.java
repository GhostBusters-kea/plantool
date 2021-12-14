package com.example.plantool.services;

import com.example.plantool.model.Member;
import com.example.plantool.repository.MemberRepo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberService {
    MemberRepo memberRepo = new MemberRepo();

    // new instance of user
    public void createMember(String name, String email, String password){
        Member member = new Member(name, email, password);

        if (emailValidation(email) == false){
            System.out.println("Email not valid");
        }

        else if (doesEmailExist(email) == false){
            memberRepo.insertMemberToDB(member);
        }
        else {
            System.out.println("Emailen already exists");
        }

    }


    // checks if the email is already registered
    public boolean doesEmailExist(String email){

        for(int i = 0; i < memberRepo.findAllMembers().size(); i++){
            if(memberRepo.findAllMembers().get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    // email validation
    public boolean emailValidation (String email){
        if(email.contains(".") && email.contains("@")){
            return true;
        } else
            return false;
    }

    // checks password
    public boolean passwordValidation (Member member, String userInput){
        boolean passIsValid = false;

        if(userInput.equals(member.getPassword())){
            passIsValid = true;
        }

        return passIsValid;
    }

    // User login
    public boolean login(String email, String password) throws SQLException {
        boolean validLogin = false;

        if(doesEmailExist(email)){
            if(passwordValidation(memberRepo.findMember(email), password)){
                validLogin = true;
            }
            else{

            }
        }

        else{

        }
        return validLogin;
    }

    public ArrayList<Member> getAllMembers(){
        ArrayList<Member> members = memberRepo.findAllMembers();
        return members;
    }

    public Member findMember(String email) throws SQLException {
        return memberRepo.findMember(email);
    }

    public Member memberById(int id) throws SQLException {
        return memberRepo.findMemberById(id);
    }

    public Member memberByName(String name) throws SQLException {
        return memberRepo.findMemberByName(name);
    }

    public void isLeaderBoolean(boolean isLeader, String email){
        memberRepo.isLeaderBoolean(isLeader, email);
    }

    // TODO: Create session service
    public String inSession(Model model, HttpSession session, String mapping) throws SQLException {

        if(session.getAttribute("userid") == null) {
            String placeHolder = "Member";
            model.addAttribute("memberName", placeHolder);

            mapping = "redirect:/login";

            return mapping;
        }
        else{
            String userId = session.getAttribute("userid").toString();
            int memberId = Integer.parseInt(userId);
            model.addAttribute("memberName", memberById(memberId).getName());

            return mapping;
        }
    }

    public int isLeaderSession(HttpSession session){
        int isLeader;

        if(session.getAttribute("boolean-leader") == null){
            isLeader = 0;
        }
        else {
            isLeader = Integer.parseInt(session.getAttribute("boolean-leader").toString());
        }
        return isLeader;
    }

    public String logOut(HttpSession session){
        session.invalidate();

        return "redirect:/";
    }
}
