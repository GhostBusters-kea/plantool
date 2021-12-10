package com.example.plantool.repository;


import com.example.plantool.model.Member;
import com.example.plantool.utility.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberRepo {

    public void insertMemberToDB (Member member){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("INSERT INTO user(name, email, password) VALUES(?,?,?) ");
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setString(3, member.getPassword());
            stmt.executeUpdate();
            System.out.println("Insert complete");
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    //Fetches all users from database
    public ArrayList<Member> findAllMembers (){

        ArrayList<Member> allMembers = new ArrayList<>();

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("SELECT * FROM user");

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                Member tmp = new Member(
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                 result.getInt(5));
                allMembers.add(tmp);

            }
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        return allMembers;
    }

    //Fetches a single user from database
    public Member findMember (String email) throws SQLException {
        Member tmpMember = new Member(null,null, null, 0);

        PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement
                ("SELECT user.userid, name, email, password, projectleader " +
                        "FROM user WHERE email ='" + email + "'");

        ResultSet set = stmt.executeQuery();
        while(set.next()){
            tmpMember.setUserId(set.getInt(1));
            tmpMember.setName(set.getString(2));
            tmpMember.setEmail(set.getString(3));
            tmpMember.setPassword(set.getString(4));
            tmpMember.setIsLeader(set.getInt(5));
        }
        System.out.println(tmpMember);
        return tmpMember;
    }

    public Member findMemberById (int id) throws SQLException {
        Member tmpMember = new Member(null,null, null, 0);

        PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT user.userid, name, email, password, projectleader FROM user WHERE userid ='" + id + "'");

        ResultSet set = stmt.executeQuery();
        while(set.next()){
            tmpMember.setUserId(set.getInt(1));
            tmpMember.setName(set.getString(2));
            tmpMember.setEmail(set.getString(3));
            tmpMember.setPassword(set.getString(4));
            tmpMember.setIsLeader(set.getInt(5));
        }
        System.out.println(tmpMember);
        return tmpMember;
    }

    public Member findMemberByName (String name) throws SQLException {

        Member tmpMember = new Member(null,null, null, 0);

        PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT user.userid, name, email, password, projectleader FROM user WHERE name ='" + name + "'");

        ResultSet set = stmt.executeQuery();
        while(set.next()){
            tmpMember.setUserId(set.getInt(1));
            tmpMember.setName(set.getString(2));
            tmpMember.setEmail(set.getString(3));
            tmpMember.setPassword(set.getString(4));
            tmpMember.setIsLeader(set.getInt(5));
        }
        System.out.println(tmpMember);
        return tmpMember;
    }


    public void isLeaderBoolean(boolean isLeader, String email){
        try{
            int leaderBoolean = isLeader ? 1 : 0;

            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("UPDATE user SET projectleader="+ leaderBoolean + " WHERE email ='" + email +"'");

            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
