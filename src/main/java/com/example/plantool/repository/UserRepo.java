package com.example.plantool.repository;


import com.example.plantool.model.User;
import com.example.plantool.utility.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepo {



    public void insertUserToDB (User user){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("INSERT INTO user(name, email, password) VALUES(?,?,?) ");
            stmt.setString(1,user.getName());
            stmt.setString(2,user.getEmail());
            stmt.setString(3,user.getPassword());
            stmt.executeUpdate();
            System.out.println("Insert complete");
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    //Fetches all users from database
    public ArrayList<User> fetchAllUser(){

        ArrayList<User> allUsers = new ArrayList<>();

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("SELECT * FROM user");

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                User tmp = new User(
                        result.getString(2),
                        result.getString(3),
                        result.getString(4));
                allUsers.add(tmp);

            }
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        return allUsers;
    }

    //Fetches a single user from database
    public User fetchUser(String email) throws SQLException {
        User tmpUser = new User(null,null, null);

        PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT user.userid, name, email, password FROM user WHERE email ='" + email + "'");

        ResultSet set = stmt.executeQuery();
        while(set.next()){
            tmpUser.setUserId(set.getInt(1));
            tmpUser.setName(set.getString(2));
            tmpUser.setEmail(set.getString(3));
            tmpUser.setPassword(set.getString(4));
        }
        System.out.println(tmpUser);
        return tmpUser;
    }

    public void isLeaderBoolean(boolean isLeader, int userId) throws SQLException {
        try{
            int leaderBoolean = isLeader ? 1 : 0;
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("UPDATE user SET projectleader="+ leaderBoolean + " WHERE userid =" + userId +"");
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
