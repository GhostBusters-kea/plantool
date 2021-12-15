package com.example.plantool.services;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Author: Jonas Munk
 *
 * Session service for funktionality
 */

public class SessionService {
    MemberService memberService = new MemberService();

    //Puts a member in session
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
            model.addAttribute("memberName", memberService.memberById(memberId).getName());

            return mapping;
        }
    }

    // Finds out if member is leader
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
